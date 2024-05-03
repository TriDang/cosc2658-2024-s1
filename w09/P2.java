package w09;

public class P2 {
  public static void main(String[] args) {
    // Lomuto
    int[] test = new int[]{3, 5, 2, 1, 8, 9, 6, 7, 4};
    int smallest = quickSelectL(test, 0, test.length - 1, 1);
    System.out.println("Smallest: " + smallest);

    test = new int[]{3, 5, 2, 1, 8, 9, 6, 7, 4};
    int secondSmallest = quickSelectL(test, 0, test.length - 1, 2);
    System.out.println("Second smallest: " + secondSmallest);

    test = new int[]{3, 5, 2, 1, 8, 9, 6, 7, 4};
    int fifthSmallest = quickSelectL(test, 0, test.length - 1, 5);
    System.out.println("Fifth smallest: " + fifthSmallest);

    // Hoare
    test = new int[]{3, 5, 2, 1, 8, 9, 6, 7, 4};
    smallest = quickSelectH(test, 0, test.length - 1, 1);
    System.out.println("Smallest: " + smallest);

    test = new int[]{3, 5, 2, 1, 8, 9, 6, 7, 4};
    secondSmallest = quickSelectH(test, 0, test.length - 1, 2);
    System.out.println("Second smallest: " + secondSmallest);

    test = new int[]{3, 5, 2, 1, 8, 9, 6, 7, 4};
    fifthSmallest = quickSelectH(test, 0, test.length - 1, 5);
    System.out.println("Fifth smallest: " + fifthSmallest);
  }

  // quick select - Lomuto partition
  static int quickSelectL(int arr[], int left, int right, int k) {
    int p = partitionL(arr, left, right);
    if (k - 1 == p) {
      return arr[p];
    }
    if (k - 1 > p) {
      return quickSelectL(arr, p + 1, right, k);
    }
    return quickSelectL(arr, left, p - 1, k);
  }

  // quick select - Hoare partition
  static int quickSelectH(int arr[], int left, int right, int k) {
    if (left == right) {
      return arr[left];
    }

    int p = partitionH(arr, left, right);
    if (k - 1 <= p) {
      return quickSelectH(arr, left, p, k);
    }
    return quickSelectH(arr, p + 1, right, k);
  }

  // Lomuto partition
  // Return a partition point p
  // Where all elements arr[left, p - 1] <= arr[p] <= all elements arr[p + 1, right]
  static int partitionL(int arr[], int left, int right) {
    int p = arr[right];  // select the right-most element as pivot
    int i = left;
    for (int j = left; j < right; j++) {
      if (arr[j] <= p) {
        // swap
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

        // increase i
        i++;
      }
    }
    // swap
    int tmp = arr[i];
    arr[i] = arr[right];
    arr[right] = tmp;

    return i;
  }

  // Hoare partition
  // Return a partition point p
  // Where all elements arr[left, p] <= all elements arr[p + 1, right]
  static int partitionH(int arr[], int left, int right) {
    int p = arr[left];  // select the left-most element as pivot
    int front = left;
    int back = right;
    while (true) {
      while (arr[front] < p) {
        front++;
      }
      while (arr[back] > p) {
        back--;
      }
      if (front >= back) {
        return back;
      }
      // swapping
      int t = arr[front];
      arr[front] = arr[back];
      arr[back] = t;
      front++;
      back--;
    }
  }
}
