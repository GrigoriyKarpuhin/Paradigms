package queue;

import java.util.LinkedList;

public class LinkedQueue extends AbstractQueue{
    protected LinkedList<Object> elements;

    {
        this.elements = new LinkedList<>();
        this.size = 0;
        this.start = 0;
    }
    @Override
    protected void enqueueImpl(Object element) {
        elements.add(element);
    }

    @Override
    protected Object elementImpl() {
        return elements.get(start);
    }

    @Override
    protected void dequeueImpl() {
        elements.remove(start);
    }

    @Override
    protected void clearImpl() {
        elements = new LinkedList<>();
    }
}
