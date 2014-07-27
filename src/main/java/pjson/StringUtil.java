package pjson;

import java.nio.charset.Charset;

/**
 */
public final class StringUtil {

    private static final Charset DEFAULT_CHAR_SET = Charset.defaultCharset();

    public static final String toString(final byte[] bts, final int start, final int len){
        return new String(bts, start, len, DEFAULT_CHAR_SET);
    }

    public static final String fastToString(final byte[] bts, final int start, final int len){
        final char[] arr = new char[len];
        final int end = start + len;
        int a = 0;
        for(int i = start; i < end; i++)
            arr[a++] = (char)bts[i];

        return new String(arr);
    }
}
