package w03;

public class ArrayList<T> implements List<T> {
  private int size;
  private int pointer;
  private static int CAPACITY = 1000;
  private T[] items;

  public ArrayList() {
    size = 0;
    pointer = 0;
    items = (T[])new Object[CAPACITY];
  }

  // shift all elements from index one position to the right
  private void shiftRight(int index) {
    for (int i = size; i > index; i--) {
      items[i] = items[i - 1];
    }
  }

  // shift all elements from the end one position to the left
  // until index
  private void shiftLeft(int index) {
    for (int i = index + 1; i < size; i++) {
      items[i - 1] = items[i];
    }
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void reset() {
    pointer = 0;
  }

  @Override
  public T get(int index) {
    if (index >= size) {
      return null;
    }
    return items[index];
  }

  @Override
  public boolean hasNext() {
    return (pointer < size);
  }

  @Override
  public T next() {
    pointer++;
    return items[pointer - 1];
  }

  @Override
  public boolean contains(T value) {
    for (int i = 0; i < size; i++) {
      if (items[i].equals(value)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean insertAt(int index, T value) {
    if (index > size) {
      return false;
    }
    shiftRight(index);
    items[index] = value;
    size++;
    return true;
  }

  @Override
  public boolean insertBefore(T searchValue, T value) {
    for (int i = 0; i < size; i++) {
      if (items[i].equals(searchValue)) {
        return insertAt(i, value);
      }
    }
    return false;
  }

  @Override
  public boolean insertAfter(T searchValue, T value) {
    for (int i = 0; i < size; i++) {
      if (items[i].equals(searchValue)) {
        return insertAt(i + 1, value);
      }
    }
    return false;
  }

  @Override
  public boolean removeAt(int index) {
    if (index >= size) {
      return false;
    }
    shiftLeft(index);
    size--;
    return true;
  }

  @Override
  public boolean remove(T value) {
    for (int i = 0; i < size; i++) {
      if (items[i].equals(value)) {
        return removeAt(i);
      }
    }
    return false;
  }

  public static void main(String[] args) {
    List<String> names = new ArrayList<>();
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
