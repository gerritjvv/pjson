package pjson.key;

public class StringKeyFn implements KeyFn<String, String>{

    public static final StringKeyFn INSTANCE = new StringKeyFn();

    @Override
    public String convert(String kw) {
        return kw;
    }

}
