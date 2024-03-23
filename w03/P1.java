package w03;

public class P1 {
  public static void main(String[] args) {
    Node n1, n2, n3, n4, n5;

    // First, I will create a list like this
    // n1 -> n2 -> n3 -> n4 -> n5 -> n2 (loop)
    n5 = new Node(5, null);
    n4 = new Node(4, n5);
    n3 = new Node(3, n4);
    n2 = new Node(2, n3);
    n1 = new Node(1, n2);
    n5.next = n2;

    // Now, if I traverse this list 10 times OR reach the end
    // I will encounter some nodes more than once
    Node node = n1;
    int count = 0;
    System.out.println("Before loop removal");
    while (count < 10 && node != null) {
      System.out.println(node.data);
      node = node.next;
      count++;
    }

    // Now, let's see how we can remove a loop from a list
    // using only array and linked list
    // Idea: use 2-pointer technique: a fast and a slow
    Node fast, slow;
    fast = slow = n1;  // assume that we know n1 is the head

    // when fast == slow: the 2 pointers pointed to
    // one common node in the loop
    while (true) {
      if (fast.next == null || fast.next.next == null) {
        // those checks ensure that nothing happen if there is in fact NO loop
        return;
      }
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        break;
      }
    }
    // now, stop fast, advance slow to count how many nodes in the loop
    int nodes = 0;
    do {
      slow = slow.next;
      nodes++;
    } while (slow != fast);

    // now, position fast <nodes> positions before slow
    // and advance them at the same speed
    // they will meet at the first intersected node
    fast = slow = n1;
    for (int i = 0; i < nodes; i++) {
      fast = fast.next;
    }
    while (slow.next != fast.next) {
      slow = slow.next;
      fast = fast.next;
    }
    // remove the loop
    fast.next = null;

    // Now, if I traverse this list 10 times OR reach the end
    // I will reach the end before
    node = n1;
    count = 0;
    System.out.println("After loop removal");
    while (count < 10 && node != null) {
      System.out.println(node.data);
      node = node.next;
      count++;
    }
  }

  static class Node {
    int data;
    Node next;
  
    public Node(int data, Node next) {
      this.data = data;
      this.next = next;
    }
  }
}
