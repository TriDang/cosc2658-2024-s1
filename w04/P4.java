package w04;

public class P4 {
  public static void main(String[] args) {
    /*
     * Create this tree to test
     *           8
     *        /      \
     *      5          16
     *    /   \      /    \
     *   2     7    12     18
     */
    TreeWithMoreSearch<Integer> tree = new TreeWithMoreSearch<>();
    tree.add(8);
    tree.add(5);
    tree.add(16);
    tree.add(2);
    tree.add(7);
    tree.add(12);
    tree.add(18);
    int[] inputs = {2, 4, 5, 6, 7, 8, 10, 12, 15, 16, 17, 18, 20};
    for (int i : inputs) {
      BinaryTreeNode<Integer> result = tree.minGreaterOrEqual(i);
      if (result != null ) {
        System.out.printf("Min value greater than or equal to %d is %d\n", i, result.data);
      } else {
        System.out.printf("No value is greater than or equal to %d\n", i);
      }
    }
  }  
}

class TreeWithMoreSearch<T extends Comparable<T>> extends BST<T> {
  public BinaryTreeNode<T> minGreaterOrEqual(T searchValue) {
    /*
     * at the root of a sub-tree
     * if root.data == searchValue => return root
     * if root.data < searchValue => continue on the right sub-tree
     * if root.data > searchValue => if there is no answer on the left sub-tree, root is the answer
     * otherwise, the answer is on the left sub-tree
     */
    return searchNode(root, searchValue);
  }

  private BinaryTreeNode<T> searchNode(BinaryTreeNode<T> node, T searchValue) {
    if (node == null) {
      return null;
    }
    if (searchValue.compareTo(node.data) == 0) {
      return node;
    }
    if (searchValue.compareTo(node.data) > 0) {
      return searchNode(node.right, searchValue);
    }
    BinaryTreeNode<T> leftResult = searchNode(node.left, searchValue);
    if (leftResult == null) {
      return node;
    }
    return leftResult;
  }
}