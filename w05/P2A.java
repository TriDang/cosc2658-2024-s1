package w05;

public class P2A {
  public static void main(String[] args) {
    RMITStudentCollectionChaining collection = new RMITStudentCollectionChaining(13);

    // all student records have the same hash value
    // and will be in the same list
    collection.put(new RMITStudent("S123", "Alice", "IT", 3.6));
    collection.put(new RMITStudent("S321", "Bob", "SE", 4.0));
    collection.put(new RMITStudent("S231", "Carol", "CS", 3.8));
    System.out.println(collection.get("S123"));  // Alice
    System.out.println(collection.get("S231"));  // Carol
    System.out.println(collection.get("S213"));  // null

    // Verify if 12 out of 13 slots in the hash table are null
    int nullCount = 0;
    for (int i = 0; i < 13; i++) {
      if (collection.hashTable[i] == null) {
        nullCount++;
      }
    }
    System.out.println("Null count: " + nullCount);  // must be 12

    // add another student, this time let's try a different hash
    collection.put(new RMITStudent("S124", "Dang", "IS", 3.5));

    // Verify if 11 out of 13 slots in the hash table are null
    nullCount = 0;
    for (int i = 0; i < 13; i++) {
      if (collection.hashTable[i] == null) {
        nullCount++;
      }
    }
    System.out.println("Null count: " + nullCount);  // must be 11 now

    // let's remove one of the student and try to get that student again
    collection.remove("S123");
    // let access that student again, must be null now
    System.out.println(collection.get("S123"));  // null
  }
}

class RMITStudentCollectionChaining {
  int N;  // size of the array for the hash table
  RMITStudentList[] hashTable;

  public RMITStudentCollectionChaining(int tableSize) {
    N = tableSize;
    hashTable = new RMITStudentList[N];
  }

  int hashCharacter(char c) {
    String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";    
    return str.indexOf(c);
  }

  int hashString(String s) {
    int res = 0;
    for (int i = 0; i < s.length(); i++) {
      res += hashCharacter(s.charAt(i));
    }
    return res % N;
  }

  boolean put(RMITStudent s) {
    int hash = hashString(s.studentId);
    // no list at this location?
    if (hashTable[hash] == null) {
      hashTable[hash] = new RMITStudentList();
    }
    return hashTable[hash].insert(s);
  }

  RMITStudent get(String studentId) {
    int hash = hashString(studentId);
    if (hashTable[hash] == null) {
      return null;
    }
    return hashTable[hash].get(studentId);
  }

  boolean remove(String studentId) {
    int hash = hashString(studentId);
    if (hashTable[hash] == null) {
      return false;
    }
    return hashTable[hash].remove(studentId);
  }
}

// Note: the LinkedList class developed in week 3
// can be used instead of constructing a new class
class RMITStudentList {
  RMITStudentNode head;
  int size;

  public RMITStudentList() {
    head = null;
    size = 0;
  }

  // insert a student node at the end
  // because we have to check for duplicated student Id
  public boolean insert(RMITStudent student) {
    if (size == 0) {
      head = new RMITStudentNode(student);
      size = 1;
      return true;
    }
    RMITStudentNode parent = null;
    RMITStudentNode current = head;
    while (current != null) {
      if (current.data.studentId.equals(student.studentId)) {
        // duplicated id
        return false;
      }
      parent = current;
      current = current.next;
    }
    parent.next = new RMITStudentNode(student);
    size++;
    return true;
  }

  // return a student with the provided id
  public RMITStudent get(String studentId) {
    RMITStudentNode node = head;
    while (node != null) {
      if (node.data.studentId.equals(studentId)) {
        return node.data;
      }
      node = node.next;
    }
    return null;
  }

  // remove a student with a provided student id
  // return true or false if the remove is successful or not
  public boolean remove(String studentId) {
    if (size == 0) {
      return false;
    }
    RMITStudentNode parent = null;
    RMITStudentNode current = head;
    while (current != null) {
      if (current.data.studentId.equals(studentId)) {
        if (current == head) {
          // remove head => need to update head
          head = head.next;
          size--;
          return true;
        }
        parent.next = current.next;
        size--;
        return true;
      }
      parent = current;
      current = current.next;
    }
    return false;
  }
}

class RMITStudentNode {
  RMITStudent data;
  RMITStudentNode next;

  public RMITStudentNode(RMITStudent s) {
    data = s;
    next = null;
  }
}
