package w07;

public class P1 {
  public static void main(String[] args) {
    // Selection Sort
    System.out.println("Selection sort - First test");
    SelectionSort.sort(new int[]{6, 2, 7, 9, 1});
    System.out.println("Selection sort - Second test");
    SelectionSort.sort(new int[]{1, 3, 5, 7, 9});
    System.out.println("Selection sort - Third test");
    SelectionSort.sort(new int[]{10, 8, 6, 4, 2});

    // Bubble sort
    System.out.println("Bubble sort - First test");
    BubbleSort.sort(new int[]{6, 2, 7, 9, 1});
    System.out.println("Bubble sort - Second test");
    BubbleSort.sort(new int[]{1, 3, 5, 7, 9});
    System.out.println("Bubble sort - Third test");
    BubbleSort.sort(new int[]{10, 8, 6, 4, 2});
  }
}

class SelectionSort {
  public static void sort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      // find the index of smallest element
      int idxMin = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[idxMin]) {
          idxMin = j;
        }
      }

      // Swap the minimum element with the first element
      int temp = arr[idxMin];
      arr[idxMin] = arr[i];
      arr[i] = temp;

      // display
      display(arr);
    }
  }

  private static void display(int[] arr) {
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < arr.length; j++) {
      sb.append(arr[j] + " ");
    }
    System.out.println(sb);
  }
}

class BubbleSort {
  public static void sort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      boolean swapped = false; // used to check if there is anny swap
      for (int j = 0; j < arr.length - 1 - i; j++) {
        if (arr[j] > arr[j+1]) {
          // swap 2 elements
          int temp = arr[j];
          arr[j] = arr[j+1];
          arr[j + 1] = temp;
          swapped = true; // there is a swap
        }
      }
      display(arr);
      // array in sorted order?
      if (!swapped) {
        return;
      }
    }
  }

  private static void display(int[] arr) {
    StringBuilder sb = new StringBuilder();
    for (int j = 0; j < arr.length; j++) {
      sb.append(arr[j] + " ");
    }
    System.out.println(sb);
  }
}