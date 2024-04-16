package w07;

public class SubSet {
  public static void main(String[] args) {
    int[] input = {1, 2, 3};
    boolean[] selected = {false, false, false};
    subset(input, selected, 0);
  }

  static void subset(int[] input, boolean[] selected, int idx) {
    if (idx == input.length) {
      process(input, selected);
      return;
    }

    // Not selected
    selected[idx] = false;
    subset(input, selected, idx + 1);

    // Selected
    selected[idx] = true;
    subset(input, selected, idx + 1);
  }

  static void process(int[] set, boolean[] selected) {
    System.out.print("Subset: ");
    for (int i = 0; i < set.length; i++) {
      if (selected[i]) {
        System.out.print(set[i] + " ");
      }
    }
    System.out.println();
  }
}