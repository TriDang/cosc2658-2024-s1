package w09;

public class P1 {
  static int MOD = 1000000007;

  public static void main(String[] args) {
    // correctness test
    System.out.println("Power brute force: " + powerBruteForce(2, 100));
    System.out.println("Power decrease conquer: " + powerFast(2, 100));

    System.out.println("Power brute force: " + powerBruteForce(7, 10001));
    System.out.println("Power decrease conquer: " + powerFast(7, 10001));

    // performance test
    long t1 = System.currentTimeMillis();
    System.out.println("Power brute force: " + powerBruteForce(201, 500000000));
    System.out.println("Time: " + (System.currentTimeMillis() - t1));
    t1 = System.currentTimeMillis();
    System.out.println("Power decrease conquer: " + powerFast(201, 500000000));
    System.out.println("Time: " + (System.currentTimeMillis() - t1));
  }

  static int powerFast(int X, int N) {
    if (N == 0) {
      return 1;
    }
    if (N == 1) {
      return X;
    }
    long sub = powerFast(X, N / 2);
    sub = (sub * sub) % MOD;
    if (N % 2 == 1) {
      return (int)((sub * X) % MOD);
    }
    return (int)sub;
  }

  static int powerBruteForce(int X, int N) {
    long res = 1;
    for (int i = 0; i < N; i++) {
      res = (res * X) % MOD;
    }
    return (int)res;
  }
}
