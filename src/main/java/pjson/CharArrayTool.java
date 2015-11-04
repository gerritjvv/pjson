package pjson;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Fast unsafe char array access and copy
 */
public final class CharArrayTool {

    private static final Unsafe UNSAFE;
    public static final int CHAR_ARRAY_OFFSET;
    public static final int CHAR_ARRAY_SCALE;

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

    public static final int endOfString(char[] data, int offset, int end) {
        final int len = data.length;
        int i;
        for (i = offset; i < end; i++) {
            //we do not need to check to i != 0 because any string will always be within an object or vector
            if (data[i] == '"' && data[i - 1] != '\\')
                return i;
        }
        return i;
    }

    public static final int indexOf(char[] data, int offset, int end, char ch) {
        int i;
        for (i = offset; i < end; i++) {
            if (data[i] == ch)
                return i;
        }
        return i;
    }

    /**
     * To use switch we need to duplicate this function into indexOfEndOfObject and indexOfEndOfList
     * @param data   char array
     * @param offset
     * @param end
     * @return the next index after the closingChar
     */
    public static final int indexOfEndOfObject(char[] data, int offset, int end) {
        int i;
        int level = 0;
        boolean inString = false;

        for (i = offset; i < end; i++) {
            //over 2 condition checks switch on primitives is faster than if elseif else
            switch (data[i]) {
                case '{':
                    level++;
                    break;
                case '"':
                    i = endOfString(data, i+1, end);
                    break;
                case '}':
                    if (level == 0)
                        return i + 1;
                    else
                        level--;
                    break;

            }
        }
        return i;
    }

    /**
     * To use switch we need to duplicate this function into indexOfEndOfObject and indexOfEndOfList
     * @param data   char array
     * @param offset
     * @param end
     * @return the next index after the closingChar
     */
    public static final int indexOfEndOfList(char[] data, int offset, int end) {
        int i;
        int level = 0;
        boolean inString = false;

        for (i = offset; i < end; i++) {
            //over 2 condition checks switch on primitives is faster than if elseif else
            switch (data[i]) {
                case '[':
                    level++;
                    break;
                case '"':
                    i = endOfString(data, i+1, end);
                    break;
                case ']':
                    if (level == 0)
                        return i + 1;
                    else
                        level--;
                    break;

            }
        }
        return i;
    }


    public static final int skipWhiteSpace(char[] data, int offset, int end) {
        int i;
        for (i = offset; i < end; i++) {
            if (!Character.isSpaceChar(data[i]))
                return i;
        }
        return i;
    }

    public static final int indexFirstNonNumeric(char[] data, int offset, int end) {
        int i;
        char val;
        for (i = offset; i < end; i++) {
            val = data[i];
            if (val < '0' || val > '9')
                break;
        }
        return i;
    }

    public static final char getChar(char[] data, int offset) {

        return UNSAFE.getChar(data, (long) (CHAR_ARRAY_OFFSET + (offset * CHAR_ARRAY_SCALE)));
    }

    public static final void putChar(char[] data, int offset, char value) {
        UNSAFE.putChar(data, (long) (CHAR_ARRAY_OFFSET + (offset * CHAR_ARRAY_SCALE)), value);
    }

    public static final void copy(char[] src, int srcPos, char[] dest, int destPos, int length) {
       /*
        int a = destPos;
        int end = a + length;
        int i = srcPos;
        for(; a < length;)
            dest[a++] = src[i++];*/

        System.arraycopy(src, srcPos, dest, destPos, length);
        //UNSAFE.copyMemory(src, CHAR_ARRAY_OFFSET + (srcPos * CHAR_ARRAY_SCALE), dest, CHAR_ARRAY_OFFSET + (destPos * CHAR_ARRAY_SCALE), length * CHAR_ARRAY_SCALE);
    }
}
