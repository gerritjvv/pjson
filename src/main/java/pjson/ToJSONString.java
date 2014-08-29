package pjson;

/**
 * Interface to toString JSON creation that supports passing in a StringBuilder
 */
public interface ToJSONString {
   public String toString();
   public void toString(StringBuilder buff);
}
