package w11;

public class P1 {
  public static void main(String[] args) {
    int[][] distances = {
      {0, 1, 2, 4},
      {1, 0, 0, 2},
      {2, 0, 0, 5},
      {4, 2, 5, 0}
    };
    System.out.println("Shortest distance: " + shortestPath(distances, 0, distances.length - 1));
  }

  static int shortestPath(int[][] nodes, int src, int dest) {
    int n = nodes.length;
    
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
        if (nodes[shortestNode][i] > 0) {
          // current distance to i > distance reached through shortestNode
          if (distances[i] > distances[shortestNode] + nodes[shortestNode][i]) {
            distances[i] = distances[shortestNode] + nodes[shortestNode][i];
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
          path = shortestNode + " -> " + path;
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
}