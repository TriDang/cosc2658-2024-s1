package w04;

public class P2 {
  public static void main(String[] args) {
    // use InOrder traversal to store all node values
    // in an array, then check if the array is an increasing array
    BinaryTreeNode<Integer> bstTree = new BinaryTreeNode<Integer>(null, 10);
    BinaryTreeNode<Integer> leftChild = new BinaryTreeNode<Integer>(bstTree, 5);
    bstTree.left = leftChild;
    BinaryTreeNode<Integer> leftLeftChild = new BinaryTreeNode<Integer>(leftChild, 3);
    leftChild.left = leftLeftChild;
    BinaryTreeNode<Integer> rightLeftChild = new BinaryTreeNode<Integer>(leftChild, 8);
    leftChild.right = rightLeftChild;
    BinaryTreeNode<Integer> rightChild = new BinaryTreeNode<Integer>(bstTree, 16);
    bstTree.right = rightChild;
    BinaryTreeNode<Integer> leftRightChild = new BinaryTreeNode<Integer>(rightChild, 12);
    rightChild.left = leftRightChild;
    BinaryTreeNode<Integer> rightRightChild = new BinaryTreeNode<Integer>(rightChild, 20);
    rightChild.right = rightRightChild;
    // Apply in-order traversal on this tree
    System.out.println("Result 1");
    inOrder(bstTree);

    // update the left and right references in the root
    // the result is not a BST anymore
    bstTree.left = rightChild;
    bstTree.right = leftChild;
    System.out.println("\nResult 2");
    inOrder(bstTree);
  }

  static void inOrder(BinaryTreeNode<Integer> node) {
    if (node != null) {
      inOrder(node.left);
      System.out.print(" " + node.data);
      inOrder(node.right);
    }
  }
}
