package w07;

import w03.LinkedListQueue;

public class P2 {
  public static void main(String[] args) {
    MatrixGraph g = new MatrixGraph(6);
    g.setNodeLabel(0, "A");
    g.setNodeLabel(1, "B");
    g.setNodeLabel(2, "C");
    g.setNodeLabel(3, "D");
    g.setNodeLabel(4, "E");
    g.setNodeLabel(5, "F");
    g.addEdge(0, 1);  // A-B
    g.addEdge(0, 2);  // A-C
    g.addEdge(0, 3);  // A-D
    g.addEdge(1, 2);  // B-C
    g.addEdge(1, 4);  // B-E
    g.addEdge(2, 3);  // C-D
    g.addEdge(2, 4);  // C-E
    g.addEdge(2, 5);  // C-F
    g.addEdge(3, 5);  // D-F
    g.DFS();
    g.BFS();    
  }
}

class MatrixGraph {
  // this matrix presents the connections in the graph
  int[][] matrix;

  // this array presents the labels of the vertices/nodes
  String[] nodeLabels;

  int size;

  // create a graph with the number of nodes/vertices
  public MatrixGraph(int nodes) {
    size = nodes;
    matrix = new int[size][size];
    // no connection/edge initiall
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        matrix[i][j] = 0;
      }
    }
    nodeLabels = new String[size];
  }

  // set the label for a node
  public void setNodeLabel(int nodeIdx, String label) {
    nodeLabels[nodeIdx] = label;
  }

  // create an edge between two nodes
  public void addEdge(int node1, int node2) {
    matrix[node1][node2] = 1;
    // for undirected graph, node1 -> node2 also means node2 -> node1
    matrix[node2][node1] = 1;
  }

  // remove an edge between two nodes
  public void removeEdge(int node1, int node2) {
    matrix[node1][node2] = 0;
    // for undirected graph, node1 -> node2 also means node2 -> node1
    matrix[node2][node1] = 0;
  }

  // depth-first search/traversal
  public void DFS() {
    System.out.println("Depth-First Search/Traversal");
    // visited states
    boolean[] visited = new boolean[size];
    for (int i = 0; i < size; i++) {
      visited[i] = false;
    }
    // start the DFS recursively from node 0 (you can start from any node)
    DFSR(0, visited);

    // the above code assumes the graph is connected
    // that mean you can reach all nodes from any node
    // if the graph is not connected, you must call DFSR on every node
  }

  public void DFSR(int nodeIdx, boolean[] visited) {
    if (visited[nodeIdx]) {
      // this node has been visited before
      return;
    }
    // this is the "visit" operation
    // do whatever you want with this node
    System.out.println("Visit: " + nodeLabels[nodeIdx]);
    // mark the visited state
    visited[nodeIdx] = true;
    // apply the DFS process to all adjacent nodes
    for (int i = 0; i < size; i++) {
      if (matrix[nodeIdx][i] == 1 && !visited[i]) {
        DFSR(i, visited);
      }
    }
  }

  // breadth-first search/traversal
  public void BFS() {
    System.out.println("Breadth-First Search/Traversal");
    // visited states
    boolean[] visited = new boolean[size];
    for (int i = 0; i < size; i++) {
      visited[i] = false;
    }
    
    // to enqueue a node, we just need the node index
    LinkedListQueue<Integer> queue = new LinkedListQueue<>();

    // start from node 0
    // you can start from any node
    queue.enQueue(0);
    visited[0] = true;

    while (!queue.isEmpty()) {
      int nodeIdx = queue.peekFront();
      queue.deQqueue();
      // "visit" this node
      System.out.println("Visit: " + nodeLabels[nodeIdx]);      

      // add all adjacent nodes to the queuq
      for (int i = 0; i < size; i++) {
        if (matrix[nodeIdx][i] == 1 && !visited[i]) {
          queue.enQueue(i);
          visited[i] = true;          
        }
      }
    }

    // the above code assumes the graph is connected
    // that mean you can reach all nodes from any node
    // if the graph is not connected, you must call BFS on every node
    // to make sure you visit all nodes
  }
}