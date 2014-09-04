package pjson;

import clojure.lang.AFn;
import clojure.lang.PersistentArrayMap;
import clojure.lang.PersistentHashMap;
import clojure.lang.PersistentVector;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 (import 'pjson.JSONGenerator)
 (require '[cheshire.core :as cheshire])
 (def msg (-> "test-resources/msg.json" slurp cheshire/parse-string))
 (dotimes [i 1000000]
 (JSONGenerator/forObj msg))


  Performance tests: Goal < 800ms
     StringWriter == 1.8 sec
     Using internal byte array and casting char arrays to byte[]
            -> no numbers 1.6 sec
            -> no string creation in toString 1.5 sec
            -> only iteration over data structures, no writing 1.0 sec
            -> only iterate over Map, List and Collection, no writing or other iterations 668.252724 ms
            -> iterate using kvmap 488.328465ms NOOP
            -> iterate with byte array creating in UTF8JSONGenerator 724.631374 ms

 */
public final class JSONGenerator {


    public static final String forObj(Object obj) throws IOException{
        JSONWriter writer = new JSONWriter.StringBuilderWriter();

        forObj(writer, obj);

        return writer.toString();
    }

    public static final void forObj(final JSONWriter writer, Object obj) throws IOException {
       if(obj == null){
            writer.writeNull();
       }else{

           if(obj instanceof String) {
               writer.writeString((String)obj);
           }
           else if(obj instanceof Integer) {
               writer.writeInt((Integer)obj);
           }else if(obj instanceof Long) {
               writer.writeLong((Long)obj);
           }else if(obj instanceof Double) {
               writer.writeDouble((Double)obj);
           }else if(obj instanceof Float) {
               writer.writeFloat((Float)obj);
           }else if(obj instanceof ToJSONString) {
               ((ToJSONString)obj).toString(writer);
           }else if(obj instanceof PersistentArrayMap) {
               PersistentArrayMap map = (PersistentArrayMap)obj;

               map.kvreduce(new AFn() {
                   @Override
                   public Object invoke(Object state, Object key, Object val) {
                       if(((Counter)state).i++ != 0)
                           writer.writeComma();

                       writer.writeFieldName(key.toString());

                       try{
                           JSONGenerator.forObj(writer, val);
                       }catch(Exception e){
                           throw new RuntimeException(e);
                       }

                       return state;
                   }
               }, new Counter());
           }else if(obj instanceof PersistentHashMap) {
               PersistentHashMap map = (PersistentHashMap)obj;
               map.kvreduce(new AFn() {
                   @Override
                   public Object invoke(Object state, Object key, Object val) {
                       if(((Counter)state).i++ != 0)
                           writer.writeComma();

                       writer.writeFieldName(key.toString());

                       try{
                           JSONGenerator.forObj(writer, val);
                       }catch(Exception e){
                           throw new RuntimeException(e);
                       }

                       return state;
                   }
               }, new Counter());
           }else if(obj instanceof PersistentVector) {
               ((PersistentVector)obj).kvreduce(new AFn() {
                   @Override
                   public Object invoke(Object state, Object k, Object val) {
                       if(((Counter)state).i++ != 0)
                           writer.writeComma();

                       try{
                           JSONGenerator.forObj(writer, val);
                       }catch(Exception e){
                           throw new RuntimeException(e);
                       }

                       return state;
                   }
               }, new Counter());
           }else if(obj instanceof Map){
               writer.startObj();
               int i = 0;
               for(Map.Entry<Object, Object> entry : ((Map<Object, Object>)obj).entrySet()){
                   if(i++ != 0)
                       writer.writeComma();

                   writer.writeFieldName(entry.getKey().toString());
                   forObj(writer, entry.getValue());
               }

               writer.endObj();

           }else if(obj instanceof List){
               List<Object> list = (List<Object>)obj;

               for(int i = 0; i < list.size(); i++){
                   if(i != 0)
                       writer.writeComma();

                   forObj(writer, list.get(i));
               }

           }else if(obj instanceof Collection){
               Iterator<Object> coll = ((Collection<Object>)obj).iterator();
               int i = 0;

               writer.startArr();
               while(coll.hasNext()){
                   if(i++ != 0)
                       writer.writeComma();
                   forObj(writer, coll.next());
               }

               writer.endArr();
           }
       }
    }

    private static final class Counter{
        int i = 0;
    }

}
