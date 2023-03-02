package queue;

class ArrayQueueModuleTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            ArrayQueueModule.enqueue("e" + i);
        }
        ArrayQueueModule.enqueue("ABOBA");
        ArrayQueueModule.enqueue("871w297e61");

        System.out.println(ArrayQueueModule.size());
        System.out.println(ArrayQueueModule.element());
        while(!ArrayQueueModule.isEmpty()) {
            System.out.println(ArrayQueueModule.dequeue());
            if (ArrayQueueModule.size() % 3 == 1) {
                System.out.println(ArrayQueueModule.element());
            }
        }
        System.out.println(ArrayQueueModule.size());
    }
}