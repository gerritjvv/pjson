package pjson;

import clojure.lang.Associative;
import clojure.lang.IMapEntry;
import clojure.lang.IPersistentCollection;
import clojure.lang.ISeq;

import java.util.Arrays;

/**
 */
public class JSONAssociative implements Associative{
    final private Object[] arr;
    final int len;

    public JSONAssociative(Object[] arr, int len){
        this.arr = arr;
        this.len = len;
    }

    @Override
    public boolean containsKey(Object key) {
        for(int i = 0; i < len; i=+2)
            if(arr[i].equals(key))
                return true;

        return false;
    }

    @Override
    public IMapEntry entryAt(Object key) {
        Object obj;
        for(int i = 0; i < len; i+=2) {
            obj = arr[i];
            if (obj.equals(key))
                return new JSONIMapEntry(obj, arr[i+1]);
        }
        return null;
    }

    @Override
    public Associative assoc(Object key, Object val) {
        int i = indexOf(key);
        if(i > -1){
            Object[] newArr = new Object[arr.length];
            System.arraycopy(arr, 0, newArr, 0, len);
            newArr[i+1] = val;
            return new JSONAssociative(newArr, len);
        }else{

            if(len+2 < arr.length){
                Object[] newArr = new Object[arr.length];
                System.arraycopy(arr, 0, newArr, 0, len);
                newArr[len] = key;
                newArr[len+2] = val;
                return new JSONAssociative(newArr, len + 2);
            }else{
                Object[] newArr = new Object[arr.length + 10];
                System.arraycopy(arr, 0, newArr, 0, len);
                newArr[len] = key;
                newArr[len+2] = val;
                return new JSONAssociative(newArr, len + 2);
            }
        }
    }

    public int indexOf(Object key){
        Object obj;
        for(int i = 0; i < len; i+=2) {
            obj = arr[i];
            if (obj.equals(key))
                return i;
        }
        return -1;
    }

    @Override
    public Object valAt(Object key) {
        System.out.println("have arry: " + Arrays.toString(arr));
        Object obj;
        for(int i = 0; i < len; i+=2) {
            obj = arr[i];
            if (obj.equals(key))
                return arr[i+1];
        }
        return null;
    }

    @Override
    public Object valAt(Object key, Object notFound) {
        Object v = valAt(key);
        return (v == null) ? notFound : v;
    }

    @Override
    public int count() {
        return len/2;
    }

    @Override
    public IPersistentCollection cons(Object o) {
        return null;
    }

    @Override
    public IPersistentCollection empty() {
        return new JSONAssociative(new Object[10], 0);
    }

    @Override
    public boolean equiv(Object o) {
        return false;
    }

    @Override
    public ISeq seq() {
        return null;
    }

    public final static class JSONAssocSEQ implements ISeq{

        private final IMapEntry[] arr;
        private final int len;
        private final int i;

        public JSONAssocSEQ(IMapEntry[] arr, int i, int len){
            this.arr = arr;
            this.len = len;
            this.i = i;
        }
        @Override
        public Object first() {
            return (len > 0) ? arr[i] : null;
        }

        @Override
        public ISeq next() {
            return (i < len) ? new JSONAssocSEQ(arr, i+1, len) : null;
        }

        @Override
        public ISeq more() {
            return next();
        }

        @Override
        public int count() {
            return len - i;
        }

        @Override
        public ISeq cons(Object o) {
            return null;
        }

        @Override
        public IPersistentCollection empty() {
            return null;
        }

        @Override
        public boolean equiv(Object o) {
            return false;
        }

        @Override
        public ISeq seq() {
            return this;
        }
    }
    public final static class JSONIMapEntry implements IMapEntry{

        private final Object key, val;

        public JSONIMapEntry(Object key, Object val){
            this.key = key;
            this.val = val;
        }

        @Override
        public final Object key() {
            return key;
        }

        @Override
        public final Object val() {
            return val;
        }

        @Override
        public final Object getKey() {
            return key;
        }

        @Override
        public final Object getValue() {
            return val;
        }

        @Override
        public Object setValue(Object value) {
            throw new RuntimeException("setValue is not supported");
        }
    }
}
