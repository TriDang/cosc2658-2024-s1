package test2_sample;

public class TriDoraemonCake {
  Topic[] topics;
  double A;

  public TriDoraemonCake(Topic[] t, double a) {
    topics = t;
    A = a;
  }

  // O(N*lgN)
  public double weightByNumber(int X) {
    double[] w = new double[topics.length];
    for (int i = 0; i < topics.length; i++) {
      w[i] = topics[i].W;
    }
    new MergeSort().mergeSort(w);
    double total = 0;
    for (int i = w.length - 1; i > w.length - 1 - X; i--) {
      total += w[i];
    }
    return total;
  }

  // O(2^N)
  public double largestWeight() {
    boolean[] selected = new boolean[topics.length];
    SubSet.subset(topics.clone(), selected, 0, A);
    for (int i = 0; i < SubSet.bestSet.length; i++) {
      if (SubSet.bestSet[i]) {
        System.out.print(i + " ");
      }
    }
    System.out.println();
    return SubSet.bestW;
  }
  
  public static void main(String[] args) {
    Topic[] topics = new Topic[3];
    topics[0] = new Topic(8, 7);
    topics[1] = new Topic(10, 8);
    topics[2] = new Topic(5, 3);
    TriDoraemonCake test = new TriDoraemonCake(topics, 10);
    System.out.println(test.weightByNumber(3));
    System.out.println(test.weightByNumber(2));
    System.out.println(test.weightByNumber(1));
    System.out.println(test.largestWeight());
  }
}

class Topic {
  public double W;
  public double S;

  public Topic(double w, double s) {
    W = w;
    S = s;
  }
}

class MergeSort {
  public void mergeSort(double arr[]) {
    if (arr.length > 1) {
      int n = arr.length;
      int middle = n / 2;

      // create 2 sub-arrays from arr
      double[] sub1 = new double[middle];
      for (int i = 0; i < middle; i++) {
        sub1[i] = arr[i];
      }
      double[] sub2 = new double[n - middle];
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
  public void merge(double[] sub1, double[] sub2, double[] dest) {
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

class SubSet {
  static double bestW = 0;
  static boolean[] bestSet = {};

  static void subset(Topic[] input, boolean[] selected, int idx, double A) {
    if (idx == input.length) {
      process(input, selected, A);
      return;
    }

    // Not selected
    selected[idx] = false;
    subset(input, selected, idx + 1, A);

    // Selected
    selected[idx] = true;
    subset(input, selected, idx + 1, A);
  }

  static void process(Topic[] set, boolean[] selected, double A) {
    double totalW = 0;
    double totalS = 0;
    for (int i = 0; i < set.length; i++) {
      if (selected[i]) {
        totalS += set[i].S;
        totalW += set[i].W;
        if (totalS > A) {
          return;
        }
      }
    }
    if (totalW > bestW) {
      bestW = totalW;
      bestSet = selected.clone();
    }
  }
}
