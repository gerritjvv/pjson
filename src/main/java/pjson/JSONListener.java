package pjson;

/**
 * Receive events from the PJSON parse method
 */
public abstract class JSONListener {

    public abstract void string(String str);
    public abstract void number(int i);
    public abstract void number(long i);

    public abstract void objectStart();
    public abstract void objectEnd();

    public abstract void arrStart();
    public abstract void arrEnd();

}
