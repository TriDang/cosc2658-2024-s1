package test2_real;

public class TriStudentSet {
  Student[] std;
  public TriStudentSet(Student[] std) {
    this.std = std;
  }

  // O(N)
  public int countGrade(String grade) {
    int res = 0;
    for (int i = 0; i < std.length; i++) {
      if (std[i].grade.equals(grade)) {
        res++;
      }
    }
    return res;
  }

  // distribution sort
  // O(N)
  public Student[] sortByGrade() {
    int totalGrade = 5;  // there are five grades
    int[] freq = new int[totalGrade];  // frequency array
    Student[] res = new Student[std.length];  // result array

    // freq counting
    for (int i = 0; i < std.length; i++) {
      freq[std[i].grade2Rank()]++;
    }

    // calculate cumulative freq
    for (int i = 1; i < totalGrade; i++) {
      freq[i] = freq[i-1] + freq[i];
    }

    // distribution step (right to left)
    for (int i = std.length - 1; i >= 0; i--) {
      // create a copy of student at i
      Student copy = new Student(std[i].id, std[i].name, std[i].GPA, std[i].grade);
      // distribute to the correct location
      res[freq[std[i].grade2Rank()] - 1] = copy;  // freq[arr[i]] - 1 because this is zero-based index
      freq[std[i].grade2Rank()]--;
    }
    return res;
  }

  public static void main(String[] args) {
    Student[] std = new Student[] {
      new Student(1, "Alice", 4.4, "HD"),
      new Student(2, "Bob", 4.1, "DI"),
      new Student(3, "Carol", 4.3, "PA"),
      new Student(4, "David", 4.7, "HD"),
      new Student(5, "Emma", 4.2, "PA"),
      new Student(6, "Finley", 4.7, "CR")
    };
    TriStudentSet program = new TriStudentSet(std);
    System.out.println("HD count: " + program.countGrade("HD"));
    System.out.println("DI count: " + program.countGrade("DI"));
    System.out.println("NN count: " + program.countGrade("NN"));
    // before sort
    System.out.println("Array before sort");
    for (int i = 0; i < std.length; i++) {
      System.out.println(std[i]);
    }
    // after sort
    System.out.println("Array after sort");
    Student[] std2 = program.sortByGrade();
    for (int i = 0; i < std2.length; i++) {
      System.out.println(std2[i]);
    }
    // verify there is no side effect
    System.out.println("Original array");
    for (int i = 0; i < std.length; i++) {
      System.out.println(std[i]);
    }
  }
}

class Student {
  int id;
  String name;
  double GPA;
  String grade;

  public Student(int id, String name, double GPA, String grade) {
    this.id = id;
    this.name = name;
    this.GPA = GPA;
    this.grade = grade;
  }

  // grade to rank
  // HD => 0
  // DI => 1
  // CR => 2
  // PA => 3
  // NN => 4
  public int grade2Rank() {
    if (grade.equals("HD")) {
      return 0;
    }
    if (grade.equals("DI")) {
      return 1;
    }
    if (grade.equals("CR")) {
      return 2;
    }
    if (grade.equals("PA")) {
      return 3;
    }
    return 4;
  }

  @Override
  public String toString() {
    return String.format("%d - %s - %.2f - %s", id, name, GPA, grade);
  }
}
