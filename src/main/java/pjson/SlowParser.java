package pjson;

import java.util.Arrays;

/**
 * Parser to handle cases where we have strings with escaped \[data]
 */
public class SlowParser {

    /**
     * Return a string result and its index
     */
    public static class ParseResult {
        final int index;
        final String val;

        public ParseResult(int index, String val) {
            this.index = index;
            this.val = val;
        }
    }

    /**
     * shamelessly stolen from Jettison.
     */
    public static ParseResult parseString(char[] bts, int strStart2, int end) {

        StringBuilder buff = new StringBuilder();
        char ch;

        char[] buff4 = new char[4];
        char[] buff2 = new char[2];

        int i = strStart2;

        outer:

        for (; i < end; i++) {
            ch = bts[i];

            switch (ch) {
                case 0:
                case '\n':
                case '\r':
                    buff.append(ch);
                    break;
                case '"':
                    break outer;
                case '\\':

                    ch = bts[i++];

                    switch (ch) {
                        case '\\':
                            //could be a double escape from json escaping e.g \\u0027 -> \u0027, \\\\ -> \\ but never \\\
                            char ch2 = bts[i];

                            if (ch2 == 'u') {
                                //due to the inner double \ we must backtrack one index.
                                i = _parseUnicodeChar(bts, buff, buff4, i + 1)-1;
                            } else
                                buff.append(ch);

                            break;
                        case 'b':
                            buff.append('\b');
                            break;
                        case 't':
                            buff.append('\t');
                            break;
                        case 'n':
                            buff.append('\n');
                            break;
                        case 'f':
                            buff.append('\f');
                            break;
                        case 'r':
                            buff.append('\r');
                            break;
                        case 'u':

                            i = _parseUnicodeChar(bts, buff, buff4, i);

                            break;
                        case 'x':

                            for (int a = 0; a < 2; a++, i++)
                                buff2[a] = bts[i];

                            buff.append((char) Integer.parseInt(StringUtil.noCopyStringFromChars(buff2), 16));
                            break;
                        case '"':
                            break outer;
                        default:
                            buff.append(ch);
                    }

                    break;

                default:
                    buff.append(ch);
            }
        }

        return new ParseResult( bts[i] == '"' ? i+1 : i, buff.toString());
    }


    /**
     * Very ugly helper method that takes the pre-created buff4 and reads unicode data into it, then adds it to the buff,
     * updates the i index and returns it.
     */
    private static int _parseUnicodeChar(char[] bts, StringBuilder buff, char[] buff4, int i) {
        for (int a = 0; a < 4; a++, i++) {
            buff4[a] = (bts[i]);
        }
        //note: require toChars to handle unicode characters represented by more than one char
        buff.append(Character.toChars(Integer.parseInt(StringUtil.noCopyStringFromChars(buff4), 16)));
        return i;
    }
}
