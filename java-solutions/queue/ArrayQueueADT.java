package queue;

import java.util.Objects;

public class ArrayQueueADT {
/*
    Model:
        [a1, a2, a3, .... ,an]
        start = a1
        size = n

     Inv: ∀i: a[i] != null
     Imm: ∀i: a'[i] == a[i]
*/
    private int start = 0;
    private int size = 0;
    private Object[] elements = new Object[2];

    // Pred: element != null
    // Post: n' = n + 1 && ∀i: a'[i] == a[i] && a[n'] == element
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        if (queue.size == queue.elements.length) {
            final Object[] newElements = new Object[2 * queue.elements.length];
            if (queue.start == 0) {
                System.arraycopy(queue.elements, 0, newElements, 0, queue.elements.length);
                queue.elements = newElements;
            } else {
                System.arraycopy(queue.elements, queue.start, newElements, 0, queue.elements.length - queue.start);
                System.arraycopy(queue.elements, 0, newElements, queue.elements.length - queue.start, queue.start);
                queue.elements = newElements;
                queue.start = 0;
            }
        }
        Objects.requireNonNull(element);
        if (queue.elements.length - queue.start <= queue.size) {
            queue.elements[queue.size + queue.start - queue.elements.length] = element;
        } else {
            queue.elements[queue.size + queue.start] = element;
        }
        queue.size++;
    }

    // Pred: n > 0
    // Post: result == a[1] && n' == n && ∀i: a'[i] == a[i]
    public static Object element(final ArrayQueueADT queue) {
        assert queue.size > 0;
        return queue.elements[queue.start];
    }

    // Pred: n > 0
    // Post: result == a[1] && n' == n - 1 && Imm(n')
    public static Object dequeue(final ArrayQueueADT queue) {
        assert queue.size > 0;
        Object element = element(queue);
        queue.elements[queue.start] = null;
        queue.size--;
        if (queue.start == queue.elements.length - 1) {
            queue.start = 0;
        } else {
            queue.start++;
        }
        return element;
    }

    // Pred: true
    // Post: result == n && n' == n && Imm(n)
    public static int size(final ArrayQueueADT queue) {
        return queue.size;
    }

    // Pred: true
    // Post: result == (n == 0) && n' == n && Imm(n)
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pred: true
    // Post: n' == 0 && ∀i: a'[i] == null && Imm(n)
    public static void clear(final ArrayQueueADT queue) {
        queue.start = 0;
        queue.size = 0;
        queue.elements = new Object[2];
    }

    public static ArrayQueueADT create() {
        final ArrayQueueADT queue = new ArrayQueueADT();
        queue.elements = new Object[2];
        queue.start = 0;
        queue.size = 0;
        return queue;
    }

    // Pred: true
    // Post: result == "[a1, a2, a3, .... ,an]" && n' == n && Imm(n)
    public static String toStr(final ArrayQueueADT queue) {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < queue.size; i++) {
            int index;
            if (queue.start + i >= queue.elements.length) {
                index = queue.start + i - queue.elements.length;
            } else {
                index = queue.start + i;
            }
            sb.append(queue.elements[index]);
            if (i != queue.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
