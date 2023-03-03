package search;

public class BinarySearchShift {
    //Pred: a ∈ int[] && ∀i: 0 <= i < a.length -> a[i] ∈ int
    //Post: result: a.get(result - 1) < a.get(result)
    public static void main(String[] args) {
        int[] a = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        if (sum % 2 == 0) {
            System.out.println(recursiveBinarySearch(a, 0, a.length - 1));
        } else {
            System.out.println(iterativeBinarySearch(a));
        }
    }

    // Pred: I && ∀i: 0 <= i < k -> a[i] > a[i + 1] && ∀i: k <= i < a.length -> a[i] > a[i + 1] && a[k - 1] < a[k]
    // Post: result: a.get(result - 1) < a.get(result)
    private static int iterativeBinarySearch(int[] a) {
        // 0 <= 0 <= result <= a.length - 1
        int left = 0;
        // 0 <= left <= result <= a.length - 1
        int right = a.length - 1;
        // I: 0 <= left <= result <= right <= a.length - 1
        while (left < right) {
            // I && left < right && mid == (left + right) / 2 -> left <= mid < right
            int mid = (left + right) / 2;
            if (a[mid] < a[right]) {
                // I && left < right && a[mid] < a[right]
                left = mid + 1;
                // left' == (left + right) / 2 + 1 && left' <= result
            } else {
                // I && left < right && a[mid] > a[right]
                right = mid;
                // right' == (left + right) / 2 - 1 && result <= right'
            }
            // I
        }
        // I && left == right -> left == result
        return left;
    }

    // Pred: ∀i: 0 <= i < k -> a[i] > a[i + 1] && ∀i: k <= i < a.length -> a[i] > a[i + 1] && a[k - 1] < a[k] && left < right
    // Post: result: a.get(result - 1) < a.get(result)
    private static int recursiveBinarySearch(int[] a, int left, int right) {
        if (left < right) {
            //left < right && mid == (left + right) / 2 -> left <= mid < right
            int mid = (left + right) / 2;
            if (a[mid] < a[right]) {
                //left < right && a[mid] < a[right]
                return recursiveBinarySearch(a, mid + 1, right);
                // left' == (left + right) / 2 + 1 && left' <= result
            } else {
                //left < right && a[mid] > a[right]
                return recursiveBinarySearch(a, left, mid);
                // right' == (left + right) / 2 - 1 && result <= right'
            }
        }
        // left == right -> left == result
        return left;
    }
}
