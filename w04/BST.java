package w04;

import java.util.Random;

public class BST<T extends Comparable<T>>{
  protected BinaryTreeNode<T> root;
  protected int size;

  public BST() {
    root = null;
    size = 0;
  }

  public int size() {
    return size;
  }
  
  // add a new value to this BST, no duplication is allowed
  // return the new added node or null (if the value exists)
  public BinaryTreeNode<T> add(T value) {
    if (root == null) {
      root = new BinaryTreeNode<T>(null, value);
      size++;
      return root;
    }
    BinaryTreeNode<T> node = root;
    while (node != null) {
      // left or right?
      if (value.compareTo(node.data) < 0) {
        if (node.left == null) {
          BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(node, value);
          node.left = newNode;
          size++;
          return newNode;
        }
        node = node.left;
      } else if (value.compareTo(node.data) > 0) {
        if (node.right == null) {
          BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(node, value);
          node.right = newNode;
          size++;
          return newNode;
        }
        node = node.right;
      } else {
        // duplication
        return null;
      }
    }
    // this return statement will never run
    // but the code won't compile without it
    return null;
  }

  // remove a value from the tree
  // return the parent node of the removed node
  // OR null, if the node cannot be found or it is the root
  public BinaryTreeNode<T> remove(T value) {
    // Empty tree
    if (size == 0) {
      return null;
    }
    // Step 1: find the node containing value
    BinaryTreeNode<T> node = root;
    while (node != null) {
      if (value.compareTo(node.data) == 0) {
        break;
      }
      if (value.compareTo(node.data) > 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    if (node == null) {
      // no node found
      return null;
    }
    // Step 2A: node to be removed has no children
    if (node.left == null && node.right == null) {
      // the node to be removed is root?
      if (node == root) {
        root = null;
        size = 0;
        return null;
      }
      // update the parent left or right
      if (node.parent.left == node) {
        node.parent.left = null;
      } else {
        node.parent.right = null;
      }
      size--;
      return node.parent;
    }

    // Step 2B: node to be removed has one left child OR one right child
    if ((node.left != null && node.right == null) ||
        (node.left == null && node.right != null)) {
      // find the correct child to replace node
      BinaryTreeNode<T> correctChild;
      if (node.left != null) {
        correctChild = node.left;
      } else {
        correctChild = node.right;
      }
      // the node to be removed is root?
      if (node == root) {
        root = correctChild;
        correctChild.parent = null;
        size--;
        return null;
      }
      // update node's parent to point to correctChild
      if (node.parent.left == node) {
        node.parent.left = correctChild;
        correctChild.parent = node.parent;
      } else {
        node.parent.right = correctChild;
        correctChild.parent = node.parent;
      }
      size--;
      return node.parent;
    }
    // Step 2C: node to be removed has two children
    // get the left-most node on the right subtree
    // OR the right-most node on the left subtree
    BinaryTreeNode<T> replaceNode = node.right;
    while (replaceNode.left != null) {
      replaceNode = replaceNode.left;
    }
    // exchange value
    T tmp = replaceNode.data;
    replaceNode.data = node.data;
    node.data = tmp;
    // now, remove replaceNode, which is similar to step 2A and step 2B
    // it is better if you create seperate methods for those operations
    // for simplicity, I just put everything in the same place

    // Step 2A, again
    if (replaceNode.left == null && replaceNode.right == null) {
      // update the parent left or right
      if (replaceNode.parent.left == replaceNode) {
        replaceNode.parent.left = null;
      } else {
        replaceNode.parent.right = null;
      }
      size--;
      return replaceNode.parent;
    }

    // Step 2B, again
    BinaryTreeNode<T> correctChild;
    if (replaceNode.left != null) {
      correctChild = replaceNode.left;
    } else {
      correctChild = replaceNode.right;
    }
    // update replaceNode's parent to point to correctChild
    if (replaceNode.parent.left == replaceNode) {
      replaceNode.parent.left = correctChild;
      correctChild.parent = replaceNode.parent;
    } else {
      replaceNode.parent.right = correctChild;
      correctChild.parent = replaceNode.parent;
    }
    size--;
    return replaceNode.parent;
  }

  // search and return the node containing value
  // return null if no node found
  public BinaryTreeNode<T> search(T value) {
    BinaryTreeNode<T> node = root;
    while (node != null) {
      if (value.compareTo(node.data) == 0) {
        return node;
      }
      if (value.compareTo(node.data) > 0) {
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return null;
  }

  // display all node data in in-order traversal
  // to verify if all values are sorted
  private void inOrderTraversal(BinaryTreeNode<T> node) {
    if (node != null) {
      inOrderTraversal(node.left);
      System.out.printf(" %s ", node.data.toString());
      inOrderTraversal(node.right);
    }
  }

  public void inOrderDisplay() {
    inOrderTraversal(root);
  }

  public static void main(String[] args) {
    // Test 1: add random values to the BST
    BST<Integer> tree = new BST<>();
    // add some random values
    Random rnd = new Random(System.currentTimeMillis());
    for (int i = 0; i < 10; i++) {
      int next = rnd.nextInt(100);
      System.out.println("Add: " + next);
      tree.add(next);
    }
    System.out.print("Tree 1: ");
    tree.inOrderDisplay();
    System.out.println();

    // Test 2: remove random values from the BST
    BST<Integer> tree2 = new BST<>();
    for (int i = 1; i <= 20; i++) {
      tree2.add(i);
    }
    for (int i = 0; i < 10; i++) {
      int next = rnd.nextInt(20);
      System.out.println("Remove: " + next);
      tree2.remove(next);
    }
    System.out.print("Tree 2: ");
    tree2.inOrderDisplay();
  }
}