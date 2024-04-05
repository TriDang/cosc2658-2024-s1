package w05;

public class P2B {
  public static void main(String[] args) {
    RMITStudentCollectionLinearProbing collection = new RMITStudentCollectionLinearProbing(13);

    // all student records have the same hash value (why?)
    collection.put(new RMITStudent("S123", "Alice", "IT", 3.6));
    collection.put(new RMITStudent("S321", "Bob", "SE", 4.0));
    collection.put(new RMITStudent("S231", "Carol", "CS", 3.8));
    System.out.println(collection.get("S123"));  // Alice
    System.out.println(collection.get("S231"));  // Carol
    System.out.println(collection.get("S213"));  // null
  }
}

class RMITStudentCollectionLinearProbing {
  int N;  // size of the array for the hash table
  int size;  // the actual number of elements in the hash table
  RMITStudent[] hashTable;

  public RMITStudentCollectionLinearProbing(int tableSize) {
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
    while (hashTable[hash] != null) {
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
    int probes = 0;  // number of probes?

    while (hashTable[hash] != null) {
      // is this the correct student?
      if (hashTable[hash].studentId.equals(studentId)) {
        return hashTable[hash];
      }
      // try the next position
      hash = (hash + 1) % N;
      probes++;
      if (probes >= N) {
        // hash table is full, but the search key does not exist
        return null;
      }
    }
    return null;
  }
}