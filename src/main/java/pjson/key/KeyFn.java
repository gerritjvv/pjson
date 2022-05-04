package pjson.key;


/**
 * Provide a way for users to customise how keys for maps are processed.
 * For example, clojure users might want to convert keys to keywords.
 * <p/>
 * If you're a clojure user, you don't need to reify this interface, the `pjson.core` namespace will do this for you.
 */
@FunctionalInterface
public interface KeyFn<String, R>{
    R convert(String kw);
}
