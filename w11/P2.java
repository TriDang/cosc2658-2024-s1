package w11;

public class P2 {
  public static void main(String[] args) {
    int[][] castles = {
      {0, 1, 2, 8},
      {1, 0, 3, 5},
      {2, 3, 0, 4},
      {8, 5, 4, 0}
    };
    System.out.println("Shortest total length: " + minimumSpanningTree(castles));
  }

  static int minimumSpanningTree(int[][] nodes) {
    int n = nodes.length;
    int length = 0;

    // use this array to mark nodes have been added already
    boolean[] added = new boolean[n];

    // distance between the tree being built and not-yet-added nodes
    int[] distances = new int[n];
    for (int i = 0; i < n; i++) {
      distances[i] = Integer.MAX_VALUE;
    }

    // insert node zero (any node is OK) first, so set its distance to zero
    distances[0] = 0;
    
    // stop when the number of added nodes = n
    int nodeCount = 0;
    while (nodeCount < n) {
      int shortest = Integer.MAX_VALUE;
      int shortestNode = -1;  // index of the node having the shortest distance to the tree

      // determine the shortest-distance node to the tree
      for (int i = 0; i < n; i++) {
        if (added[i]) {
          continue;
        }
        if (shortest > distances[i]) {
          shortest = distances[i];
          shortestNode = i;
        }
      }

      if (shortest == Integer.MAX_VALUE) {
        // we cannot go further - the graph is not connected
        return Integer.MAX_VALUE;
      }

      // add the shortest node to the tree
      added[shortestNode] = true;
      length += distances[shortestNode];
      nodeCount++;

      // update other distances to the tree
      for (int i = 0; i < n; i++) {
        if (added[i]) {
          continue;
        }
        // shortestNode and i are connected
        if (nodes[shortestNode][i] > 0) {
          // connect through shortestNode is better?
          if (distances[i] > nodes[shortestNode][i]) {
            distances[i] = nodes[shortestNode][i];
          }
        }
      }
    }
    return length;
  }
}
