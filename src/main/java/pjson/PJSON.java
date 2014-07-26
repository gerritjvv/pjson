package pjson;

/**
 */
public final class PJSON {


    private static final byte OBJECT_START = (byte)'{';
    private static final byte OBJECT_END = (byte)'}';
    private static final byte ARR_START = (byte)'[';
    private static final byte ARR_END = (byte)']';
    private static final byte STR_MARK = (byte)'\"';
    private static final byte STR_COMMA = (byte)',';
    private static final byte STR_ESCAPE = (byte)'\\';

    private static final byte STR_COL = (byte)':';

    public static final void parse(final byte[] bts, final int start, final int len, final JSONListener events){
        final int btsLen = len;
        boolean inString = false;
        int strStartIndex = 0;
        byte prevByte = -1;
        byte bt = -1;
        boolean inNonStrObj = false;

        for(int i = start; i < btsLen; i++){
            prevByte = bt;
            bt = bts[i];
            switch (bt){
                case STR_MARK:
                    if(prevByte != STR_ESCAPE) {
                        if (inString) {
                            events.string(new String(bts, strStartIndex, i - strStartIndex));
                        } else
                            strStartIndex = i+1;

                        inString ^= true;
                    }
                    inNonStrObj = false;
                    break;
                case STR_COL:
                    inNonStrObj = true;
                    strStartIndex = i+1;
                    break;
                case STR_COMMA:
                    if(inNonStrObj) {
                        parseNumber(events, bts, strStartIndex, i - strStartIndex);
                        inNonStrObj = false;
                    }
                    break;
                case OBJECT_START:
                    if(!inString)
                     events.objectStart();
                    break;
                case OBJECT_END:
                    if(!inString){
                        if(inNonStrObj){
                            parseNumber(events, bts, strStartIndex, i - strStartIndex);
                            inNonStrObj = false;
                        }
                        events.objectEnd();
                    }
                    break;
                case ARR_START:
                    if(!inString)
                        events.arrStart();

                    break;
                case ARR_END:
                    if(!inString){
                        if(inNonStrObj){
                            parseNumber(events, bts, strStartIndex, i - strStartIndex);
                            inNonStrObj = false;
                        }
                        events.arrEnd();
                    }
                    break;

            }

        }
    }

    private static final void parseNumber(JSONListener events, byte[] bts, int i, int len){
        events.string(new String(bts, i, len));
    }

    public static final Object defaultParse(final byte[] bts){
        final DefaultListener list = new DefaultListener();
        parse(bts, 0, bts.length, list);
        return list.getValue();
    }
}
