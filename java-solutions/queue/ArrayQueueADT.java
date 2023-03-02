package queue;

import java.util.Objects;

public class ArrayQueueADT {
/*
    Model:
        [a1, a2, a3, .... ,an]
        start = a1
        end = an
        size = n

     Inv: ∀i: a[i] != null
     Imm: ∀i: a'[i] == a[i]
*/
    private int start = 0;
    private int end = 0;
    private int size = 0;
    private Object[] elements = new Object[2];

    // Pred: element != null
    // Post: n' = n + 1 && ∀i: a'[i] == a[i] && a[n'] == element
    public static void enqueue(final ArrayQueueADT queue, final Object element) {
        if (queue.size == queue.elements.length) {
            final Object[] newElements = new Object[2 * queue.elements.length];
            if (queue.start < queue.end) {
                System.arraycopy(queue.elements, 0, newElements, 0, queue.elements.length);
                queue.elements = newElements;
            } else {
                System.arraycopy(queue.elements, queue.start, newElements, 0, queue.elements.length - queue.start);
                System.arraycopy(queue.elements, 0, newElements, queue.elements.length - queue.start, queue.end);
                queue.elements = newElements;
                queue.start = 0;
                queue.end = queue.size;
            }
        }
        if (queue.end == queue.elements.length) {
            queue.end = 0;
        }
        Objects.requireNonNull(element);
        queue.elements[queue.end] = element;
        queue.end++;
        queue.size++;
    }

    // Pred: n > 0
    // Post: result == a[1]
    public static Object element(final ArrayQueueADT queue) {
        return queue.elements[queue.start];
    }

    // Pred: n > 0
    // Post: result == a[1] && n' == n - 1 && ∀i: a'[i] == a[i + 1]
    public static Object dequeue(final ArrayQueueADT queue) {
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
    // Post: result == n && n' == n && ∀i: a'[i] == a[i]
    public static int size(final ArrayQueueADT queue) {
        return queue.size;
    }

    // Pred: true
    // Post: result == (n == 0) && n' == n && ∀i: a'[i] == a[i]
    public static boolean isEmpty(final ArrayQueueADT queue) {
        return queue.size == 0;
    }

    // Pred: true
    // Post: n == 0 && ∀i: a'[i] == null
    public static void clear(final ArrayQueueADT queue) {
        queue.start = 0;
        queue.end = 0;
        queue.size = 0;
        queue.elements = new Object[2];
    }

    public static ArrayQueueADT create() {
        final ArrayQueueADT queue = new ArrayQueueADT();
        queue.elements = new Object[2];
        queue.start = 0;
        queue.end = 0;
        queue.size = 0;
        return queue;
    }
}
