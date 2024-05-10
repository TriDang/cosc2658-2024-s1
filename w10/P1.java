package w10;

public class P1 {
  public static void main(String[] args) {
    Task[] tasks = new Task[] {
      new Task(4, 5),
      new Task(2, 6),
      new Task(1, 3),
      new Task(6, 7)
    };

    // sort tasks based on end time
    MergeSort sort = new MergeSort();
    sort.mergeSort(tasks);

    // pick the next task without conflict
    Task current = tasks[0];
    System.out.printf("Selected task (%d - %d)\n", current.start, current.end);
    for (int i = 1; i < tasks.length; i++) {
      if (tasks[i].start < current.end) {
        continue;
      }
      current = tasks[i];
      System.out.printf("Selected task (%d - %d)\n", current.start, current.end);
    }
  }
}

class Task {
  int start;
  int end;

  public Task(int s, int e) {
    start = s;
    end = e;
  }
}

// copy from w08, change int[] to Task[]
class MergeSort {
  public void mergeSort(Task arr[]) {
    if (arr.length > 1) {
      int n = arr.length;
      int middle = n / 2;

      // create 2 sub-arrays from arr
      Task[] sub1 = new Task[middle];
      for (int i = 0; i < middle; i++) {
        sub1[i] = arr[i];
      }
      Task[] sub2 = new Task[n - middle];
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
  public void merge(Task[] sub1, Task[] sub2, Task[] dest) {
    int p1 = 0, p2 = 0, pDest = 0;  // pointers to 3 arrays

    // merge based on end time
    while (p1 < sub1.length && p2 < sub2.length) {
      if (sub1[p1].end <= sub2[p2].end) {
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
