package pjson;

import pjson.key.KeyFn;
import pjson.key.StringKeyFn;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Fast simple json parser.<br/>
 */
public final class PJSON {


    public static final void lazyParse(final Charset charset, final byte[] bts, final int start, final int len, final JSONListener events) {
        final char[] chars = StringUtil.toCharArrayFromBytes(bts, charset, start, len);
        lazyParse(chars, 0, chars.length, events);
    }


    /**
     * Parses the byte array for json data and create lazy objects
     *
     * @param bts    byte array
     * @param start  start offset
     * @param end    the end index
     * @param events JSONListener, all events will be sent to this instance.
     */
    public static final int lazyParse(final char[] bts, final int start, final int end, final JSONListener events) {
        final int btsLen = end;
        char bt;

        int idx = CharArrayTool.skipWhiteSpace(bts, start, btsLen);

        bt = CharArrayTool.getChar(bts, idx);

        if (bt == '{') {
            events.objectStart();

            int strStart = CharArrayTool.indexOf(bts, idx + 1, end, '"') + 1;
            idx = CharArrayTool.endOfString(bts, strStart, end);
            events.string(StringUtil.fastToString(bts, strStart, idx - strStart));
            idx = CharArrayTool.indexOf(bts, idx + 1, end, ':');
            idx++;

        } else if (bt == '[') {
            events.arrStart();
            idx = CharArrayTool.skipWhiteSpace(bts, idx + 1, btsLen);
        } else
            throw new RuntimeException("Invalid message, expected [/{ found " + bt);

        if (idx >= btsLen) {
            events.objectEnd();
            return btsLen;
        }

        int endIndex;

        for (; idx < btsLen; idx++) {
            idx = CharArrayTool.skipWhiteSpace(bts, idx, btsLen);
            bt = CharArrayTool.getChar(bts, idx);

            switch (bt) {
                case '"': //34
                    final int strStart2 = idx + 1;
                    try {
                        idx = CharArrayTool.endOfString2(bts, strStart2, end);
                        events.string(StringUtil.fastToString(bts, strStart2, idx - strStart2));
                    } catch (CharArrayTool.SlowParserException slowe) {
                        SlowParser.ParseResult result = SlowParser.parseString(bts, strStart2, end);
                        events.string(result.val);
                        idx = result.index;
                    }
                    break;
                case '-': //45
                    idx++;
                    bt = CharArrayTool.getChar(bts, idx);
                    if (Character.isDigit(bt)) {
                        endIndex = CharArrayTool.indexFirstNonNumeric(bts, idx, end);

                        if (bts[endIndex] == '.') {
                            idx--;
                            int idx2 = CharArrayTool.indexFirstNonNumeric(bts, endIndex + 1, end);
                            parseDouble(bts, idx, idx2, events);
                            idx = idx2 - 1;
                        } else if (CharArrayTool.contains(bts, idx, endIndex, 'e', 'E')) {
                            idx--;
                            parseBigDecimal(bts, idx, endIndex, events);
                            idx = endIndex - 1;
                        } else {
                            parseInt(bts, idx, endIndex, events, true);
                            idx = endIndex - 1;
                        }
                    }
                    break;
                case 'F': //70
                    events.number(Boolean.FALSE);
                    idx += 4; ////add 4 we are already at F
                    break;
                case 'T': //84
                    events.number(Boolean.TRUE);
                    idx += 3; //add 3 we are already at T
                    break;
                case '[': //91
                    endIndex = CharArrayTool.indexOfEndOfList(bts, idx + 1, btsLen);
                    events.lazyArr(bts, idx, endIndex);
                    idx = endIndex;
                    break;
                case 'f': //102
                    events.number(Boolean.FALSE);
                    idx += 4; ////add 4 we are already at F
                    break;
                case '}':
                    events.objectEnd();
                    break;
                case 'n':
                    events.string(null);
                    idx += 3; //add 3 we are already at n
                    break;
                case 't':
                    events.number(Boolean.TRUE);
                    idx += 3; //add 3 we are already at T
                    break;
                case '{':
                    endIndex = CharArrayTool.indexOfEndOfObject(bts, idx + 1, btsLen);

                    events.lazyObject(bts, idx, endIndex);
                    idx = endIndex;
                    break;

                case 48: //0-9 are numbers
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:

                    endIndex = CharArrayTool.indexFirstNonNumeric(bts, idx, end);

                    if (bts[endIndex] == '.') {
                        int idx2 = CharArrayTool.indexFirstNonNumeric(bts, endIndex + 1, end);
                        parseDouble(bts, idx, idx2, events);
                        idx = idx2 - 1;
                    } else if (CharArrayTool.contains(bts, idx, endIndex, 'e', 'E')) {
                        parseBigDecimal(bts, idx, endIndex, events);
                        idx = endIndex - 1;
                    } else {
                        parseInt(bts, idx, endIndex, events, false);
                        idx = endIndex - 1;
                    }
                    break;

                default:
                    break;
            }
        }

        return idx;

    }

    private static final void parseInt(char[] bts, int offset, int end, JSONListener events, boolean isNeg) {
        int len = end - offset;
        long l;

        switch (len) {
            case 1:
                l = NumberUtil.parse_1(bts, offset);
                break;
            case 2:
                l = NumberUtil.parse_2(bts, offset);
                break;
            case 3:
                l = NumberUtil.parse_3(bts, offset);
                break;
            case 4:
                l = NumberUtil.parse_4(bts, offset);
                break;
            case 5:
                l = NumberUtil.parse_5(bts, offset);
                break;
            case 6:
                l = NumberUtil.parse_6(bts, offset);
                break;
            case 7:
                l = NumberUtil.parse_7(bts, offset);
                break;
            case 8:
                l = NumberUtil.parse_8(bts, offset);
                break;
            case 9:
                l = NumberUtil.parse_9(bts, offset);
                break;
            case 10:
                l = NumberUtil.parse_10(bts, offset);
                break;
            case 11:
                l = NumberUtil.parse_11(bts, offset);
                break;
            case 12:
                l = NumberUtil.parse_12(bts, offset);
                break;
            case 13:
                l = NumberUtil.parse_13(bts, offset);
                break;
            case 14:
                l = NumberUtil.parse_14(bts, offset);
                break;
            case 15:
                l = NumberUtil.parse_15(bts, offset);
                break;
            case 16:
                l = NumberUtil.parse_16(bts, offset);
                break;
            case 17:
                l = NumberUtil.parse_17(bts, offset);
                break;
            case 18:
                l = NumberUtil.parse_18(bts, offset);
                break;
            case 19:
                try {
                    //LONG MAX is 9223372036854775807, values like 9720766928229509520 will overflow
                    //we need to catch possible number format exceptions and parse a big integer
                    l = NumberUtil.parse_19(bts, offset);
                } catch (NumberFormatException e) {
                    events.bigInteger(NumberUtil.parseBigInteger(bts, offset, end - offset, isNeg));
                    return;
                }
                break;
            default:
                events.bigInteger(NumberUtil.parseBigInteger(bts, offset, end - offset, isNeg));
                return;
        }

        events.number(new Long(isNeg ? l * -1 : l));
    }

    private static final void parseBigDecimal(char[] bts, int offset, int end, JSONListener events) {
        final String str = StringUtil.fastToString(bts, offset, end - offset);
        events.bigDecimal(new BigDecimal(str));
    }

    private static final void parseDouble(char[] bts, int offset, int end, JSONListener events) {
        int len = end - offset;

        final String str = StringUtil.fastToString(bts, offset, len);

        if (len > 22 || CharArrayTool.contains(bts, offset, end+1, 'e', 'E'))
            events.bigDecimal(new BigDecimal(str));
        else {
            try {
                events.number(Double.valueOf(str));
            } catch (NumberFormatException e) {
                try {
                    events.bigDecimal(new BigDecimal(str));
                } catch (ArrayIndexOutOfBoundsException bde) {
                    throw new RuntimeException("Error trying to parse " + str, bde);
                }
            }
        }
    }

    /**
     * Parse the bts byte array from offset start and to < len.
     *
     * @param bts
     * @param start
     * @param len
     * @return Object Map or Collection
     */
    public static final Object defaultLazyParse(final Charset charset, final byte[] bts, final int start, final int len) {
        return defaultLazyParse(charset, bts, start, len, StringKeyFn.INSTANCE);
    }

    /**
     * Parse the bts byte array from offset start and to < len.
     *
     * @param bts
     * @param start
     * @param len
     * @return Object Map or Collection
     */
    public static final Object defaultLazyParse(final Charset charset, final byte[] bts, final int start, final int len, KeyFn<String, ?> keyFn) {
        final DefaultListener list = new DefaultListener(keyFn);
        //lazyParse(final char[] bts, final int start, final int end, final JSONListener events
        lazyParse(charset, bts, start, len, list);
        return list.getValue();
    }

    public static final Object defaultLazyParse(final Charset charset, final char[] bts, final int start, final int len) {
        return defaultLazyParse(charset, bts, start, len, StringKeyFn.INSTANCE);
    }

    public static final Object defaultLazyParse(final Charset charset, final char[] bts, final int start, final int len, KeyFn<String, ?> keyFn) {
        final DefaultListener list = new DefaultListener(keyFn);
        //lazyParse(final char[] bts, final int start, final int end, final JSONListener events
        lazyParse(bts, 0, bts.length, list);
        return list.getValue();
    }

    /**
     * Parse the whole byte array bts.
     *
     * @param bts
     * @return Object Map or Collection.
     */
    public static final Object defaultLazyParse(final Charset charset, final char[] bts) {
        return defaultLazyParse(charset, bts, StringKeyFn.INSTANCE);
    }

    /**
     * Parse the whole byte array bts.
     *
     * @param bts
     * @return Object Map or Collection.
     */
    public static final Object defaultLazyParse(final Charset charset, final char[] bts, KeyFn<String, ?> keyFn) {
        return defaultLazyParse(charset, bts, 0, bts.length, keyFn);
    }

    /**
     * Parse the whole byte array bts.
     *
     * @param bts
     * @return Object Map or Collection.
     */
    public static final Object defaultLazyParse(final Charset charset, final byte[] bts) {
        return defaultLazyParse(charset, bts, StringKeyFn.INSTANCE);
    }

    /**
     * Parse the whole byte array bts.
     *
     * @param bts
     * @return Object Map or Collection.
     */
    public static final Object defaultLazyParse(final Charset charset, final byte[] bts, KeyFn<String, ?> keyFn) {
        return defaultLazyParse(charset, bts, 0, bts.length, keyFn);
    }
}
