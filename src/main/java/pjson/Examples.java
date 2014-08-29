package pjson;

import java.util.Map;

/**
 */
public class Examples {

    public static void testReadMessage(){

        byte[] msg_bts = "{\":a\": 1}".getBytes();

        Map<Object, Object> obj = (Map<Object, Object>) PJSON.defaultParse(StringUtil.DEFAULT_CHAR_SET, msg_bts);
        System.out.println(obj);
    }

    public static void main(String args[]){
        testReadMessage();

    }
}
