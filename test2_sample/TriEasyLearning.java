package test2_sample;

public class TriEasyLearning {
  int[][] cost;

  public TriEasyLearning(int[][] c) {
    cost = c;
  }

  // O(N)
  public int compare(int[] seq1, int[] seq2) {
    int c1 = 0;
    int c2 = 0;
    for (int i = 0; i < seq1.length - 1; i++) {
      c1 += cost[seq1[i]][seq1[i + 1]];
    }
    for (int i = 0; i < seq2.length - 1; i++) {
      c2 += cost[seq2[i]][seq2[i + 1]];
    }
    if (c1 > c2) {
      return 1;
    }
    if (c1 < c2) {
      return -1;
    }
    return 0;
  }

  // O(N^2)
  public int bestSequence() {
    int src = 0;
    int dest = cost.length - 1;
    int n = cost.length;
      
    int[] distances = new int[n];  // distance[i] stores the minimum distance from src to i
    boolean[] visited = new boolean[n];  // visited state
    int[] previous = new int[n];  // used to construct the shortest path; previous[i] stores the node that is visited before i
  
    // initialization
    for (int i = 0; i < n; i++) {
      distances[i] = Integer.MAX_VALUE;
      previous[i] = -1;
    }
    distances[src] = 0;  // zero distance from the src to itself
  
    while (true) {
      // Greedy choice: retrieve the shortest-distance node from
      // unvisited nodes
      int shortest = Integer.MAX_VALUE;
      int shortestNode = -1;
      for (int i = 0; i < n; i++) {
        if (visited[i]) {
          continue;
        }
        if (shortest > distances[i]) {
          shortest = distances[i];
          shortestNode = i;
        }
      }

      // update the shortest distance through shortest node
      // to all unvisited nodes
      for (int i = 0; i < n; i++) {
        if (visited[i]) {
          continue;
        }
        // shortestNode and i are connected?
        if (cost[shortestNode][i] > 0) {
          // current distance to i > distance reached through shortestNode
          if (distances[i] > distances[shortestNode] + cost[shortestNode][i]) {
            distances[i] = distances[shortestNode] + cost[shortestNode][i];
            previous[i] = shortestNode;
          }
        }
      }
      
      if (shortestNode == dest) {
        // we reach the destination
        // display the shortest path
        String path = shortestNode + "";
        while (previous[shortestNode] != -1) {
          shortestNode = previous[shortestNode];
          path = shortestNode + " " + path;
        }

        System.out.println("Shortest path: " + path);
        return distances[dest];
      }

      // even shortest is INFINITY => stop
      if (shortest == Integer.MAX_VALUE) {
        // we cannot go further
        return Integer.MAX_VALUE;
      }
      // continue the next round
      visited[shortestNode] = true;
    }
  }

  public static void main(String[] args) {
    int[][] courses = new int[][] {
      {0, 1, 5},
      {4, 0, 3},
      {2, 1, 0}
    };
    TriEasyLearning test = new TriEasyLearning(courses);
    int[] s1 = new int[] {0, 2};
    int[] s2 = new int[] {0, 1, 2};
    System.out.println(test.compare(s1, s2));
    System.out.println(test.bestSequence());
  }
}
