package pjson;

import clojure.lang.LazilyPersistentVector;
import clojure.lang.PersistentArrayMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Object container that allows parsed values to be appended to the contains.
 * Two types of containers are: Vector/Array(s) and Object/Map(s).
 */
public abstract class ValueContainer {

    public abstract void append(Object val);
    public abstract Object getValue();
    public abstract void clear();

    public static final class ObjectContainer extends ValueContainer{

        private final List<Object> arr = new ArrayList<Object>();
        private Object[] objs = new Object[10];
        private Object k = null;

        @Override
        public void clear(){
            arr.clear();
        }

        @Override
        public final void append(Object val) {
            if(k != null) {
                arr.add(k);
                arr.add(val);
                k = null;
            }else
                k = val;
        }

        @Override
        public final Object getValue(){
            return new PersistentArrayMap(objs);
        }

    }

    public static final class AssocObjContainer extends ValueContainer{

        private Object[] arr = new Object[20];
        private int i = 0;
        private Object k = null;

        @Override
        public void clear(){
        }

        @Override
        public final void append(Object val) {
            if(k != null) {
                if(i+2 > arr.length){
                    //grow array
                    Object[] newArr = new Object[arr.length + 10];
                    System.arraycopy(arr, 0, newArr, 0, i);
                    arr = newArr;
                }
                arr[i++] = k;
                arr[i++] = val;
                k = null;
            }else
                k = val;
        }

        @Override
        public final Object getValue(){
            return new JSONAssociative(arr, i);
        }

    }
    public static final class ArrayContainer extends ValueContainer{
        private List<Object> v = new ArrayList<Object>();

        @Override
        public void clear(){
            v.clear();
        }

        @Override
        public final void append(Object val) {

            if(val instanceof Number)
                v.add(val);
        }

        @Override
        public final Object getValue() {
            return LazilyPersistentVector.createOwning(v.toArray());
        }
    }

}
