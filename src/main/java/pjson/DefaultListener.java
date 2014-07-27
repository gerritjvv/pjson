package pjson;

import java.util.ArrayList;
import java.util.List;

/**
 * Return the JSON data as: Objects == Map, Array == Vector, keys and values as String.
 */
public final class DefaultListener extends JSONListener{

    private final List<ValueContainer> stack = new ArrayList<ValueContainer>();
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
    public final void objectStart() {
       if(current != null)
           stack.add(current);

        current = new ValueContainer.ObjectContainer();
    }

    @Override
    public final void objectEnd() {
        ValueContainer parent = pop();
        if(parent != null) {
            parent.append(current.getValue());
            current = parent;
        }
    }

    @Override
    public final void arrStart() {
        if(current != null)
            stack.add(current);

        current = new ValueContainer.ArrayContainer();
    }

    @Override
    public final void arrEnd() {
        ValueContainer parent = pop();
        if(parent != null) {
            parent.append(current);
            current = parent;
        }
    }

    private final ValueContainer pop(){
        return (stack.size() > 0)? stack.remove(stack.size() - 1) : null;
    }

    public final Object getValue(){
        return current.getValue();
    }
}
