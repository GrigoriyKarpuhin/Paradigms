package search;

public class BinarySearch {
    //Pred: x ∈ int && a ∈ int[] && ∀i: 0 <= i < a.length -> a[i] ∈ int
    //Post: result: a[result] <= x < a[result - 1]
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            a[i - 1] = Integer.parseInt(args[i]);
        }
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        if (sum % 2 == 0) {
            System.out.println(recursiveBinarySearch(x, a, 0, a.length - 1));
        } else {
            System.out.println(iterativeBinarySearch(x, a));
        }
    }

    // Pred: ∀i: 0 <= i < a.length -> a[i] >= a[i + 1] && left < right
    // Post: result: a[result] <= x < a[result - 1]
    private static int recursiveBinarySearch(int x, int[] a, int left, int right) {
        if (left > right) {
            //left > right -> left == right + 1 == result
            return left;
        }
        //left <= right && mid == (left + right) / 2 -> left <= mid <= right
        int mid = (left + right) / 2;
        if (x < a[mid]) {
            //x < a[mid]
            return recursiveBinarySearch(x, a, mid + 1, right);
            // left' == (left + right) / 2 + 1 && left' <= result
        } else {
            //x >= a[mid]
            return recursiveBinarySearch(x, a, left, mid - 1);
            // right' == (left + right) / 2 - 1 && result < right'
        }
    }

    // Pred: I && ∀i: 0 <= i < a.length -> a[i] >= a[i + 1]
    // Post: result: a[result] <= x < a[result - 1]
    private static int iterativeBinarySearch(int x, int[] a) {
        // 0 <= 0 <= result <= a.length - 1
        int left = 0;
        // 0 <= left <= result <= a.length <= a.length
        int right = a.length - 1;
        // I: 0 <= left <= result <= right + 1 <= a.length
        while (left <= right) {
            // I && left <= right && mid == (left + right) / 2 -> left <= mid <= right
            int mid = (left + right) / 2;
            if (x < a[mid]) {
                // I && left <= right && x < a[mid]
                left = mid + 1;
                // left' == (left + right) / 2 + 1 && left' <= result
            } else {
                // I && left <= right && x >= a[mid]
                right = mid - 1;
                // right' == (left + right) / 2 - 1 && result < right'
            }
            // I
        }
        // I && left > right -> left == right + 1 == result
        return left;
    }
}