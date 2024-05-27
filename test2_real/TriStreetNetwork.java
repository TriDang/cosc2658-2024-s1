package test2_real;

public class TriStreetNetwork {
  int N;
  int[][] network;

  public TriStreetNetwork(int[][] network) {
    this.network = network;
    N = network.length;
  }

  // O(N)
  public int nearestNeighbour() {
    int nearestIndex = -1;
    int nearestDistance = Integer.MAX_VALUE;
    for (int j = 1; j < N; j++) {
      if (network[0][j] < 0) {
        // no direct path
        continue;
      }
      if (network[0][j] < nearestDistance) {
        nearestDistance = network[0][j];
        nearestIndex = j;
      }
    }
    return nearestIndex;
  }

  // O(N^2)
  public int shortestToSchool() {
    int src = 0;
    int dest = N - 1;

    int[] distances = new int[N];  // distance[i] stores the minimum distance from src to i
    boolean[] visited = new boolean[N];  // visited state
    int[] previous = new int[N];  // used to construct the shortest path; previous[i] stores the node that is visited before i

    // initialization
    for (int i = 0; i < N; i++) {
      distances[i] = Integer.MAX_VALUE;
      previous[i] = -1;
    }
    distances[src] = 0;  // zero distance from the src to itself

    while (true) {
      // Greedy choice: retrieve the shortest-distance node from
      // unvisited nodes
      int shortest = Integer.MAX_VALUE;
      int shortestNode = -1;
      for (int i = 0; i < N; i++) {
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
      for (int i = 0; i < N; i++) {
        if (visited[i]) {
          continue;
        }
        // shortestNode and i are connected?
        if (network[shortestNode][i] > 0) {
          // current distance to i > distance reached through shortestNode
          if (distances[i] > distances[shortestNode] + network[shortestNode][i]) {
            distances[i] = distances[shortestNode] + network[shortestNode][i];
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

  public static void main(String[] args) {
    int[][] distances = {
      {0, -1, 5, 10},
      {-1, 0, 4, 2},
      {-1, 1, 0, 4},
      {3, -1, 7, 0}
    };
    TriStreetNetwork program = new TriStreetNetwork(distances);
    System.out.println("Nearest Neighbour: " + program.nearestNeighbour());
    System.out.println("Shortest to School: " + program.shortestToSchool());
  }
}
