package w08;

import java.util.Random;

public class P1 {
  static final int SIZE = (int)1e6;
  static final int SIZE_SMALL = 10;
  static final int RANGE = (int)1e4;
  // use a small range (for example, 100) makes
  // QuickSort/Lomuto partition raise a StackOverflow exception
  // why?

  // generate a random array
  public static int[] generate(boolean small) {
    int size = SIZE;
    if (small) {
      size = SIZE_SMALL;
    }
    Random rnd = new Random();
    int[] res = new int[size];
    for (int i = 0; i < res.length; i++) {
      res[i] = rnd.nextInt(RANGE);
    }
    return res;
  }

  public static void print(int[] arr) {
    StringBuilder sb = new StringBuilder("Array: ");
    boolean first = true;
    for (int n : arr) {
      if (!first) {
        sb.append(", " + n);
      } else {
        sb.append(n);
        first = false;
      }
    }
    System.out.println(sb);
  }

  public static void main(String[] args) throws Exception {
    // Correctness test
    // int[] arr1 = generate(true);
    // System.out.println("Before merge sort");
    // print(arr1);
    // (new MergeSort()).mergeSort(arr1);
    // System.out.println("After merge sort");
    // print(arr1);

    // int[] arr2 = generate(true);
    // System.out.println("Before quick sort/Lomuto partition");
    // print(arr2);
    // (new QuickSortLomuto()).quickSort(arr2, 0, arr2.length - 1);
    // System.out.println("After quick sort/Lomuto partition");
    // print(arr2);

    // int[] arr3 = generate(true);
    // System.out.println("Before quick sort/Hoare partition");
    // print(arr3);
    // (new QuickSortHoare()).quickSort(arr3, 0, arr3.length - 1);
    // System.out.println("After quick sort/Hoare partition");
    // print(arr3);

    // Performance test
    int[] arr4 = generate(false);
    long time1  = System.currentTimeMillis();
    (new MergeSort()).mergeSort(arr4);
    System.out.println("Merge sort time (ms): " + (System.currentTimeMillis() - time1));

    int[] arr5 = generate(false);
    time1  = System.currentTimeMillis();
    (new QuickSortLomuto()).quickSort(arr5, 0, arr5.length - 1);
    System.out.println("Quick sort/Lomuto partition time (ms): " + (System.currentTimeMillis() - time1));

    int[] arr6 = generate(false);
    time1  = System.currentTimeMillis();
    (new QuickSortHoare()).quickSort(arr6, 0, arr6.length - 1);
    System.out.println("Quick sort/Hoare partition time (ms): " + (System.currentTimeMillis() - time1));
  }
  
}

class MergeSort {
  public void mergeSort(int arr[]) {
    if (arr.length > 1) {
      int n = arr.length;
      int middle = n / 2;

      // create 2 sub-arrays from arr
      int[] sub1 = new int[middle];
      for (int i = 0; i < middle; i++) {
        sub1[i] = arr[i];
      }
      int[] sub2 = new int[n - middle];
      for (int i = middle; i < n; i++) {
        sub2[i - middle] = arr[i];
      }

      // sort first and second halves
      mergeSort(sub1);
      mergeSort(sub2);

      // merge sub1 and sub2 into the original array
      merge(sub1, sub2, arr);
    }
  }

  // merge two sub-arrays sub1 and sub2 into the array dest
  public void merge(int[] sub1, int[] sub2, int[] dest) {
    int p1 = 0, p2 = 0, pDest = 0;  // pointers to 3 arrays
    while (p1 < sub1.length && p2 < sub2.length) {
      if (sub1[p1] <= sub2[p2]) {
        dest[pDest] = sub1[p1];
        p1++;
      } else {
        dest[pDest] = sub2[p2];
        p2++;
      }
      pDest++;
    }

    // copy remaining elements, if any
    while (p1 < sub1.length) {
      dest[pDest++] = sub1[p1++];
    }
    while (p2 < sub2.length) {
      dest[pDest++] = sub2[p2++];
    }
  }
}

class QuickSortLomuto {
  // sort with quick sort
  public void quickSort(int arr[], int left, int right) {
    if (left < right) {
      try {
        int p = partition(arr, left, right);
        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
  }

  // Lomuto partition
  // Return a partition point p
  // Where all elements arr[left, p - 1] <= arr[p] <= all elements arr[p + 1, right]
  public int partition(int arr[], int left, int right) {
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
}

class QuickSortHoare {
  // sort with quick sort
  public void quickSort(int arr[], int left, int right) {
    if (left < right) {
      int p = partition(arr, left, right);
      quickSort(arr, left, p);
      quickSort(arr, p + 1, right);
    }
  }

  // Hoare partition
  // Return a partition point p
  // Where all elements arr[left, p] <= all elements arr[p + 1, right]
  public int partition(int arr[], int left, int right) {
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