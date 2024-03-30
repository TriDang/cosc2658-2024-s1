package w04;

import java.util.Random;
import w03.LinkedListQueue;

public class P1 {
  public static void main(String[] args) {
    TreeTraversal<Integer> tree = new TreeTraversal<>();
    Random rnd = new Random(System.currentTimeMillis());
    for (int i = 0; i < 10; i++) {
      int value = rnd.nextInt(100);
      System.out.println("Add: " + value);
      tree.add(value);
    }
    // Display results
    System.out.println("Pre Order Traversal");
    tree.preOrder();
    System.out.println("Post Order Traversal");
    tree.postOrder();
    System.out.println("In Order Traversal");
    tree.inOrder();
    System.out.println("Breadth First Traversal");
    tree.breadthFirst();
  }
  
}

class TreeTraversal<T extends Comparable<T>> extends BST<T> {
  public void preOrder() {
    preOrderRecursive(root);
  }

  private void preOrderRecursive(BinaryTreeNode<T> node) {
    if (node != null) {
      // visit node
      System.out.println(node.data);
      // traverse sub-trees
      preOrderRecursive(node.left);
      preOrderRecursive(node.right);
    }
  }

  public void postOrder() {
    postOrderRecursive(root);
  }

  private void postOrderRecursive(BinaryTreeNode<T> node) {
    if (node != null) {      
      // traverse sub-trees
      postOrderRecursive(node.left);
      postOrderRecursive(node.right);
      // visit node
      System.out.println(node.data);
    }
  }

  public void inOrder() {
    inOrderRecursive(root);
  }

  private void inOrderRecursive(BinaryTreeNode<T> node) {
    if (node != null) {      
      // traverse left sub-tree
      inOrderRecursive(node.left);
      // visit node
      System.out.println(node.data);
      // traverse right sub-tree
      inOrderRecursive(node.right);      
    }
  }

  public void breadthFirst() {
    // a queue is needed
    LinkedListQueue<BinaryTreeNode<T>> queue = new LinkedListQueue<>();
    queue.enQueue(root);
    while (!queue.isEmpty()) {
      // extract the first element in the queue
      BinaryTreeNode<T> node = queue.peekFront();
      queue.deQqueue();
      if (node != null) {
        // visit node
        System.out.println(node.data);
        // enqueue sub-tree roots
        queue.enQueue(node.left);
        queue.enQueue(node.right);
      }
    }
  }
}