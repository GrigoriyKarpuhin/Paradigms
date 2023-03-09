package queue;

/*
    Model:
        [a1, a2, a3, .... ,an]
        start = a1
        size = n

        Inv: i >= 0 && ∀i: a[i] != null
        Imm: ∀i: a'[i] == a[i]
     */
public interface Queue {

    // Pred: element != null
    // Post: n' = n + 1 && Imm(n) && a[n'] == element
    void enqueue(Object element);

    // Pred: n > 0
    // Post: result == a[1] && n' == n && Imm(n)
    Object element();

    // Pred: n > 0
    // Post: result == a[1] && n' == n - 1 && Imm(n')
    Object dequeue();

    // Pred: true
    // Post: result == n && Imm(n)
    int size();

    // Pred: true
    // Post: result == (n == 0) && Imm(n)
    boolean isEmpty();

    // Pred: true
    // Post: n' == 0 && ∀i: a'[i] == null && Imm(n)
    void clear();
}
