package pjson;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Return the JSON data as: Objects == Map, Array == Vector, keys and values as String.
 */
public final class DefaultListener extends JSONListener{

    //private final List<ValueContainer> stack = new ArrayList<ValueContainer>();
    private ValueContainer[] stack = new ValueContainer[10];
    private int stackPointer = 0;

    private ValueContainer current;

    @Override
    public final void string(String val) {
        current.append(val);
    }

    @Override
    public final void number(Integer val) {
        current.append(val);
    }

    @Override
    public final void number(Long val) {
        current.append(val);
    }

    @Override
    public final void number(Double val) {
        current.append(val);
    }

    @Override
    public final void number(Boolean val) {
        current.append(val);
    }

    @Override
    public final void bigInteger(BigInteger val) {
        current.append(val);
    }

    @Override
    public final void bigDecimal(BigDecimal val) { current.append(val); }

    @Override
    public final void objectStart() {
       if(current != null)
           push(current);

       current = new ValueContainer.AssocObjContainer();
    }

    @Override
    public final void objectEnd() {
        popValue();
    }

    @Override
    public final void arrStart() {
        if(current != null)
            push(current);

        current = new ValueContainer.ArrayContainer();
    }

    @Override
    public final void arrEnd() {
        popValue();
    }

    @Override
    public void lazyObject(char[] json, int from, int end) {
        LazyMap map = new LazyMap(json, from, end - from);

        if(current != null)
            current.append(map);
    }

    @Override
    public void lazyArr(char[] json, int from, int end) {
        LazyVector v = new LazyVector(json, from, end - from);
        if(current != null)
            current.append(v);
    }

    private final void popValue(){
        final ValueContainer parent = pop();
        if(parent != null) {
            parent.append(current.getValue());
            current = parent;
        }
    }

    private final ValueContainer pop(){
       return (stackPointer > 0) ? stack[--stackPointer] : stack[0];
    }

    private final void push(final ValueContainer container){
        if(stackPointer < stack.length)
            stack[stackPointer++] = container;
        else{
            //grow stack
            final ValueContainer[] newStack = new ValueContainer[stack.length + 5];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            stack = newStack;
            stack[stackPointer++] = container;
        }
    }

    public final Object getValue(){
        return (current == null) ? null : current.getValue();
    }

}
