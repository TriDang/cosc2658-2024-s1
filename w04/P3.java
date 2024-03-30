package w04;

public class P3 {
  public static void main(String[] args) {
    /*
     * Create this tree to test
     *           10
     *        /      \
     *      6          16
     *    /   \      /    \
     *   2     9    12     18
     */
    TreeWithLevel<Integer> tree = new TreeWithLevel<>();
    tree.add(10);
    tree.add(6);
    tree.add(16);
    tree.add(2);
    tree.add(9);
    tree.add(12);
    tree.add(18);
    System.out.println("Level Tests");
    System.out.println(tree.nodeLevel(tree.search(10)));  // 0
    System.out.println(tree.nodeLevel(tree.search(6)));  // 1
    System.out.println(tree.nodeLevel(tree.search(2)));  // 2
    System.out.println("Distance Tests");
    // Distance between the same node
    System.out.println(tree.distance(tree.search(2), tree.search(2)));  // 0
    // Distance between the a node and its parent
    System.out.println(tree.distance(tree.search(2), tree.search(6)));  // 1
    // Distance between a node and root
    System.out.println(tree.distance(tree.search(2), tree.search(10)));  // 2
    // Distance between two general nodes
    System.out.println(tree.distance(tree.search(2), tree.search(16)));  // 3
  }
}

class TreeWithLevel<T extends Comparable<T>> extends BST<T> {
  public int nodeLevel(BinaryTreeNode<T> node) {
    if (node == root) {
      return 0;
    }
    return nodeLevel(node.parent) + 1;
  }

  public int distance(BinaryTreeNode<T> node1, BinaryTreeNode<T> node2) {
    // node1 and node2 must have at least a common ancestor
    // the distance between them is the total length
    // of the two paths:
    // the path from node1 to the lowest common ancestor
    // and the path from node2 to the lowest common ancestor
    int level1 = nodeLevel(node1);
    int level2 = nodeLevel(node2);
    int minLevel = Math.min(level1, level2);
    // go up to determine the lowest common ancestor
    int tmp = level1;
    while (minLevel < tmp) {
      node1 = node1.parent;
      tmp--;
    }
    tmp = level2;
    while (minLevel < tmp) {
      node1 = node1.parent;
      tmp--;
    }
    while (node1 != node2) {
      node1 = node1.parent;
      node2 = node2.parent;
      minLevel--;
    }
    return (level1 - minLevel) + (level2 - minLevel);
  }
}