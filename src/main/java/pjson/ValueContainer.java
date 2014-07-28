package pjson;

import clojure.lang.*;

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
            return new PersistentArrayMap(arr.toArray());
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
            v.add(val);
        }

        @Override
        public final Object getValue() {
            return LazilyPersistentVector.createOwning(v.toArray());
        }
    }

}
