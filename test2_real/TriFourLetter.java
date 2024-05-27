package test2_real;

import w03.ArrayList;
import w03.LinkedListQueue;

public class TriFourLetter {
  String start;
  static final String target = "RMIT";
  static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  public TriFourLetter(String s) {
    start = s;
  }

  // O(1)
  // For each letter, if it is the same as the letter 
  // of the same position in the target RMIT, skip it
  // Otherwise, it can be replaced by the next or previous letter
  // in the alphabet repeatedly, until it equals the letter of the target RMIT
  // The calculation here is constant, the number of letters (4) is also constant
  // So, this algorithm has a complexity O(1)
  public int freeTransform() {
    int step = 0;
    for (int i = 0; i < start.length(); i++) {
      // position in the alphabet of the source letter
      int p1 = alphabet.indexOf(start.charAt(i));
      // position in the alphabet of the target letter
      int p2 = alphabet.indexOf(target.charAt(i));
      // forward distance
      int d1 = Math.abs(p2 - p1);
      // backward distance
      int d2 = alphabet.length() - d1;
      // pick the smaller one
      step += Math.min(d1, d2);
    }
    return step;
  }

  // O(N ^ 4)
  // there are N values (A - Z) for each letter
  // there are 4 letters
  public int constraintTransform(String[] forbidden) {
    SearchState begin = new SearchState(start, null);
    return BFS(begin, forbidden);
  }

  // utility method: check if a state has been visited
  private boolean isVisited(boolean[][][][] visited, SearchState state) {
    String value = state.value;
    int idx0 = value.charAt(0) - 'A';
    int idx1 = value.charAt(1) - 'A';
    int idx2 = value.charAt(2) - 'A';
    int idx3 = value.charAt(3) - 'A';
    return visited[idx0][idx1][idx2][idx3];
  }

  // utility method: set a stated as visited
  private void setVisited(boolean[][][][] visited, SearchState state) {
    String value = state.value;
    int idx0 = value.charAt(0) - 'A';
    int idx1 = value.charAt(1) - 'A';
    int idx2 = value.charAt(2) - 'A';
    int idx3 = value.charAt(3) - 'A';
    visited[idx0][idx1][idx2][idx3] = true;
  }

  private int BFS(SearchState begin, String[] forbidden) {
    LinkedListQueue<SearchState> queue = new LinkedListQueue<>();
    int N = alphabet.length();
    boolean[][][][] visited = new boolean[N][N][N][N];
  
    queue.enQueue(begin);
    setVisited(visited, begin);
  
    while (!queue.isEmpty()) {
      SearchState current = queue.peekFront();
      queue.deQqueue();
      if (current.value.equals(target)) {
        // construct the solution from current to the beginning
        return buildSolution(current, begin);
      }
      // generate new states
      ArrayList<SearchState> nextStates = current.generate(forbidden);
      nextStates.reset();
      while (nextStates.hasNext()) {
        SearchState state = nextStates.next();
        if (isVisited(visited, state)) {
          continue;
        }
        queue.enQueue(state);
        setVisited(visited, state);
      }
    }
    return -1;
  }

  private int buildSolution(SearchState current, SearchState beginState) {
    StringBuilder solution = new StringBuilder();
    int res = 0;
    while (!current.value.equals(beginState.value)) {
      solution.insert(0, String.format(" -> %s", current.value));
      current = current.parent;
      res++;
    }
    // insert begin state
    solution.insert(0, String.format("%s", beginState.value));
    System.out.println(solution.toString());
    return res;
  }

  public static void main(String[] args) {
    TriFourLetter program = new TriFourLetter("RMIV");
    System.out.println("Free Transform from RMIV: " + program.freeTransform());
    System.out.println("Constraint Transform from RMIV: " + program.constraintTransform(new String[]{"RMIU"}));
  }
}

// utility class
class StringGenerator {
  static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  // generate a new character from current character c
  // using the direction dir (1/-1) (forward/backward)
  public static char genChar(char c, int dir) {
    int idx = alphabet.indexOf(c);
    idx += dir;
    if (idx < 0) {
      idx += alphabet.length();
    }
    if (idx >= alphabet.length()) {
      idx = idx % alphabet.length();
    }
    return alphabet.charAt(idx);
  }

  // generate a new String from current String
  // changing the letter at index idx
  // using the direction dir (1 or -1)
  public static String genString(String current, int idx, int dir) {
    char[] chars = current.toCharArray();
    chars[idx] = genChar(chars[idx], dir);
    return String.valueOf(chars);
  }
}

// a search state: a four-letter String
class SearchState {
  String value;
  SearchState parent;  // the previous state
  
  public SearchState(String current, SearchState p) {
    value = current;
    parent = p;
  }  
  
  // generate new states from the current state
  // given the forbidden states
  public ArrayList<SearchState> generate(String[] forbidden) {
    ArrayList<SearchState> res = new ArrayList<>();
    int[] direction = {1, -1};
    for (int i = 0; i < value.length(); i++) {
      for (int j = 0; j < direction.length; j++) {
        String newState = StringGenerator.genString(value, i, direction[j]);
        // newSteate is a forbidden state?
        boolean forbid = false;
        for (int k = 0; k < forbidden.length; k++) {
          if (newState.equals(forbidden[k])) {
            forbid = true;
            break;
          }
        }
        if (!forbid) {
          res.insertAt(res.size(), new SearchState(newState, this));
        }
      }
    }
    return res;
  }
}