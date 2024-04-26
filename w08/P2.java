package w08;

public class P2 {
  public static void main(String[] args) {
    // TreeNode node = (new Array2BalancedBST()).build(new int[] {1, 3, 5, 7, 9, 11, 20, 27, 33, 45, 60, 77, 82, 89, 99});
    // System.out.println(node);
    // node.inOrder();

    LinkedList list = new LinkedList();
    list.addToHead(new DataNode(99));
    list.addToHead(new DataNode(95));
    list.addToHead(new DataNode(89));
    list.addToHead(new DataNode(72));
    list.addToHead(new DataNode(67));
    list.addToHead(new DataNode(53));
    list.addToHead(new DataNode(22));
    list.addToHead(new DataNode(19));
    list.addToHead(new DataNode(13));
    list.addToHead(new DataNode(11));
    list.addToHead(new DataNode(7));
    list.addToHead(new DataNode(3));
    TreeNode node2 = (new List2BalancedBST()).build(list);
    System.out.println(node2);
    node2.inOrder();
  }
}

class Array2BalancedBST {
  public TreeNode build(int[] arr) {
    TreeNode root = buildTree(arr, 0, arr.length - 1);
    return root;
  }

  public TreeNode buildTree(int[] arr, int left, int right) {
    if (left > right) {
      return null;
    }
    int mid = (left + right) / 2;
    TreeNode parent = new TreeNode(arr[mid]);
    parent.left = buildTree(arr, left, mid - 1);
    parent.right = buildTree(arr, mid + 1, right);
    return parent;
  }
}

class List2BalancedBST {
  public TreeNode build(LinkedList list) {
    TreeNode root = buildTree(list.head, null);
    return root;
  }

  // build a balanced tree from a linked list bounded by
  // begin (inclusive) and end (exclusive)
  public TreeNode buildTree(DataNode begin, DataNode end) {
    if (begin == end) {
      return null;
    }

    DataNode slow = begin;
    DataNode fast = begin;

    // when fast reaches the end, slow is in the middle
    while (fast != end && fast.next != end) {
      fast = fast.next.next;
      slow = slow.next;
    }

    TreeNode parent = new TreeNode(slow.data);
    parent.left = buildTree(begin, slow);
    parent.right = buildTree(slow.next, end);
    return parent;
  }
}

class TreeNode {
  int data;
  TreeNode left;
  TreeNode right;
  boolean balanced = true;
  
  public TreeNode(int data) {
    this.data = data;
    left = right = null;
  }

  // calculate tree height
  // and check for balanced property at the same tiem
  public int height() {
    balanced = true;
    return heightRecursive(this);
  }

  public boolean isBalanced() {
    height();  // just in case this method has not been called
    return balanced;
  }

  private int heightRecursive(TreeNode node) {
    if (node == null) {
      return 0;
    }
    int hl = heightRecursive(node.left);
    int hr = heightRecursive(node.right);
    if (Math.abs(hl - hr) > 1) {
      balanced = false;
    } else {
      // for a tree to be balanced, the sub-trees must be balanced, too
      balanced = node.left.balanced && node.right.balanced;
    }
    return 1 + Math.max(hl, hr);
  }

  // in-order traversal
  public void inOrder() {
    System.out.println("In-order traversal");
    inOrderRecursive(this);
  }

  private void inOrderRecursive(TreeNode node) {
    if (node == null) {
      return;
    }
    // traverse left-subtree
    inOrderRecursive(node.left);

    // process root
    System.out.println(node.data);

    // traverse right-subtree
    inOrderRecursive(node.right);
  }
  
  @Override
  public String toString() {
    return "Node data: " + data + ", height: " + height() + ", and balanced: " + balanced;
  }
}

class DataNode {
  int data;
  DataNode next;

  public DataNode(int d) {
    data = d;
    next = null;
  }
}

class LinkedList {
  DataNode head;

  public void addToHead(DataNode newNode) {
    if (head != null) {
      newNode.next = head;
    }
    head = newNode;
  }
}