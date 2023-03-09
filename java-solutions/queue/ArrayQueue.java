package queue;

public class ArrayQueue extends AbstractQueue {

    /*
    Model:
        [a1, a2, a3, .... ,an]
        start = a1
        size = n

        Inv: ∀i: a[i] != null
        Imm: ∀i: a'[i] == a[i]
     */
    protected Object[] elements;

    {
        this.elements = new Object[2];
        this.size = 0;
        this.start = 0;
    }

    @Override
    protected void enqueueImpl(Object element) {
        if (size == elements.length) {
            Object[] newElements = new Object[2 * elements.length];
            if (start == 0) {
                System.arraycopy(elements, 0, newElements, 0, elements.length);
            } else {
                System.arraycopy(elements, start, newElements, 0, elements.length - start);
                System.arraycopy(elements, 0, newElements, elements.length - start, start);
                start = 0;
            }
            elements = newElements;
        }
        if (elements.length - start <= size) {
            elements[size + start - elements.length] = element;
        } else {
            elements[size + start] = element;
        }
    }

    @Override
    protected Object elementImpl() {
        return elements[start];
    }

    @Override
    protected void dequeueImpl() {
        elements[start] = null;
        if (start == elements.length - 1) {
            start = 0;
        } else {
            start++;
        }
    }

    @Override
    protected void clearImpl() {
        elements = new Object[2];
    }

    //Pred: true
    //Post: result == "[a1, a2, a3, .... ,an]" && Imm(n) && n' == n
    public String toStr() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            int index;
            if (start + i >= elements.length) {
                index = start + i - elements.length;
            } else {
                index = start + i;
            }
            sb.append(elements[index]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
