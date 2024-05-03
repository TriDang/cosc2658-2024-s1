package w09;

import w03.LinkedListQueue;

public class P4 {
  public static void main(String[] args) {
    String[] courses = new String[] {
      "Intro to Programming",
      "Further Programming",
      "Algorithms",
      "Database Applications"
    };
    int[][] requires = new int[][] {
      {0, 0, 0, 0},
      {1, 0, 1, 0},
      {0, 0, 0, 1},
      {1, 0, 0, 0}
    };

    String[] learningOrder = topoSort(courses, requires);
    print(learningOrder);
  }

  static class Course {
    String name;
    int index;
    int inDegree;
    boolean visited;

    public Course(String n, int i) {
      name = n;
      index = i;
      inDegree = 0;
      visited = false;
    }

    public void increaseDegree() {
      inDegree++;
    }

    public void decreaseDegree() {
      inDegree--;
    }

    public boolean isSource() {
      return (inDegree == 0);
    }
  }

  static String[] topoSort(String[] courseNames, int[][] requires) {
    // initialization
    int n = courseNames.length;
    String[] res = new String[n];
    Course[] courses = new Course[n];
    LinkedListQueue<Course> queue = new LinkedListQueue<>();

    // course objects creation
    for (int i = 0; i < n; i++) {
      courses[i] = new Course(courseNames[i], i);
      // indegree calculation
      for (int j = 0; j < n; j++) {
        if (requires[i][j] != 0) {
          courses[i].increaseDegree();
        }
      }
    }

    for (int i = 0; i < n; i++) {
      if (courses[i].isSource()) {
        queue.enQueue(courses[i]);
        courses[i].visited = true;
      }
    }

    int p = 0;
    while (!queue.isEmpty()) {
      Course u = queue.peekFront();
      queue.deQqueue();
      res[p++] = u.name;
      int source = u.index;
      for (int target = 0; target < n; target++) {
        if (requires[target][source] != 0) {
          if (!courses[target].visited) {
            courses[target].decreaseDegree();
            if (courses[target].isSource()) {
              queue.enQueue(courses[target]);
              courses[target].visited = true;
            }
          }
        }
      }
    }
    if (p < n) {
      System.out.println("Cannot take all courses");
    }
    return res;
  }

  static void print(String[] arr) {
    System.out.println(String.join(" > ", arr));
  }
}
