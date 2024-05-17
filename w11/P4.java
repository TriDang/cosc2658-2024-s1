package w11;

public class P4 {
  public static void main(String[] args) {
    System.out.println("Number of paths (1, 1): " + countPaths(1, 1));
    System.out.println("Number of paths (1, 10): " + countPaths(1, 10));
    System.out.println("Number of paths (10, 1): " + countPaths(10, 1));
    System.out.println("Number of paths (2, 2): " + countPaths(2, 2));
    System.out.println("Number of paths (3, 3): " + countPaths(3, 3));
    System.out.println("Number of paths (10, 10): " + countPaths(10, 10));
  }

  static int countPaths(int rows, int cols) {
    // To reach the cell[R,C] you can come from
    // its left cell[R-1,C] or its above cell[R,C-1]
    // furthermore, you cannot go from [R-1,C] to [R,C-1] or vice versa
    // so, the number of paths to [R,C] = paths to [R-1,C] + paths to [R,C-1]

    // base cases: one unique path to any top-row cells or first-column cells

    // use a 2D array to store the number of paths to all cells
    int[][] paths = new int[rows][cols];
    for (int i = 0; i < cols; i++) {
      paths[0][i] = 1;  // top row
    }
    for (int i = 0; i < rows; i++) {
      paths[i][0] = 1;  // first column
    }
    for (int r = 1; r < rows; r++) {
      for (int c = 1; c < cols; c++) {
        paths[r][c] = paths[r - 1][c] + paths[r][c - 1];
      }
    }
    return paths[rows - 1][cols - 1];
  }
}
