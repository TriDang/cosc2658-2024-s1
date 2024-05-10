package w10;

import w03.LinkedListQueue;
import w03.LinkedList;

public class P3 {
  public static void main(String[] args) {
    SearchState begin = new SearchState(0, 0, null, null);
    WaterJugs.BFS(begin, 2);
  }
}

class WaterJugs {
  // Solving the water jug problem with BFS
  static void BFS(SearchState begin, int target) {
    LinkedListQueue<SearchState> queue = new LinkedListQueue<SearchState>();
    boolean[][] visited = new boolean[SearchState.FIRST_MAX + 1][SearchState.SECOND_MAX + 1];
  
    queue.enQueue(begin);
    visited[begin.first][begin.second] = true;
  
    while (!queue.isEmpty()) {
      SearchState current = queue.peekFront();
      queue.deQqueue();
      if (current.first == target || current.second == target) {
        // construct the solution from current to the beginning
        showSolution(current, begin);
        return;
      }
      LinkedList<SearchState> nextStates = current.generate();
      nextStates.reset();
      while (nextStates.hasNext()) {
        SearchState state = nextStates.next();
        if (visited[state.first][state.second]) {
          continue;
        }
        queue.enQueue(state);
        visited[state.first][state.second] = true;
      }
    }
    System.out.println("No solution");
  }

  static void showSolution(SearchState current, SearchState beginState) {
    StringBuilder solution = new StringBuilder();
    while (!current.equals(beginState)) {
      solution.insert(0, String.format("\n%s to reach (%d, %d)", current.howto, current.first, current.second));
      current = current.parent;
    }
    // insert begin state
    solution.insert(0, String.format("Begin state (%d, %d)", beginState.first, beginState.second));
    System.out.println(solution);
  }
}

// a search state: amount of water in two jugs
class SearchState {
  // capacity of the two water jugs
  public static final int FIRST_MAX = 3;
  public static final int SECOND_MAX = 4;
  int first, second;  // amount of water in the two jugs
  SearchState parent;  // the previous state
  String howto;  // how to reach to this state from the previous state
  
  public SearchState(int f, int s, SearchState p, String h) {
    first = f;
    second = s;
    parent = p;
    howto = h;
  }
  
  // generate new states from the current state
  public LinkedList<SearchState> generate() {
    LinkedList<SearchState> nextStates = new LinkedList<>();
    // empty first
    if (first > 0) {
      nextStates.insertAt(nextStates.size(), new SearchState(0, second, this, "Empty First"));
    }
    
    // empty second
    if (second > 0) {
      nextStates.insertAt(nextStates.size(), new SearchState(first, 0, this, "Empty Second"));
    }
    
    // fill first
    if (first < FIRST_MAX) {
      nextStates.insertAt(nextStates.size(), new SearchState(FIRST_MAX, second, this, "Fill First"));
    }
    
    // fill second
    if (second < SECOND_MAX) {
      nextStates.insertAt(nextStates.size(), new SearchState(first, SECOND_MAX, this, "Fill Second"));
    }
    
    // pour first to second
    if (first > 0) {
      // first becomes empty first OR second becomes full first
      if (first + second <= SECOND_MAX) {
        nextStates.insertAt(nextStates.size(), new SearchState(0, first + second, this, "Pour First to Second"));
      } else {
        nextStates.insertAt(nextStates.size(), new SearchState(first + second - SECOND_MAX, SECOND_MAX, this, "Pour First to Second"));
      }
    }
    
    // pour second to first
    if (second > 0) {
      // second becomes empty first OR first becomes full first
      if (first + second <= FIRST_MAX) {
        nextStates.insertAt(nextStates.size(), new SearchState(first + second, 0, this, "Pour Second to First"));
      } else {
        nextStates.insertAt(nextStates.size(), new SearchState(FIRST_MAX, first + second - FIRST_MAX, this, "Pour Second to First"));
      }
    }    
    return nextStates;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof SearchState)) {
      return false;
    }
    SearchState otherState = (SearchState)other;
    return (first == otherState.first && second == otherState.second);
  }
}
