package w03;

public class P4 {
  static boolean isBalanced(String s) {
    LinkedListStack<Character> st = new LinkedListStack<Character>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '[' || c == '(' || c == '{') {
        st.push(c);
        continue;
      }
      if (st.isEmpty()) {
        return false;
      }
      char c2 = st.peek();
      st.pop();
      if ((c == ']' && c2 != '[') ||
          (c == ')' && c2 != '(') ||
          (c == '}' && c2 != '{')) {
        return false;
      }
    }
    if (!st.isEmpty()) {
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("[]: " + isBalanced("[]"));  // true
    System.out.println("[](){}: " + isBalanced("[](){}"));  // true
    System.out.println("{[]}(()): " + isBalanced("{[]}(())"));  // true
    System.out.println("][)(}{: " + isBalanced("][)(}{"));  // false
    System.out.println("(((((((((()))))))))}: " + isBalanced("(((((((((()))))))))}"));  // false
  }
}
