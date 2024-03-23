package w04;

import java.util.Random;

public class AVL<T extends Comparable<T>> extends BST<T> {
  // return the height of the tree
  public int treeHeight() {
    if (root == null) {
      return 0;
    }
    return root.height;
  }

  @Override
  public BinaryTreeNode<T> add(T value) {
    BinaryTreeNode<T> newNode = super.add(value);
    rebalanceUp(newNode);
    // displayTreePreOrder(root);
    return newNode;
  }

  public void displayTreePreOrder(BinaryTreeNode<T> n) {
    if (n != null) {
      System.out.print("Node: " + n.data);
      if (n.parent != null) {
        System.out.println(" -> " + n.parent.data);
      } else {
        System.out.println();
      }
      displayTreePreOrder(n.left);
      displayTreePreOrder(n.right);
    }    
  }

  @Override
  public BinaryTreeNode<T> remove(T value) {
    BinaryTreeNode<T> removeParent = super.remove(value);
    rebalanceUp(removeParent);
    return removeParent;
  }

  // rebalance a node upward the tree
  // until root is reached or no more step needed
  // update heights of sub-trees at the same time
  private void rebalanceUp(BinaryTreeNode<T> node) {
    // recursively rebalance upward
    while (node != null) {
      node.updateHeight();
      node = balanceNode(node);
      node = node.parent;
    }
  }

  // balance around a given node
  // and return the new node at that location
  private BinaryTreeNode<T> balanceNode(BinaryTreeNode<T> node) {
    int bf = node.getBalanceFactor();
    if (bf < -1) {
      BinaryTreeNode<T> leftChild = node.left;
      int bf2 = leftChild.getBalanceFactor();
      if (bf2 < 0) {
        return rotateRight(node);
      } else {
        rotateLeft(leftChild);
        return rotateRight(node);
      }
    } else if (bf > 1) {
      BinaryTreeNode<T> rightChild = node.right;
      int bf2 = rightChild.getBalanceFactor();
      if (bf2 > 0) {
        return rotateLeft(node);
      } else {
        rotateRight(rightChild);
        return rotateLeft(node);
      }
    }
    return node;
  }

  // rotate right around the sub-stree rooted at node
  // and return the new root
  public BinaryTreeNode<T> rotateRight(BinaryTreeNode<T> node) {
    BinaryTreeNode<T> parent = node.parent;
    BinaryTreeNode<T> leftChild = node.left;
    BinaryTreeNode<T> rightOfLeftChild = leftChild.right;

    leftChild.right = node;
    node.parent = leftChild;

    node.left = rightOfLeftChild;
    if (rightOfLeftChild != null) {
      rightOfLeftChild.parent = node;
    }

    if (parent != null) {
      if (node == parent.left) {
        parent.left = leftChild;
      } else {
        parent.right = leftChild;
      }
      leftChild.parent = parent;
    } else {
      leftChild.parent = null;
      root = leftChild;
    }
    node.updateHeight();
    leftChild.updateHeight();
    return leftChild;
  }

  // rotate left around the sub-stree rooted at node
  // and return the new root
  public BinaryTreeNode<T> rotateLeft(BinaryTreeNode<T> node) {
    BinaryTreeNode<T> parent = node.parent;
    BinaryTreeNode<T> rightChild = node.right;
    BinaryTreeNode<T> leftOfRightChild = rightChild.left;

    rightChild.left = node;
    node.parent = rightChild;

    node.right = leftOfRightChild;
    if (leftOfRightChild != null) {
      leftOfRightChild.parent = node;
    }

    if (parent != null) {
      if (node == parent.left) {
        parent.left = rightChild;
      } else {
        parent.right = rightChild;
      }
      rightChild.parent = parent;
    } else {
      rightChild.parent = null;
      root = rightChild;
    }
    node.updateHeight();
    rightChild.updateHeight();
    return rightChild;
  }

  public static void main(String[] args) {
    // Test 1: add random values to the AVL tree correctness
    AVL<Integer> tree = new AVL<>();
    // add some random values
    Random rnd = new Random(System.currentTimeMillis());
    for (int i = 0; i < 10; i++) {
      int next = rnd.nextInt(100);
      System.out.println("Add: " + next);
      tree.add(next);
    }
    System.out.print("Tree 1 inorder traversal: ");
    tree.inOrderDisplay();
    System.out.println("\nTree height: " + tree.treeHeight());

    // Test 2: remove random values from the AVL
    AVL<Integer> tree2 = new AVL<>();
    for (int i = 1; i <= 20; i++) {
      tree2.add(i);
    }
    for (int i = 0; i < 10; i++) {
      int next = rnd.nextInt(20);
      System.out.println("Remove: " + next);
      tree2.remove(next);
    }
    System.out.print("Tree 2 inorder traversal: ");
    tree2.inOrderDisplay();
    System.out.println("\nTree height: " + tree2.treeHeight());

    // Test 3: verify if the tree is balance
    AVL<Integer> tree3 = new AVL<>();
    for (int i = 1; i <= 4; i++) {
      tree3.add(i);
    }
    System.out.printf("Tree 3 has a size of %d and height of %d\n", tree3.size(), tree3.treeHeight());
    // add more values
    for (int i = 5; i <= 100; i++) {
      tree3.add(i);
    }
    System.out.printf("Tree 3 has a size of %d and height of %d\n", tree3.size(), tree3.treeHeight());
    // add even more values
    for (int i = 101; i <= 1024; i++) {
      tree3.add(i);
    }
    System.out.printf("Tree 3 has a size of %d and height of %d\n", tree3.size(), tree3.treeHeight());
    // this one is the last
    for (int i = 1025; i <= Math.pow(10, 6); i++) {
      tree3.add(i);
    }
    System.out.printf("Tree 3 has a size of %d and height of %d\n", tree3.size(), tree3.treeHeight());
  }
}
