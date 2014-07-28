package pjson;

import sun.misc.Unsafe;
import java.lang.reflect.Field;

/**
 * Fast unsafe char array access and copy
 */
public class CharArrayTool {

    private static final Unsafe UNSAFE;
    public static final long CHAR_ARRAY_OFFSET;
    public static final long CHAR_ARRAY_SCALE;

    static {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            UNSAFE = (Unsafe) theUnsafe.get(null);
            int boo = UNSAFE.arrayBaseOffset(char[].class);
            // It seems not all Unsafe implementations implement the following method.
            UNSAFE.copyMemory(new char[1], boo, new char[1], boo, 1);
            CHAR_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(char[].class);
            CHAR_ARRAY_SCALE = UNSAFE.arrayIndexScale(char[].class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static final char getChar(char[] data, int offset) {

        return UNSAFE.getChar(data, CHAR_ARRAY_OFFSET + (offset * CHAR_ARRAY_SCALE));
    }

    public static final void putChar(char[] data, int offset, char value) {
        UNSAFE.putChar(data, (long)(CHAR_ARRAY_OFFSET + (offset * CHAR_ARRAY_SCALE)), value);
    }

    public static final void copy(char[] src, int srcPos, char[] dest, int destPos, int length) {
        UNSAFE.copyMemory(src, CHAR_ARRAY_OFFSET + (srcPos * CHAR_ARRAY_SCALE), dest, CHAR_ARRAY_OFFSET + (destPos * CHAR_ARRAY_SCALE), length);
    }
}
