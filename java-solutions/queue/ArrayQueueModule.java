package queue;

import java.util.Objects;

public class ArrayQueueModule {

    /*
    Model:
        [a1, a2, a3, .... ,an]
        start = a1
        size = n

     Inv: ∀i: a[i] != null
     Imm: ∀i: a'[i] == a[i]
     */
    private static int start = 0;
    private static int size = 0;
    private static Object[] elements = new Object[2];

    // Pred: element != null
    // Post: n' = n + 1 && Imm(n) && a[n'] == element
    public static void enqueue(final Object element) {
        if (size == elements.length) {
            final Object[] newElements = new Object[2 * elements.length];
            if (start == 0) {
                System.arraycopy(elements, 0, newElements, 0, elements.length);
            } else {
                System.arraycopy(elements, start, newElements, 0, elements.length - start);
                System.arraycopy(elements, 0, newElements, elements.length - start, start);
            }
            elements = newElements;
            start = 0;
        }
        Objects.requireNonNull(element);
        if (elements.length - start <= size) {
            elements[size + start - elements.length] = element;
        } else {
            elements[size + start] = element;
        }
        size++;
    }

    // Pred: n > 0
    // Post: result == a[1] && n' == n && Imm(n)
    public static Object element() {
        assert size > 0;
        return elements[start];
    }

    // Pred: n > 0
    // Post: result == a[1] && n' == n - 1 && ∀i: Imm(n')
    public static Object dequeue() {
        assert size > 0;
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
    // Post: result == n && n' = n && Imm(n)
    public static int size() {
        return size;
    }

    // Pred: true
    // Post: result == (n == 0) && n' == n && Imm(n)
    public static boolean isEmpty() {
        return size == 0;
    }

    // Pred: true
    // Post: n' == 0 && ∀i: a'[i] == null && Imm(n)
    public static void clear() {
        start = 0;
        size = 0;
        elements = new Object[2];
    }

    // Pred: true
    // Post: result == "[a1, a2, a3, .... ,an]" && n' == n && Imm(n)
    public static String toStr() {
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
