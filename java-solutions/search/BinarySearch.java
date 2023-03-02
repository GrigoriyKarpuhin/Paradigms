package search;

import java.util.ArrayList;

public class BinarySearch {
    //Pred: x ∈ int && a ∈ ArrayList<Integer> && ∀i: 0 <= i < a.size() -> a.get(i) ∈ int
    //Post: result: a.get(result) <= x < a.get(result - 1)
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 1; i < args.length; i++) {
            a.add(Integer.parseInt(args[i]));
        }
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
        }
        if (sum % 2 == 0) {
            System.out.println(recursiveBinarySearch(x, a, 0, a.size() - 1));
        } else {
            System.out.println(iterativeBinarySearch(x, a));
        }
    }

    // Pred: ∀i: 0 <= i < a.size() -> a.get(i) >= a.get(i + 1)
    // Post: result: a.get(result) <= x < a.get(result - 1)
    private static int recursiveBinarySearch(int x, ArrayList<Integer> a, int left, int right) {
        if (left > right) {
            //left > right -> left == right + 1 == result
            return left;
        }
        //left <= right && mid == (left + right) / 2 -> left <= mid <= right
        int mid = (left + right) / 2;
        if (x < a.get(mid)) {
            //x < a.get(mid)
            return recursiveBinarySearch(x, a, mid + 1, right);
            // left' == (left + right) / 2 + 1 && left' <= result
        } else {
            //x >= a.get(mid)
            return recursiveBinarySearch(x, a, left, mid - 1);
            // right' == (left + right) / 2 - 1 && result < right'
        }
    }

    // Pred: I && ∀i: 0 <= i < a.size() -> a.get(i) >= a.get(i + 1)
    // Post: result: a.get(result) <= x < a.get(result - 1)
    private static int iterativeBinarySearch(int x, ArrayList<Integer> a) {
        // 0 <= 0 <= result <= a.size() - 1
        int left = 0;
        // 0 <= left <= result <= a.size() <= a.size()
        int right = a.size() - 1;
        // I: 0 <= left <= result <= right + 1 <= a.size()
        while (left <= right) {
            // I && left <= right && mid == (left + right) / 2 -> left <= mid <= right
            int mid = (left + right) / 2;
            if (x < a.get(mid)) {
                // I && left <= right && x < a.get(mid)
                left = mid + 1;
                // left' == (left + right) / 2 + 1 && left' <= result
            } else {
                // I && left <= right && x >= a.get(mid)
                right = mid - 1;
                // right' == (left + right) / 2 - 1 && result < right'
            }
            // I
        }
        // I && left > right -> left == right + 1 == result
        return left;
    }
}
