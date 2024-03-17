package w03;

// Singly Linked List-based implementation of queue
public class LinkedListQueue<T> {
  // this class is used as a container of data 
  static class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private int size;
  private Node<T> head;

  public LinkedListQueue() {
    size = 0;
    head = null;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean enQueue(T item) {
    // add a new node at the beginning
    Node<T> n = new Node<T>(item);
    n.next = head;
    head = n;
    size++;
    return true;
  }

  public boolean deQqueue() {
    // remove the last node
    if (isEmpty()) {
      return false;
    }
    if (size == 1) {
      head = null;
      size = 0;
      return true;
    }
    Node<T> prev = head;
    Node<T> current = prev.next;
    while (current.next != null) {
      prev = current;
      current = current.next;
    }
    prev.next = null;
    size--;
    return true;
  }

  public T peekFront() {
    if (isEmpty()) {
      return null;
    }
    Node<T> node = head;
    while (node.next != null) {
      node = node.next;
    }
    return node.data;
  }

  public static void main(String[] args) {
    LinkedListQueue<String> queue = new LinkedListQueue<String>();
    queue.enQueue("Algorithm");
    queue.enQueue("Is");
    queue.enQueue("Easy");
    queue.enQueue("Right?");
    while (!queue.isEmpty()) {
      System.out.println(queue.peekFront());
      queue.deQqueue();
    }
  }
}
