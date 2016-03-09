package pjson;

import java.math.BigInteger;

/**
 * Fast number parsing.
 * Integers can have a maximum string length of 10, and Long a maximum of 19.<br/>
 * All other numbers are not parsable, this fact allows us to unroll all the<br/>
 * number parsing functions.
 */
public class NumberUtil {

    public static final int parse_1(char[] ch, int start) {
        return (ch[start] - '0');
    }

    public static final int parse_2(char[] ch, int start) {
        return (ch[start] - '0')
                * 10 + (ch[start + 1] - '0');
    }

    public static final int parse_3(char[] ch, int start) {

        return (((((ch[start] - '0')
                * 10) + (ch[start + 1] - '0'))
                * 10) + (ch[start + 2] - '0'));
    }

    public static final int parse_4(char[] ch, int start) {

        return (((((((ch[start] - '0')
                * 10) + (ch[start + 1] - '0'))
                * 10) + (ch[start + 2] - '0'))
                * 10) + (ch[start + 3] - '0'));
    }

    public static final int parse_5(char[] ch, int start) {
        return (((((((((ch[start] - '0')
                * 10) + (ch[start + 1] - '0'))
                * 10) + (ch[start + 2] - '0'))
                * 10) + (ch[start + 3] - '0'))
                * 10) + (ch[start + 4] - '0'));
    }

    public static final int parse_6(char[] ch, int start) {
        return (((((((((((ch[start] - '0')
                * 10) + (ch[start + 1] - '0'))
                * 10) + (ch[start + 2] - '0'))
                * 10) + (ch[start + 3] - '0'))
                * 10) + (ch[start + 4] - '0'))
                * 10) + (ch[start + 5] - '0'));
    }

    public static final int parse_7(char[] ch, int start) {
        return (((((((((((((ch[start] - '0')
                * 10) + (ch[start + 1] - '0'))
                * 10) + (ch[start + 2] - '0'))
                * 10) + (ch[start + 3] - '0'))
                * 10) + (ch[start + 4] - '0'))
                * 10) + (ch[start + 5] - '0'))
                * 10) + (ch[start + 6] - '0'));
    }

    public static final int parse_8(char[] ch, int start) {
        return (((((((((((((((ch[start] - '0')
                * 10) + (ch[start + 1] - '0'))
                * 10) + (ch[start + 2] - '0'))
                * 10) + (ch[start + 3] - '0'))
                * 10) + (ch[start + 4] - '0'))
                * 10) + (ch[start + 5] - '0'))
                * 10) + (ch[start + 6] - '0'))
                * 10) + (ch[start + 7] - '0'));
    }

    public static final int parse_9(char[] ch, int start) {
        return (((((((((((((((((ch[start] - '0')
                * 10) + (ch[start + 1] - '0'))
                * 10) + (ch[start + 2] - '0'))
                * 10) + (ch[start + 3] - '0'))
                * 10) + (ch[start + 4] - '0'))
                * 10) + (ch[start + 5] - '0'))
                * 10) + (ch[start + 6] - '0'))
                * 10) + (ch[start + 7] - '0'))
                * 10) + (ch[start + 8] - '0'));
    }

    public static final long parse_10(char[] ch, int start) {
        return (((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'));
    }

    public static final long parse_11(char[] ch, int start) {
        return (((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'));
    }

    public static final long parse_12(char[] ch, int start) {
        return (((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'));
    }

    public static final long parse_13(char[] ch, int start) {
        return (((((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'))
                * 10L) + (ch[start + 12] - '0'));
    }

    public static final long parse_14(char[] ch, int start) {
        return (((((((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'))
                * 10L) + (ch[start + 12] - '0'))
                * 10L) + (ch[start + 13] - '0'));
    }

    public static final long parse_15(char[] ch, int start) {
        return (((((((((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'))
                * 10L) + (ch[start + 12] - '0'))
                * 10L) + (ch[start + 13] - '0'))
                * 10L) + (ch[start + 14] - '0'));
    }

    public static final long parse_16(char[] ch, int start) {
        return (((((((((((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'))
                * 10L) + (ch[start + 12] - '0'))
                * 10L) + (ch[start + 13] - '0'))
                * 10L) + (ch[start + 14] - '0'))
                * 10L) + (ch[start + 15] - '0'));
    }

    public static final long parse_17(char[] ch, int start) {
        return (((((((((((((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'))
                * 10L) + (ch[start + 12] - '0'))
                * 10L) + (ch[start + 13] - '0'))
                * 10L) + (ch[start + 14] - '0'))
                * 10L) + (ch[start + 15] - '0'))
                * 10L) + (ch[start + 16] - '0'));
    }

    public static final long parse_18(char[] ch, int start) {
        return (((((((((((((((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'))
                * 10L) + (ch[start + 12] - '0'))
                * 10L) + (ch[start + 13] - '0'))
                * 10L) + (ch[start + 14] - '0'))
                * 10L) + (ch[start + 15] - '0'))
                * 10L) + (ch[start + 16] - '0'))
                * 10L) + (ch[start + 17] - '0'));
    }

    public static final long parse_19(char[] ch, int start) {
        return (((((((((((((((((((((((((((((((((((((ch[start] - '0')
                * 10L) + (ch[start + 1] - '0'))
                * 10L) + (ch[start + 2] - '0'))
                * 10L) + (ch[start + 3] - '0'))
                * 10L) + (ch[start + 4] - '0'))
                * 10L) + (ch[start + 5] - '0'))
                * 10L) + (ch[start + 6] - '0'))
                * 10L) + (ch[start + 7] - '0'))
                * 10L) + (ch[start + 8] - '0'))
                * 10L) + (ch[start + 9] - '0'))
                * 10L) + (ch[start + 10] - '0'))
                * 10L) + (ch[start + 11] - '0'))
                * 10L) + (ch[start + 12] - '0'))
                * 10L) + (ch[start + 13] - '0'))
                * 10L) + (ch[start + 14] - '0'))
                * 10L) + (ch[start + 15] - '0'))
                * 10L) + (ch[start + 16] - '0'))
                * 10L) + (ch[start + 17] - '0'))
                * 10L) + (ch[start + 18] - '0'));
    }

    public static final BigInteger parseBigInteger(char[] array, int offset, int len, boolean isNeg) {
        if(isNeg)
            return new BigInteger("-" + StringUtil.fastToString(array, offset, len));
        else
            return new BigInteger(StringUtil.fastToString(array, offset, len));
    }
}
