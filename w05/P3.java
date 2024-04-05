package w05;

public class P3 {
  public static void main(String[] args) {
    RMITStudentCollectionLinearProbing2 collection = new RMITStudentCollectionLinearProbing2(13);

    // Alice -> Bob -> Carol
    collection.put(new RMITStudent("S123", "Alice", "IT", 3.6));
    collection.put(new RMITStudent("S321", "Bob", "SE", 4.0));
    collection.put(new RMITStudent("S231", "Carol", "CS", 3.8));
    System.out.println(collection.get("S123"));  // Alice
    System.out.println(collection.get("S231"));  // Carol
    // Remove Bob - the middle element
    collection.remove("S321");
    // Check if Bob has been replaced by DELETED
    System.out.println(collection.hashTable[(collection.hashString("S321") + 1) % collection.N]);
    // Try to access Carol again
    System.out.println(collection.get("S231"));  // Carol
    // Add a new student having the same hash value
    collection.put(new RMITStudent("S312", "Dang", "IS", 3.5));
    // Check if the new student occupies the slot of Bob before
    System.out.println(collection.hashTable[(collection.hashString("S321") + 1) % collection.N]);
  }
}

class RMITStudentCollectionLinearProbing2 {
  int N;  // size of the array for the hash table
  int size;  // the actual number of elements in the hash table
  RMITStudent[] hashTable;

  // let's create a special value for DELETED
  static RMITStudent DELETED = new RMITStudent("S000", "DELETED", "NONE", 0.0);

  public RMITStudentCollectionLinearProbing2(int tableSize) {
    N = tableSize;
    size = 0;
    hashTable = new RMITStudent[N];
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
    // hash table is full
    if (size == N) {
      return false;
    }
    int hash = hashString(s.studentId);
    while (hashTable[hash] != null && hashTable[hash] != DELETED) {
      // the calculated position has been occupied (collision)
      // check if there is a duplicated id
      if (hashTable[hash].studentId.equals(s.studentId)) {
        return false;
      }
      // try the next position
      // make sure hash is not out of range
      hash = (hash + 1) % N;
    }
    hashTable[hash] = s;
    size++;
    return true;
  }

  RMITStudent get(String studentId) {
    int hash = hashString(studentId);
    while (hashTable[hash] != null) {
      // is this the correct student?
      if (hashTable[hash].studentId.equals(studentId)) {
        return hashTable[hash];
      }
      // try the next position
      hash = (hash + 1) % N;
    }
    return null;
  }

  boolean remove(String studentId) {
    int hash = hashString(studentId);
    while (hashTable[hash] != null) {
      // is this the correct student?
      if (hashTable[hash].studentId.equals(studentId)) {
        hashTable[hash] = DELETED;
        size--;
        return true;
      }
      // try the next position
      hash = (hash + 1) % N;
    }
    return false;
  }
}