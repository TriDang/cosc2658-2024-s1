package w05;

public class P1 {
  static int[] generate(int size, int max) {
    int[] res = new int[size];
    for (int i = 0; i < size; i++) {
      res[i] = (int)(Math.random() * max + 1);
    }
    return res;
  }

  // sort an integer array having the range value [1..max]
  static int[] countingSort(int[] arr, int max) {
    int[] freq = new int[max + 1];  // frequency array
    int[] res = new int[arr.length];  // result array

    // freq counting
    for (int i = 0; i < arr.length; i++) {
      freq[arr[i]]++;
    }

    // calculate cumulative freq
    for (int i = 1; i <= max; i++) {
      freq[i] = freq[i-1] + freq[i];
    }

    // distribution step (right to left)
    for (int i = arr.length - 1; i >= 0; i--) {
      res[freq[arr[i]] - 1] = arr[i];  // freq[arr[i]] - 1 because this is zero-based index
      freq[arr[i]]--;
    }
    return res;
  }

  static void printArr(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    // Correctness test
    int[] test = generate(10, 10);
    System.out.println("Before sorting");
    printArr(test);
    System.out.println("After sorting");
    printArr(countingSort(test, 10));

    // Performance test
    int size = 1000;
    int max = 1000000000;
    int[] test2 = generate(size, max);
    long t1 = System.currentTimeMillis();
    countingSort(test2, max);
    long t2 = System.currentTimeMillis();
    System.out.println("Distribution sort: " + (t2 - t1) + " milliseconds");

    long t3 = System.currentTimeMillis();
    java.util.Arrays.sort(test2);
    long t4 = System.currentTimeMillis();
    System.out.println("Java sort: " + (t4 - t3) + " milliseconds");
  }
}

// The sub-problem 1B can be solved with Radix sort
