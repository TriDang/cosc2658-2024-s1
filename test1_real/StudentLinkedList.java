package test1_real;

import w03.LinkedList;

public class StudentLinkedList extends LinkedList<Student> {
  // O(N)
  public int add(Student std) {
    reset();
    if (!hasNext()) {
      insertAt(0, std);
      return 0;
    }
    int idx = 0;
    Student tmp = next();
    while (tmp.GPA < std.GPA) {
      idx++;
      if (!hasNext()) {
        break;
      }      
      tmp = next();
    }
    insertAt(idx, std);
    return idx;
  }

  // O(N)
  public Student nearest(double searchGPA) {
    reset();
    if (size() == 0) {
      return null;
    }
    double currentNearest = Double.MAX_VALUE;
    Student nearestStd, currentStd;
    nearestStd = currentStd = next();
    while (true) {
      if (Math.abs(currentStd.GPA - searchGPA) < currentNearest) {
        currentNearest = Math.abs(currentStd.GPA - searchGPA);
        nearestStd = currentStd;
      }
      if (nearestStd.GPA >= searchGPA) {
        break;
      }
      if (hasNext()) {
        currentStd = next();
      } else {
        break;
      }
    }
    return nearestStd;
  }

  public static void main(String[] args) {
    StudentLinkedList list = new StudentLinkedList();
    System.out.println("Add (1, Alice, 3.3): " + list.add(new Student(1, "Alice", 3.3)));  // 0
    System.out.println("Add (2, Bob, 3.6): " + list.add(new Student(2, "Bob", 3.6)));  // 1
    System.out.println("Add (3, Carol, 3.0): " + list.add(new Student(3, "Carol", 3.0)));  // 0
    System.out.println("Add (4, David, 3.5): " + list.add(new Student(4, "David", 3.5)));  // 2
    System.out.println("Add (5, Evan, 3.9): " + list.add(new Student(5, "Evan", 3.9)));  // 4

    Student tmp = list.nearest(0.1);
    System.out.printf("Neareast to 0.1 is (%d, %s, %.2f)\n", tmp.id, tmp.name, tmp.GPA);  // (3, Carol, 3.0)
    tmp = list.nearest(3.1);
    System.out.printf("Neareast to 3.1 is (%d, %s, %.2f)\n", tmp.id, tmp.name, tmp.GPA);  // (3, Carol, 3.0)
    tmp = list.nearest(3.2);
    System.out.printf("Neareast to 3.2 is (%d, %s, %.2f)\n", tmp.id, tmp.name, tmp.GPA);  // (1, Alice, 3.3)
    tmp = list.nearest(3.5);
    System.out.printf("Neareast to 3.5 is (%d, %s, %.2f)\n", tmp.id, tmp.name, tmp.GPA);  // (4, David, 3.5)
    tmp = list.nearest(4);
    System.out.printf("Neareast to 4.0 is (%d, %s, %.2f)\n", tmp.id, tmp.name, tmp.GPA);  // (5, Evan, 3.9)
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
}
