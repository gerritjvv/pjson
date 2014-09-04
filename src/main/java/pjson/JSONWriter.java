package pjson;

/**
 * Treats all chars as one byte per char
 */
public abstract class JSONWriter{

    public abstract void startObj();
    public abstract void endObj();
    public abstract void startArr();
    public abstract void endArr();

    public abstract void writeString(String str);
    public abstract void writeString(char[] str, int from, int len);

    public abstract void writeInt(Integer i);
    public abstract void writeDouble(Double i);

    public abstract void writeFloat(Float i);

    public abstract void writeLong(Long i);

    public abstract void writeTrue();
    public abstract void writeFalse();

    public abstract void writeComma();

    public abstract void writeNull();


    public abstract void writeFieldName(String str);

    public static class NOOPJSONWriter extends JSONWriter{

        @Override
        public void startObj() {
        }

        @Override
        public void endObj() {
        }

        @Override
        public void startArr() {
        }

        @Override
        public void endArr() {
        }

        @Override
        public void writeString(String str) {
        }

        @Override
        public void writeString(char[] str, int from, int len) {

        }

        @Override
        public void writeInt(Integer i) {
        }

        @Override
        public void writeDouble(Double i) {

        }

        @Override
        public void writeFloat(Float i) {

        }

        @Override
        public void writeLong(Long i) {

        }

        @Override
        public void writeTrue() {
        }

        @Override
        public void writeFalse() {
        }

        @Override
        public void writeComma() {
        }

        @Override
        public void writeNull() {
        }

        @Override
        public void writeFieldName(String str) {
        }

        @Override
        public String toString(){
            return "";
        }
    }

    public static final class StringBuilderWriter extends JSONWriter{
        final StringBuilder buff = new StringBuilder(500);


        @Override
        public void startObj() {
            buff.append('{');
        }

        @Override
        public void endObj() {
            buff.append('}');
        }

        @Override
        public void startArr() {
            buff.append('[');
        }

        @Override
        public void endArr() {
            buff.append(']');
        }

        @Override
        public void writeString(String str) {
            buff.append('"').append(str).append('"');
        }

        @Override
        public void writeString(char[] str, int from, int len) {
            buff.append(str, from, len);
        }

        @Override
        public void writeInt(Integer i) {
            buff.append(String.valueOf(i.intValue()));
        }

        @Override
        public void writeDouble(Double i) {
            buff.append(String.valueOf(i.doubleValue()));
        }

        @Override
        public void writeFloat(Float i) {
            buff.append(String.valueOf(i.floatValue()));
        }
        @Override
        public void writeLong(Long i) {
            buff.append(String.valueOf(i.longValue()));
        }

        @Override
        public void writeTrue() {
            buff.append("true");
        }

        @Override
        public void writeFalse() {
            buff.append("false");
        }

        @Override
        public void writeComma() {
            buff.append(',');
        }

        @Override
        public void writeNull() {
            buff.append("null");
        }

        @Override
        public void writeFieldName(String str) {
            buff.append('"').append(str).append('"').append(':');
        }

        public String toString(){
            return buff.toString();
        }
    }
}
