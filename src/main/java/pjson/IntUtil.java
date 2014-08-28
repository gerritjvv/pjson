package pjson;

/**
 * Created by gvanvuuren on 29/07/14.
 */
public class IntUtil {
    /**
     * Please see:
     * http://stackoverflow.com/questions/1030479/most-efficient-way-
     * of-converting-string-to-integer-in-java<br/>
     * This method is the fastest String to int conversion method tested so far.
     *
     * @param chars
     * @param from int
     * @param len int
     * @return
     */
    public static final int intValueOf(char[] chars, int from, int len) {
        int ival = 0, idx = from;
        int end = idx + len;
        boolean sign = false;
        char ch;

        if (((ch = CharArrayTool.getChar(chars, idx)) < '0' || ch > '9')
                && (!(sign = ch == '-') || ++idx == end || ((ch = CharArrayTool.getChar(chars, idx)) < '0' || ch > '9')))
            throw new NumberFormatException();

        for (;; ival *= 10) {
            ival += '0' - ch;
            if (++idx == end)
                return sign ? ival : -ival;
            if ((ch = CharArrayTool.getChar(chars, idx)) < '0' || ch > '9')
                throw new NumberFormatException();
        }
    }
}
