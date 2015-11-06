package pjson;

import java.math.BigInteger;

/**
 * Receive events from the PJSON parse method
 */
public abstract class JSONListener {

    public abstract void string(String str);
    public abstract void number(Integer i);
    public abstract void number(Long i);
    public abstract void number(Double i);
    public abstract void number(Boolean i);

    public abstract void bigInteger(BigInteger i);

    public abstract void objectStart();
    public abstract void objectEnd();

    public abstract void arrStart();
    public abstract void arrEnd();

    public abstract void lazyObject(char[] json, int from, int end);
    public abstract void lazyArr(char[] json, int from, int end);

}
