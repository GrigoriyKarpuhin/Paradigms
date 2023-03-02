package queue;

class MyArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue q1 = new ArrayQueue();
        ArrayQueue q2 = new ArrayQueue();
        for (int i = 0; i < 5; i++) {
            q1.enqueue("q1_e" + i);
            q2.enqueue("q2_e" + i);
        }
        dumpQueue(q1);
        dumpQueue(q2);
    }

    private static void dumpQueue(ArrayQueue queue) {
        while(!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}