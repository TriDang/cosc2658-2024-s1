package test1_sample;

public class StudentBST {
  StudentNode root;

  public StudentBST() {
    root = null;
  }

  // addStudent complexity = O(lg(N)) (because the tree is balanced)
  public void addStudent(Student student) {
    if (root == null) {
      root = new StudentNode(student);
      return;
    }
    StudentNode parent = null;
    StudentNode current = root;

    while (current != null) {
      if (student.GPA < current.data.GPA) {
        parent = current;
        current = current.left;
      } else {
        parent = current;
        current = current.right;
      }
    }
    StudentNode node = new StudentNode(student);
    node.parent = parent;
    if (student.GPA < parent.data.GPA) {
      parent.left = node;
    } else {
      parent.right = node;
    }
  }

  // utility method: return the StudentNode containing the given Student object
  // getStudentNode complexity = O(lg(N))
  private StudentNode getStudentNode(Student student) {
    StudentNode node = root;
    // student object must exist in the tree
    // so, no need to check for null
    while (node.data.id != student.id) {
      if (student.GPA < node.data.GPA) {
        node = node.left;
      } else {
        node = node.right;
      }
    }
    return node;
  }

  
  public Student nextStudentEasy(Student student) {
    StudentNode node = getStudentNode(student);

    // go to the left-most child of the right sub-tree
    StudentNode child = node.right;
    while (child.left != null) {
      child = child.left;
    }
    return child.data;
  }

  // nextStudent complexity = O(lg(N))
  // you can also use in-order traversal to create
  // an array of students in the ascending order of GPA
  // then, use that array to answer this method.
  // But that approach is not efficient because the complexity is O(N)
  public Student nextStudent(Student student) {
    StudentNode node = getStudentNode(student);

    // if there is a right sub-tree => it is
    // the left-most node in the right sub-tree
    if (node.right != null) {
      node = node.right;
      while (node.left != null) {
        node = node.left;
      }
      return node.data;
    }

    // let's call this current node X
    // let's call the next higher node Y
    // if Y exist, X must be the right-most child of the left subtree of Y
    // AND Y's level must be nearest to X's level
    // so, let go back (i.e., follow the parent pointer/reference)
    // until we can find the first node Z such that Z.left contain a node
    // in the path traversed so far
    // if we cannot find any Z until root => there is no node that is higher than X
    while (node.parent != null) {
      if (node.parent.left == node) {
        return node.parent.data;
      }
      node = node.parent;
    }
    return null;
  }

  public static void main(String[] args) {
    StudentBST tree = new StudentBST();
    Student s1 = new Student(1, "A", 70.0);
    Student s2 = new Student(2, "B", 65.0);
    Student s3 = new Student(3, "C", 80.0);
    Student s4 = new Student(4, "D", 78.0);
    Student s5 = new Student(5, "E", 68.0);

    tree.addStudent(s1);
    tree.addStudent(s2);
    tree.addStudent(s3);
    tree.addStudent(s4);
    tree.addStudent(s5);

    System.out.println(tree.nextStudent(s1));
    System.out.println(tree.nextStudent(s5));
    System.out.println(tree.nextStudent(s3));
    System.out.println(tree.nextStudent(s4));
  }
}

class Student {
  int id;
  String name;
  double GPA;

  public Student(int id, String name, double GPA) {
    this.id = id;
    this.name = name;
    this.GPA = GPA;
  }

  @Override
  public String toString() {
    return "id: " + id + ", name: " + name + ", GPA: " + GPA;
  }
}

class StudentNode {
  Student data;
  StudentNode parent, left, right;

  public StudentNode(Student student) {
    data = student;
    parent = left = right = null;
  }
}
