package pjson;

/**
 * Receive events from the PJSON parse method
 */
public abstract class JSONListener {

    public abstract void string(String str);
    public abstract void number(Integer i);
    public abstract void number(Long i);

    public abstract void objectStart();
    public abstract void objectEnd();

    public abstract void arrStart();
    public abstract void arrEnd();

}
