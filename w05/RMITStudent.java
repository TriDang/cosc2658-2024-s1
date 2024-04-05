package w05;

public class RMITStudent {
  String studentId;
  String fullName;
  String major;  
  double GPA;

  public RMITStudent(String id, String name, String m, double mark) {
    studentId = id;
    fullName = name;
    major = m;
    GPA = mark;
  }

  @Override
  public String toString() {
    return "Id: " + studentId +
           ", Fullname: " + fullName +
           ", Major: " + major +
           ", GPA: " + GPA;
  }
}
