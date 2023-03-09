package queue;

import java.util.Objects;

public abstract class AbstractQueue implements Queue {

    protected int size;
    protected int start;

    public void enqueue(Object element) {
        Objects.requireNonNull(element);
        enqueueImpl(element);
        size++;
    }

    protected abstract void enqueueImpl(Object element);

    public Object element() {
        return elementImpl();
    }

    protected abstract Object elementImpl();

    public Object dequeue() {
        assert size > 0;
        Object element = element();
        dequeueImpl();
        size--;
        return element;
    }

    protected abstract void dequeueImpl();

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        start = 0;
        clearImpl();
    }
    protected abstract void clearImpl();

    public int count(Object element) {
        int res = 0;
        for (int i = 0; i < size; i++) {
            if (element.equals(element())) {
                res++;
            }
            enqueue(element());
            dequeue();
        }
        return res;
    }
}
