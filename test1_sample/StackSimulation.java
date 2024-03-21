package test1_sample;

public class StackSimulation {
  // popAll complexity = O(N)
  String[] popAll(String[] stack) {
    if (stack.length == 0) {
      return new String[0];
    }
    int N = stack.length;
    String[] result = new String[N];
    for (int i = 0; i < N; i++) {
      result[i] = stack[N - i - 1];
    }
    return result;
  }

  // Starting from left to right, we need to determine the common
  // elements in the two stacks
  // (it's possible that there is no common element)

  // Let's call the remaining target elements T1, T2, ... TN
  // let's call the remaining current elements C1, C2, ... CM
  // because T1 != C1 (otherwise; they are in the set of common elements)
  // we need to make the position currently contain C1 to contain T1
  // so, we need to remove all C1, C2, ..., CM by calling
  // pop() M times, then calling push("T1").

  // Now, in the current element list, there is no element after T1
  // So, we need one more call push("T2") to match the
  // next remaining element in target, and so on until we match TN.

  // That is another N calls of push()
  // So, the total calls needed is M + N

  // minOperations complexity = O(M + N) = O(N)
  public int minOperations(String[] targetStack, String[] currentStack) {
    int start = 0;
    while (start < targetStack.length && start < currentStack.length) {
      if (targetStack[start].equals(currentStack[start])) {
        start++;
      } else {
        break;
      }
    }
    // "start" is the first index where (target[start] != enteredRooms[start])
    // so, the remaining elements of both array start from "start"
    return (targetStack.length - start) + (currentStack.length - start);
  }

  // client code
  public static void main(String[] args) {
    StackSimulation stack = new StackSimulation();
    String[] result = stack.popAll(new String[] {"A", "B", "C"});
    System.out.println("popAll (A, B, C)");
    for (String r : result) {
      System.out.println(r);
    }
    System.out.println("Test minOperations");
    System.out.println(stack.minOperations(new String[] {}, new String[] {}));  // 0
    System.out.println(stack.minOperations(new String[] {"A"}, new String[] {}));  // 1
    System.out.println(stack.minOperations(new String[] {}, new String[] {"A"}));  // 1
    System.out.println(stack.minOperations(new String[] {"A", "B"}, new String[] {"A"}));  // 1
    System.out.println(stack.minOperations(new String[] {"A", "B"}, new String[] {"B", "A"}));  // 4
    System.out.println(stack.minOperations(new String[] {"A", "B", "C"}, new String[] {"A", "B"}));  // 1
    System.out.println(stack.minOperations(new String[] {"A", "B", "C"}, new String[] {"A", "B", "C", "D"}));  // 1
    System.out.println(stack.minOperations(new String[] {"A", "B", "C"}, new String[] {"A", "C", "B", "D"}));  // 5
  }
}
