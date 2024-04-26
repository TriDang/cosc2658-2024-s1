package w08;

public class P3 {
  public static void main(String[] args) {
    int[] test1 = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
    // inversion = 0
    System.out.println(InversionBruteforce.count(test1));
    System.out.println(InversionDivideConquer.count(test1));

    int[] test2 = new int[] {1, 2, 3, 4, 5, 6, 8, 7};
    // inversion = 1
    System.out.println(InversionBruteforce.count(test2));
    System.out.println(InversionDivideConquer.count(test2));

    int[] test3 = new int[] {5, 4, 3, 2, 1};
    // inversion = 10
    System.out.println(InversionBruteforce.count(test3));
    System.out.println(InversionDivideConquer.count(test3));

    // correctness test
    int SIZE = 2000;
    int[] test4 = new int[SIZE];
    for (int i = 0; i < SIZE; i++) {
      test4[i] = (int)(Math.random() * SIZE + 1);
    }
    System.out.println(InversionBruteforce.count(test4));
    System.out.println(InversionDivideConquer.count(test4));

    // performance test
    SIZE = 200000;
    int[] test5 = new int[SIZE];
    for (int i = 0; i < SIZE; i++) {
      test5[i] = (int)(Math.random() * SIZE + 1);
    }
    long t1 = System.currentTimeMillis();
    System.out.println(InversionBruteforce.count(test5));
    System.out.println("Brute force time: " + (System.currentTimeMillis() - t1));
    t1 = System.currentTimeMillis();
    System.out.println(InversionDivideConquer.count(test5));
    System.out.println("Divide and Conquer time: " + (System.currentTimeMillis() - t1));
  }
}

class InversionBruteforce {
  static int count(int[] arr) {
    int res = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          res++;
        }
      }
    }
    return res;
  }
}

class InversionDivideConquer {
  static int count(int[] arr) {
    int res = 0;
    if (arr.length == 1) {
      return res;
    }
    int mid = arr.length / 2;
    int[] left = new int[mid];
    int[] right = new int[arr.length - mid];

    for (int i = 0; i < mid; i++) {
      left[i] = arr[i];
    }
    for (int i = mid; i < arr.length; i++) {
      right[i - mid] = arr[i];
    }

    res = count(left) + count(right);
    
    res += merge(left, right, arr);
    
    return res;
  }

  static int merge(int[] left, int[] right, int[] dest) {
    int res = 0;
    int pLeft = 0, pRight = 0, pDest = 0;
    while (pLeft < left.length && pRight < right.length) {
      if (left[pLeft] <= right[pRight]){
        dest[pDest] = left[pLeft];
        pDest++;
        pLeft++;
      } else {
        dest[pDest] = right[pRight];
        pDest++;
        pRight++;
        res += left.length - pLeft;
      }
    }
    while (pLeft < left.length) {
      dest[pDest++] = left[pLeft++];
    }
    while (pRight < right.length) {
      dest[pDest++] = right[pRight++];
    }
    return res;
  }
}