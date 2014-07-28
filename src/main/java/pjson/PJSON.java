package pjson;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Fast simple json parser.<br/>
 * Note that numbers are not converted to any Java number and are returned as java Strings.<br/>
 * This improves performs but has the side effect that you need to parse any number data before treating it as an actual number.
 */
public final class PJSON {


    private static final char OBJECT_START = '{';
    private static final char OBJECT_END = '}';
    private static final char ARR_START = '[';
    private static final char ARR_END = ']';
    private static final char STR_MARK = '\"';
    private static final char STR_COMMA = ',';
    private static final char STR_ESCAPE = '\\';
    private static final char STR_SPACE = ' ';

    private static final char STR_DOT = '.';
    private static final char STR_T = 'T';
    private static final char STR_F = 'F';
    private static final char STR_t = 't';
    private static final char STR_f = 'f';

    private static final char STR_COL = ':';

    public static final void parse(final Charset charset, final byte[] bts, final int start, final int len, final JSONListener events){
        final char chars[] = StringUtil.toCharArrayFromBytes(bts, charset, start, len);
        parse(chars, 0, chars.length, events);
    }
    /**
     * Parses the byte array for json data.
     * @param bts byte array
     * @param start start offset
     * @param len number of bytes to parse
     * @param events JSONListener, all events will be sent to this instance.
     */
    public static final void parse(final char[] bts, final int start, final int len, final JSONListener events){
        final int btsLen = start + len;
        boolean inString = false;
        int strStartIndex = 0;
        char prevByte = '-';
        char bt = '-';
        boolean inNonStrObj = false;
        boolean seenDot = false;
        byte seenBool = -1;
        int lastNonSpace = -1;

        for(int i = start; i < btsLen; i++){
            prevByte = bt;

            bt = CharArrayTool.getChar(bts, i);

            if(bt != STR_SPACE)
                lastNonSpace = i;

            switch (bt){
                case STR_MARK:
                    seenDot = false;
                    seenBool = -1;
                    if(prevByte != STR_ESCAPE) {
                        if (inString) {
                            events.string(StringUtil.toString(bts, strStartIndex, i - strStartIndex));
                            strStartIndex = -1;
                        } else
                            strStartIndex = i+1;

                        inString ^= true;
                    }
                    inNonStrObj = false;
                    break;
                case STR_COL:
                    if(!inString){
                        inNonStrObj = true;
                        strStartIndex = skipWhiteSpace(bts, i, btsLen);
                    }
                    break;
                case STR_COMMA:
                    if(inNonStrObj) {
                        final int diff = i - lastNonSpace;
                        if(diff > 1)
                            parseNumber(events, bts, strStartIndex, diff, seenDot, seenBool);
                        inNonStrObj = seenDot = false;
                        seenBool = -1;
                    }
                    break;
                case OBJECT_START:
                    if(!inString)
                     events.objectStart();
                    strStartIndex = i+1;
                    seenDot = false;
                    seenBool = -1;
                    break;
                case OBJECT_END:
                    if(!inString){
                        if(inNonStrObj){
                            final int diff = i - lastNonSpace;
                            if(diff > 1)
                                parseNumber(events, bts, strStartIndex, diff, seenDot, seenBool);
                            inNonStrObj = seenDot = false;
                            seenBool = -1;
                        }
                        events.objectEnd();
                    }
                    seenDot = false;
                    seenBool = -1;
                    strStartIndex=i+1;
                    break;
                case ARR_START:
                    if(!inString)
                        events.arrStart();

                    seenDot = false;
                    seenBool = -1;
                    strStartIndex=i+1;
                    break;
                case ARR_END:
                    if(!inString){
                        if(inNonStrObj){
                            final int diff = i - lastNonSpace;
                            if(diff > 1)
                                parseNumber(events, bts, strStartIndex, diff, seenDot, seenBool);
                            inNonStrObj = seenDot = false;
                            seenBool = -1;
                        }
                        events.arrEnd();
                    }
                    strStartIndex=i+1;
                    break;
                case STR_DOT:
                    if(inNonStrObj)
                        seenDot = true;
                    break;
                case STR_F:
                    seenBool = STR_F;
                    break;
                case STR_T:
                    if(inNonStrObj)
                        seenBool = STR_T;
                    break;
                case STR_f:
                    if(inNonStrObj)
                        seenBool = STR_F;
                    break;
                case STR_t:
                    if(inNonStrObj)
                        seenBool = STR_T;
                    break;
            }

        }
    }

    private static final int skipWhiteSpace(char[] chars, int offset, int btsLen){
        //skip white space
        char bt;
        for(; offset < btsLen;){
            offset++;
            bt = CharArrayTool.getChar(chars, offset);
            if(bt != STR_SPACE)
                break;
        }
        return offset;
    }

    /**
     * Returns a String from a number.
     * @param events
     * @param bts
     * @param i
     * @param len
     */
    private static final void parseNumber(JSONListener events, char[] bts, int i, int len, boolean seenDot, byte seenBool){
        final String str = StringUtil.fastToString(bts, i, len);
        System.out.println("String: " + str + " count: " + str.length());
        if(seenDot)
            events.number(Double.valueOf(str));
        else if(seenBool == STR_T)
            events.number(Boolean.TRUE);
        else if(seenBool == STR_F)
            events.number(Boolean.FALSE);
        else if(str.charAt(0) == 'n')
            events.string("null");
        else if(str.length() < 10)
            events.number(Integer.valueOf(str));
        else
            events.number(Long.valueOf(str));
    }

    /**
     * Parse the whole byte array bts.
     * @param bts
     * @return Object Map or Collection.
     */
    public static final Object defaultParse(final Charset charset, final byte[] bts){
        final DefaultListener list = new DefaultListener();
        parse(charset, bts, 0, bts.length, list);
        return list.getValue();
    }

    /**
     * Parse the bts byte array from offset start and to < len.
     * @param bts
     * @param start
     * @param len
     * @return Object Map or Collection
     */
    public static final Object defaultParse(final Charset charset, final byte[] bts, final int start, final int len){
        final DefaultListener list = new DefaultListener();
        parse(charset, bts, start, len, list);
        return list.getValue();
    }

}
