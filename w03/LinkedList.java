package w03;

public class LinkedList<T> implements List<T> {
  static class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  private int size;
  private Node<T> pointer;
  private Node<T> head;

  public LinkedList() {
    size = 0;
    head = null;
    pointer = null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void reset() {
    pointer = head;
  }

  @Override
  public T get(int index) {
    if (index >= size) {
      return null;
    }
    Node<T> p = head;
    while (index > 0) {
      p = p.next;
      index--;
    }
    return p.data;
  }

  @Override
  public boolean hasNext() {
    return (pointer != null);
  }

  @Override
  public T next() {
    T data = pointer.data;
    pointer = pointer.next;
    return data;
  }

  @Override
  public boolean contains(T value) {
    Node<T> p = head;
    while (p != null) {
      if (p.data.equals(value)) {
        return true;
      }
      p = p.next;
    }
    return false;
  }

  private boolean insertAtHead(T value) {
    Node<T> n = new Node<T>(value);
    n.next = head;
    head = n;
    size++;
    return true;
  }

  private boolean removeAtHead() {
    if (head == null) {
      return false;
    }
    head = head.next;
    size--;
    return true;
  }

  @Override
  public boolean insertAt(int index, T value) {
    if (index > size) {
      return false;
    }
    if (index == 0) {
      return insertAtHead(value);
    }
    Node<T> current = head;
    Node<T> previous = null;
    while (index > 0) {
      previous = current;
      current = current.next;
      index--;
    }
    Node<T> node = new Node<T>(value);
    node.next = current;
    previous.next = node;
    size++;
    return true;
  }

  @Override
  public boolean insertBefore(T searchValue, T value) {
    if (head == null) {
      return false;
    }
    if (head.data.equals(searchValue)) {
      return insertAtHead(value);
    }
    Node<T> current = head;
    Node<T> previous = null;
    while (current != null) {
      if (current.data.equals(searchValue)) {
        Node<T> node = new Node<T>(value);
        node.next = current;
        previous.next = node;
        size++;
        return true;
      }
      previous = current;
      current = current.next;
    }    
    return false;
  }

  @Override
  public boolean insertAfter(T searchValue, T value) {
    if (head == null) {
      return false;
    }
    Node<T> current = head;
    Node<T> previous = null;
    while (current != null) {
      if (current.data.equals(searchValue)) {
        previous = current;
        current = current.next;
        Node<T> node = new Node<T>(value);
        node.next = current;
        previous.next = node;
        size++;
        return true;
      }
      previous = current;
      current = current.next;
    }    
    return false;
  }

  @Override
  public boolean removeAt(int index) {
    if (index >= size) {
      return false;
    }
    if (index == 0) {
      return removeAtHead();
    }
    Node<T> current = head;
    while (--index > 0) {
      current = current.next;
    }
    current.next = current.next.next;
    size--;
    return true;
  }

  @Override
  public boolean remove(T value) {
    if (head == null) {
      return false;
    }
    if (head.data.equals(value)) {
      return removeAtHead();
    }
    Node<T> current = head.next;
    Node<T> previous = head;
    while (current != null) {
      if (current.data.equals(value)) {
        previous.next = current.next;
        size--;
        return true;
      }
      previous = current;
      current = current.next;
    }
    return false;
  }

  public static void main(String[] args) {
    List<String> names = new LinkedList<>();
    names.insertAt(0, "World");  // World
    names.insertAt(0, "Hello");  // Hello, World
    names.insertAt(0, "RMIT");  // RMIT, Hello, World
    System.out.println("-------First Test-------");
    names.reset();
    while (names.hasNext()) {
      System.out.println(names.next());
    }
    names.insertBefore("RMIT", "SSET");  // SSET, RMIT, Hello, World
    names.insertAfter("World", "4.0");  // SSET, RMIT, Hello, World, 4.0
    names.insertAfter("Alice", "Wonderland");  // SSET, RMIT, Hello, World, 4.0 (no change)
    System.out.println("-------Second Test-------");
    names.reset();
    while (names.hasNext()) {
      System.out.println(names.next());
    }
    names.removeAt(1);  // // SSET, Hello, World, 4.0
    names.remove("4.0");  // // SSET, Hello, World
    System.out.println("-------Third Test-------");
    names.reset();
    while (names.hasNext()) {
      System.out.println(names.next());
    }
    System.out.println("-------More Test-------");
    System.out.println("Value at index 1: " + names.get(1));  // Hello
    System.out.println("Alice exists in the list? " + names.contains("Alice"));  // false
    System.out.println("SSET exists in the list? " + names.contains("SSET"));  // true
  }
}
