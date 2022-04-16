package pjson.key;

import clojure.lang.IFn;

public class ClojureFn implements KeyFn {

    private final IFn fn;

    public ClojureFn(IFn fn) {
        this.fn = fn;
    }

    @Override
    public Object convert(Object kw) {
        return fn.invoke(kw);
    }

    public static KeyFn create(Object fn) {
        if (fn instanceof KeyFn)
            return (KeyFn) fn;

        return new ClojureFn((IFn) fn);
    }
}
