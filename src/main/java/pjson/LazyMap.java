package pjson;

import clojure.lang.*;
import pjson.key.KeyFn;
import pjson.key.StringKeyFn;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Takes a json string and only realized it when required, the toString methods do not require any json parsing.<br/>
 * Any null maps are treated as Empty MAPs.
 */
public class LazyMap extends APersistentMap implements ToJSONString {

    final char[] json;
    final int from, len;
    final AtomicBoolean realized = new AtomicBoolean(false);
    final KeyFn keyFn;

    APersistentMap map;

    public LazyMap(char[] json, int from, int len) {
        this(json, from, len, StringKeyFn.INSTANCE);
    }

    public LazyMap(char[] json, int from, int len, KeyFn keyFn){
        this.json = json;
        this.from = from;
        this.len = len;
        this.keyFn = keyFn;
    }

    public final int jsonFrom(){
        return from;
    }

    public final int jsonLen(){
        return len;
    }

    private final void realize(){
        if(!realized.getAndSet(true)){
            DefaultListener listener = new DefaultListener(keyFn);

            PJSON.lazyParse(json, from, from+len, listener);
            APersistentMap map = (APersistentMap)listener.getValue();
            this.map = (map == null) ? PersistentArrayMap.EMPTY : map;
        }
    }

    public final char[] json(){
        return json;
    }

    @Override
    public boolean containsKey(Object key) {
        realize();
        return map.containsKey(key);
    }

    @Override
    public IMapEntry entryAt(Object key) {
        realize();
        return map.entryAt(key);
    }

    @Override
    public IPersistentMap assoc(Object key, Object val) {
        realize();
        return map.assoc(key, val);
    }

    @Override
    public IPersistentMap assocEx(Object key, Object val) {
        realize();
        return map.assocEx(key, val);
    }

    @Override
    public IPersistentMap without(Object key) {
        realize();
        return map.without(key);
    }

    @Override
    public Object valAt(Object key) {
        realize();
        return map.valAt(key);
    }

    @Override
    public Object valAt(Object key, Object notFound) {
        realize();
        return map.valAt(key, notFound);
    }

    @Override
    public int count() {
        realize();
        return (map == null) ? 0 : map.count();
    }

    @Override
    public IPersistentCollection empty() {
        return PersistentArrayMap.EMPTY;
    }

    @Override
    public Iterator iterator() {
        realize();
        return (map == null) ? PersistentArrayMap.EMPTY.iterator() : map.iterator();
    }

    @Override
    public ISeq seq() {
        realize();
        return (map == null) ? null : map.seq();
    }

    @Override
    public String toString(){
        return StringUtil.fastToString(json, from, len);
    }

    public void toString(JSONWriter buff) {
        buff.writeObjLazyString(json, from, len);
    }
}

