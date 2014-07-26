package pjson;

import clojure.lang.*;

import java.util.ArrayList;
import java.util.List;

/**
 */
public abstract class ValueContainer {

    public abstract void append(Object val);
    public abstract Object getValue();

    public static final class ObjectContainer extends ValueContainer{
        //private ITransientMap m = PersistentArrayMap.EMPTY.asTransient();
        private IPersistentMap m = PersistentArrayMap.EMPTY;
        //private List<Object> arr = new ArrayList<Object>();

        private Object k = null;

        @Override
        public final void append(Object val) {
            if(k != null) {
                m = m.assoc(k, val);
                k = null;
            }else
                k = val;
        }

        @Override
        public final Object getValue(){
            return m;
        }

    }

    public static final class ArrayContainer extends ValueContainer{
        private ITransientCollection v = PersistentVector.EMPTY.asTransient();

        @Override
        public final void append(Object val) {
            v = v.conj(val);
        }

        @Override
        public final Object getValue() {
            return v.persistent();
        }
    }

}
