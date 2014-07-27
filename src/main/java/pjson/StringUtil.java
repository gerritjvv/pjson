package pjson;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Utility functions for converting bytes to String.
 */
public final class StringUtil {

    public static final Charset DEFAULT_CHAR_SET = Charset.forName("iso-8859-1");

    public static final String toString(Charset charset, final byte[] bts, final int start, final int len){
        return new String(bts, start, len, charset);
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
