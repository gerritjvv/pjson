package pjson;

import java.nio.charset.Charset;

/**
 * Fast simple json parser.<br/>
 * Note that numbers are not converted to any Java number and are returned as java Strings.<br/>
 * This improves performs but has the side effect that you need to parse any number data before treating it as an actual number.
 */
public final class PJSON {


    private static final char OBJECT_START = '{';
    private static final char OBJECT_END = '}';
    private static final char ARR_START = '[';
    private static final char ARR_END = ']';
    private static final int STR_MARK = '\"';
    private static final char STR_COMMA = ',';
    private static final char STR_ESCAPE = '\\';
    private static final char STR_SPACE = ' ';

    private static final char STR_DOT = '.';
    private static final char STR_T = 'T';
    private static final char STR_F = 'F';
    private static final char STR_t = 't';
    private static final char STR_f = 'f';

    private static final char STR_COL = ':';

    public static final void parse(final Charset charset, final byte[] bts, final int start, final int len, final JSONListener events){
        //final char chars[] = StringUtil.decode(bts, start, len);

         final char[] chars = StringUtil.toCharArrayFromBytes(bts, charset, start, len);
        parse(chars, 0, chars.length, events);
    }
    /**
     * Parses the byte array for json data.
     * @param bts byte array
     * @param start start offset
     * @param end the end index
     * @param events JSONListener, all events will be sent to this instance.
     */
    public static final int parse(final char[] bts, final int start, final int end, final JSONListener events){
        final int btsLen = end;
        boolean inString = false;
        char bt;
        int i;
        //1.a: raw iteration with CharArrayTool.getchar Execution time mean : 108.188995 ms, 112.338543 ms
        //1.b: using char array lookup Execution time mean : 111.391841 ms
        //2.a: overhead of calling object.start and object end 586.818982 ms
        //2.b: overhead instead of ArrayList stack using ValueContainer parent reference 674.102807 ms, 673.025107 ms
        //2.c: using stack object array 545.142249 ms
        //3.a: what is overhead from 2.c when ArrayList.toArray() is not called 519.697907 ms
        //3.b try own implementation of AssocObjContainer 519.500857 ms, this is only way not to call toArray.
        //3.c use ArrayList in AssocObjContainer instead of Object[] array. 516.218482 ms
        //3.d add arrayList full stack 513.968832 ms

        //Conclusions so far
        //  Use CharArrayTool for iteration, use AssocObjContainer, use Object[] for stack and grow if full

        //second round: testing base: 516.499957 ms
        //4.a overhead of case '"' 569.630274
        //4.b from 4.a with CharArrayTool.endOfString(bts, i+1, end); overhead is 656.135840 ms
        //4.c before 4.a with 4.b inside of case '{' without case '"' 577.122282 ms
        //4.d from 4.c with if else rather than switch 398.176798 ms

        //Conclusions so far
        //for short conditions use if else if rather than switch.
        //5.a from 4.d add another else '[', else ']' 467.269732 ms
        //5.b from 4.d add check for '}' inside if(bt == '{') and change outer check '}' for ']' 464.401121 ms
        //5.c add indexOf ':' 481.102465 ms
        //5.d add recursive call to parse with 4 elseif clauses in inner for loop 335.352232 ms 355.177432
        //5.e factor 5.d into a method 371.835232 ms
        //5.f add indexOf '"' before endOfString 339.316632 ms
        //conclusion: ??, fast searches lessen the if branches and is faster than multiple branches.

        //6.a add String key creation 399.380198 ms 396.857043 ms
        //6.b add String value creation 577.415732 ms
        //6.c add JSONAssociative 535.606182 ms;

        for(i = start; i < btsLen; i++){

            bt = CharArrayTool.getChar(bts, i);
            if(bt == '{'){
                events.objectStart();

                int strStart = CharArrayTool.indexOf(bts, i+1, end, '"') + 1;
                int idx = CharArrayTool.endOfString(bts, strStart, end);
                //create string here
                events.string(StringUtil.fastToString(bts, strStart, idx - strStart));
                idx = CharArrayTool.indexOf(bts, idx+1, end, ':');
                idx++;

                for(; idx < btsLen; idx++){
                    bt = CharArrayTool.getChar(bts, idx);
                    if(bt == '}') {
                        events.objectEnd();
                        return idx++;
                    }else if(bt == '"'){
                        final int strStart2 = idx + 1;
                        idx = CharArrayTool.endOfString(bts, strStart2, end);
                        events.string(StringUtil.fastToString(bts, strStart2, idx-strStart2));
                        idx++;
                    }else if(Character.isDigit(bt)) {

                    }else if(bt != ',' && bt != ' '){

                        idx = parse(bts, idx, btsLen, events);

                    }
                }

                return idx;

            }else if(bt == '['){
                //events.objectEnd();


            }
        }

        return i;
    }

    /**
     * Parse the whole byte array bts.
     * @param bts
     * @return Object Map or Collection.
     */
    public static final Object defaultParse(final Charset charset, final byte[] bts){
        final DefaultListener list = new DefaultListener();
        parse(charset, bts, 0, bts.length, list);
        return list.getValue();
    }

    /**
     * Parse the bts byte array from offset start and to < len.
     * @param bts
     * @param start
     * @param len
     * @return Object Map or Collection
     */
    public static final Object defaultParse(final Charset charset, final byte[] bts, final int start, final int len){
        final DefaultListener list = new DefaultListener();
        parse(charset, bts, start, len, list);
        return list.getValue();
    }

}
