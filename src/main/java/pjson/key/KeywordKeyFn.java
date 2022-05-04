package pjson.key;

import clojure.lang.Keyword;

public class KeywordKeyFn implements KeyFn<String, Keyword> {

    public static final KeywordKeyFn INSTANCE = new KeywordKeyFn();

    @Override
    public Keyword convert(String kw) {
        if (kw == null)
            return null;

        return Keyword.intern(kw);
    }

}
