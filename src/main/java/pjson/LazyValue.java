package pjson;

import clojure.lang.Delay;
import clojure.lang.IFn;

/**
 */
public final class LazyValue {

    private final Delay delay;

    public LazyValue(IFn fn){
        this.delay = new Delay(fn);
    }

    public String toString(){
        return delay.deref().toString();
    }
}
