package pjson;

import clojure.lang.*;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * (import 'pjson.JSONGenerator)
 * (require '[cheshire.core :as cheshire])
 * (def msg (-> "test-resources/msg.json" slurp cheshire/parse-string))
 * (dotimes [i 1000000]
 * (JSONGenerator/forObj msg))
 * <p/>
 * <p/>
 * Performance tests: Goal < 800ms
 * StringWriter == 1.8 sec
 * Using internal byte array and casting char arrays to byte[]
 * -> no numbers 1.6 sec
 * -> no string creation in toString 1.5 sec
 * -> only iteration over data structures, no writing 1.0 sec
 * -> only iterate over Map, List and Collection, no writing or other iterations 668.252724 ms
 * -> iterate using kvmap 488.328465ms NOOP
 * -> iterate with byte array creating in UTF8JSONGenerator 724.631374 ms
 */
public final class JSONGenerator {


    public static final String forObj(final Object obj) throws IOException {
        final JSONWriter writer = new JSONWriter.StringBuilderWriter();
        forObj(writer, obj);
        return writer.toString();
    }

    public static final void forObj(final JSONWriter writer, final Object obj) throws IOException {
        if (obj == null) {
            writer.writeNull();
        } else {
            if (obj instanceof String) {
                writer.writeString((String) obj);
            } else if (obj instanceof Named) {
                //support keywords and symbols
                writer.writeString(((Named) obj).getName());
            } else if (obj instanceof Integer) {
                writer.writeInt((Integer) obj);
            } else if (obj instanceof Long) {
                writer.writeLong((Long) obj);
            } else if (obj instanceof Double) {
                writer.writeDouble((Double) obj);
            } else if (obj instanceof Float) {
                writer.writeFloat((Float) obj);
            } else if (obj instanceof ToJSONString) {
                ((ToJSONString) obj).toString(writer);
            } else if (obj instanceof PersistentArrayMap) {
                writer.startObj();
                final PersistentArrayMap map = (PersistentArrayMap) obj;

                map.kvreduce(new AFn() {
                    @Override
                    public Object invoke(Object state, Object key, Object val) {
                        if (((Counter) state).i++ != 0)
                            writer.writeComma();

                        writer.writeFieldName(convertFieldName(key));

                        try {
                            JSONGenerator.forObj(writer, val);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        return state;
                    }
                }, new Counter());
                writer.endObj();
            } else if (obj instanceof PersistentHashMap) {
                writer.startObj();
                final PersistentHashMap map = (PersistentHashMap) obj;
                map.kvreduce(new AFn() {
                    @Override
                    public Object invoke(Object state, Object key, Object val) {
                        if (((Counter) state).i++ != 0)
                            writer.writeComma();

                        writer.writeFieldName(convertFieldName(key.toString()));

                        try {
                            JSONGenerator.forObj(writer, val);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        return state;
                    }
                }, new Counter());
                writer.endObj();
            } else if (obj instanceof PersistentVector) {
                writer.startArr();
                ((PersistentVector) obj).kvreduce(new AFn() {
                    @Override
                    public Object invoke(Object state, Object k, Object val) {
                        if (((Counter) state).i++ != 0)
                            writer.writeComma();

                        try {
                            JSONGenerator.forObj(writer, val);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        return state;
                    }
                }, new Counter());
                writer.endArr();
            } else if (obj instanceof Map) {
                writer.startObj();
                int i = 0;
                for (Map.Entry<Object, Object> entry : ((Map<Object, Object>) obj).entrySet()) {
                    if (i++ != 0)
                        writer.writeComma();

                    writer.writeFieldName(convertFieldName(entry.getKey()));
                    forObj(writer, entry.getValue());
                }

                writer.endObj();

            } else if (obj instanceof List) {
                final List<Object> list = (List<Object>) obj;
                writer.startArr();
                for (int i = 0; i < list.size(); i++) {
                    if (i != 0)
                        writer.writeComma();

                    forObj(writer, list.get(i));
                }
                writer.endArr();
            } else if (obj instanceof Iterable) {
                final Iterator<Object> coll = ((Iterable<Object>) obj).iterator();
                int i = 0;

                writer.startArr();
                while (coll.hasNext()) {
                    if (i++ != 0)
                        writer.writeComma();
                    forObj(writer, coll.next());
                }

                writer.endArr();
            }else {
                writer.writeString(cleanUnknownOutput(obj.toString()));
            }
        }
    }

    private final static String convertFieldName(Object key) {
        if (key instanceof String)
            return (String) key;
        else if (key instanceof Named)
            return ((Named) key).getName();
        else
            return key.toString();
    }

    /**
     * Used to cleanout any unknown objects that would most certainly contain illegal characters.
     *
     * @param s
     * @return
     */
    private final static String cleanUnknownOutput(String s) {
        char[] chrs = StringUtil.toCharArray(s);
        char[] newchrs = new char[chrs.length];
        for (int i = 0; i < chrs.length; i++) {
            char ch = chrs[i];
            if (ch == '[' || ch == ']' || ch == '{' || ch == '}' || ch == '"')
                newchrs[i] = '_';
            else
                newchrs[i] = chrs[i];

        }

        return StringUtil.noCopyStringFromChars(newchrs);
    }

    private static final class Counter {
        int i = 0;
    }
}
