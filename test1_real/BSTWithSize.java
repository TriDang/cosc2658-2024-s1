package test1_real;

import w04.BinaryTreeNode;
import w04.BST;

public class BSTWithSize<T extends Comparable<T>> extends BST<T> {
  // O(N)
  public int subtreeSize(BinaryTreeNode<T> node) {
    if (node == null) {
      return 0;
    }
    return subtreeSize(node.left) + subtreeSize(node.right) + 1;
  }

  // O(lgN)
  public BinaryTreeNode<T> add(T value) {
    BinaryTreeNode<T> node = super.add(value);
    BinaryTreeNode<T> tmp = node.parent;
    while (tmp != null) {
      tmp.subtreeNodes++;
      tmp = tmp.parent;      
    }
    return node;
  }

  public static void main(String[] args) {
    BSTWithSize<Integer> tree = new BSTWithSize<>();
    tree.add(15);
    tree.add(10);
    tree.add(20);
    tree.add(8);
    tree.add(12);
    tree.add(16);
    tree.add(25);
    BinaryTreeNode<Integer> node;
    node = tree.search(15);
    System.out.println("Subtree size at 15 is " + tree.subtreeSize(node));  // 7
    System.out.println("Subtree size at 15 is " + node.subtreeNodes);  // 7
    node = tree.search(10);
    System.out.println("Subtree size at 10 is " + tree.subtreeSize(node));  // 3
    System.out.println("Subtree size at 10 is " + node.subtreeNodes);  // 3
    node = tree.search(16);
    System.out.println("Subtree size at 16 is " + tree.subtreeSize(node));  // 1
    System.out.println("Subtree size at 16 is " + node.subtreeNodes);  // 1
  }
}
