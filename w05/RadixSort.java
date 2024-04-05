package w05;

public class RadixSort {
  private int[] values;
  static final int BASE = 10;

  public RadixSort(int[] values) {
    this.values = values;
  }

  // apply counting sort to array values
  // based on the digit i (counting from the right)
  // assume: decimal base is used
  // the parameter is the result of raising ten to
  // the power of i (i start from 0)
  private void countingSort(int tenPower) {
    int[] temp = new int[values.length];
    int[] count = new int[BASE];
    // frequency counting
    for (int i = 0; i < values.length; i++) {
      int digit = (values[i] / tenPower) % BASE;
      count[digit]++;
    }
    // cumulative frequency counting
    for (int i = 1; i < BASE; i++) {
      count[i] += count[i - 1];
    }
    // distribute values to the correct locations
    for (int i = values.length - 1; i >= 0; i--) {
      int digit = (values[i] / tenPower) % BASE;
      temp[count[digit] - 1] = values[i];
      count[digit]--;
    }
    // update attributes
    values = temp;
  }

  public void sort() {
    // get the maximum value in values
    int max = values[0];
    for (int i = 0; i < values.length; i++) {
      if (max < values[i]) {
        max = values[i];
      }
    }
    // how many digits in max?
    int digits = (max + "").length();
    int tenPower = 1;
    for (int i = 0; i < digits; i++) {
      countingSort(tenPower);
      tenPower *= 10;
    }
  }

  public int[] get() {
    return values;
  }

  public static void main(String[] args) {
    RadixSort s = new RadixSort(new int[] {
      1, 22, 333, 4444, 55555, 666666,
      7777777, 88888888,
      19, 21, 383, 12345, 1000000000,
      999999999, 1234567, 3456789, 900, 1001
    });
    s.sort();
    int[] res = s.get();
    for (int i = 0; i < res.length; i++) {
      System.out.println(res[i]);
    }
  }
}
