package queue;

import static queue.ArrayQueueADT.*;
class ArrayQueueADTTest {
    public static void main(String[] args){
        ArrayQueueADT q1 = create();
        ArrayQueueADT q2 = create();
        for (int i = 0; i < 5; i++) {
            enqueue(q1,"q1_e" + i);
            enqueue(q2,"q2_e" + i);
        }
        dumpQueue(q1);
        dumpQueue(q2);
    }
    private static void dumpQueue(ArrayQueueADT queue) {
        while(!isEmpty(queue)) {
            System.out.println(dequeue(queue));
        }
    }
}