package w07;

public class Permutation {
  public static void main(String[] args) {
    int[] input = {1, 2, 3};
    int[] current = {0, 0, 0};
    boolean[] taken = {false, false, false};
    permute(input, taken, current, 0);
  }

  static void permute(int[] input, boolean[] taken, int[] current, int idx) {
    if (idx == input.length) {
      process(current);
      return;
    }

    for (int i = 0; i < input.length; i++) {
      if (taken[i]) {
        continue;
      }
      current[idx] = input[i];
      taken[i] = true;
      permute(input, taken, current, idx + 1);
      taken[i] = false;
    }
  }

  static void process(int[] permutation) {
    for (int v : permutation) {
      System.out.print(v);
    }
    System.out.println();
  }
}