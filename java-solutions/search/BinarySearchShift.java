package search;

import java.util.ArrayList;

public class BinarySearchShift {
    //Pred: a ∈ ArrayList<Integer> && ∀i: 0 <= i < a.size() -> a.get(i) ∈ int
    //Post: result: a.get(result - 1) < a.get(result)
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            a.add(Integer.parseInt(args[i]));
        }
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i);
        }
        if (sum % 2 == 0) {
            System.out.println(recursiveBinarySearch(a, 0, a.size() - 1));
        } else {
            System.out.println(iterativeBinarySearch(a));
       }
    }
    // Pred: I && ∀i: 0 <= i < k -> a.get(i) > a.get(i + 1) && ∀i: k <= i < a.size() -> a.get(i) > a.get(i + 1) && a.get(k - 1) < a.get(k)
    // Post: result: a.get(result - 1) < a.get(result)
    private static int iterativeBinarySearch(ArrayList<Integer> a) {
        // 0 <= 0 <= result <= a.size() - 1
        int left = 0;
        // 0 <= left <= result <= a.size() - 1
        int right = a.size() - 1;
        // I: 0 <= left <= result <= right <= a.size() - 1
        while (left < right) {
            // I && left < right && mid == (left + right) / 2 -> left <= mid < right
            int mid = (left + right) / 2;
            if (a.get(mid) < a.get(right)) {
                // I && left < right && a.get(mid) < a.get(right)
                left = mid + 1;
                // left' == (left + right) / 2 + 1 && left' <= result
            } else {
                // I && left < right && a.get(mid) > a.get(right)
                right = mid;
                // right' == (left + right) / 2 - 1 && result <= right'
            }
            // I
        }
        // I && left == right -> left == result
        return left;
    }

    // Pred: ∀i: 0 <= i < k -> a.get(i) > a.get(i + 1) && ∀i: k <= i < a.size() -> a.get(i) > a.get(i + 1) && a.get(k - 1) < a.get(k)
    // Post: result: a.get(result - 1) < a.get(result)
    private static int recursiveBinarySearch(ArrayList<Integer> a, int left, int right) {
        if (left < right) {
            //left < right && mid == (left + right) / 2 -> left <= mid < right
            int mid = (left + right) / 2;
            if (a.get(mid) < a.get(right)) {
                //left < right && a.get(mid) < a.get(right)
                return recursiveBinarySearch(a, mid + 1, right);
                // left' == (left + right) / 2 + 1 && left' <= result
            } else {
                //left < right && a.get(mid) > a.get(right)
                return recursiveBinarySearch(a, left, mid);
                // right' == (left + right) / 2 - 1 && result <= right'
            }
        }
        // left == right -> left == result
        return left;
    }
}
