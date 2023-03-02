package queue;

import java.util.Objects;

public class ArrayQueue {

    /*
    Model:
        [a1, a2, a3, .... ,an]
        start = a1
        end = an
        size = n

        Inv: ∀i: a[i] != null
        Imm: ∀i: a'[i] == a[i]
     */
    private int start;
    private int end;
    private int size;
    private Object[] elements;

    {
        this.elements = new Object[2];
        this.end = 0;
        this.size = 0;
        this.start = 0;
    }

    // Pred: element != null
    // Post: n' = n + 1 && ∀i: a'[i] == a[i] && a[n'] == element
    public void enqueue(final Object element) {
        if (size == elements.length) {
            final Object[] newElements = new Object[2 * elements.length];
            if (start < end) {
                System.arraycopy(elements, 0, newElements, 0, elements.length);
                elements = newElements;
            } else {
                System.arraycopy(elements, start, newElements, 0, elements.length - start);
                System.arraycopy(elements, 0, newElements, elements.length - start, end);
                elements = newElements;
                start = 0;
                end = size;
            }
        }
        if (end == elements.length) {
            end = 0;
        }
        Objects.requireNonNull(element);
        elements[end] = element;
        end++;
        size++;
    }

    // Pred: n > 0
    // Post: result == a[1]
    public Object element() {
        return elements[start];
    }

    // Pred: n > 0
    // Post: result == a[1] && n' == n - 1 && ∀i: a'[i] == a[i + 1]
    public Object dequeue() {
        Object element = element();
        elements[start] = null;
        size--;
        if (start == elements.length - 1) {
            start = 0;
        } else {
            start++;
        }
        return element;
    }

    // Pred: true
    // Post: result == n && n == n' && ∀i: a'[i] == a[i]
    public int size() {
        return this.size;
    }

    // Pred: true
    // Post: result == (n == 0) && n == n' && ∀i: a'[i] == a[i]
    public boolean isEmpty() {
        return size == 0;
    }

    // Pred: true
    // Post: n == 0 && ∀i: a'[i] == null
    public void clear() {
        start = 0;
        end = 0;
        size = 0;
        elements = new Object[2];
    }

    //Pred: true
    //Post: result == "[a1, a2, a3, .... ,an]" && n == n' && ∀i: a'[i] == a[i]
    public String toStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index = (start + i) % elements.length;
            sb.append(elements[index]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
