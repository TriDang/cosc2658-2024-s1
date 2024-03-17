package w03;

// Singly Linked List-based implementation of stack
public class LinkedListStack<T> {
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

  public LinkedListStack() {
    size = 0;
    head = null;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public boolean push(T item) {
    // add a new node at the beginning
    Node<T> node = new Node<T>(item);
    if (!isEmpty()) {
      node.next = head;
    }
    head = node;
    size++;
    return true;
  }

  public boolean pop() {
    // remove the first node
    // make sure the stack is not empty
    if (isEmpty()) {
      return false;
    }
    // advance head
    head = head.next;
    size--;
    return true;
  }

  public T peek() {
    // make sure the stack is not empty
    if (isEmpty()) {
      return null;
    }
    return head.data;
  }

  public static void main(String[] args) {
    LinkedListStack<Double> st = new LinkedListStack<Double>();
    st.push(1.0);
    st.push(2.0);
    st.push(3.0);
    st.push(4.0);
    while (!st.isEmpty()) {
      System.out.println(st.peek());
      st.pop();
    }
  }
}
