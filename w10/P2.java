package w10;

import java.util.Random;

public class P2 {
  public static void main(String[] args) {
    int SIZE = 10;
    int[] numbers = new int[SIZE];
    Random rnd = new Random(SIZE);
    for (int i = 0; i < SIZE; i++) {
      numbers[i] = rnd.nextInt(20);
    }

    // array content before sort
    System.out.println("Before sorting:");
    for (int i = 0; i < SIZE; i++) {
      System.out.println(numbers[i]);
    }

    // heap sort
    BinaryHeap heap = new BinaryHeap();
    heap.buildHeap(numbers);
    int n = SIZE - 1;
    while (!heap.isEmpty()) {
      numbers[n] = heap.extractMax();
      n--;
    }

    // after sort
    System.out.println("=====Heap Sort Result=====");
    for (int i = 0; i < SIZE; i++) {
      System.out.println(numbers[i]);
    }
    System.out.println("==========");

    // insert random values into the heap
    for (int i = 0; i < 10; i++) {
      int t = rnd.nextInt(20);
      System.out.println("Insert: " + t);
      heap.insert(t);
    }

    // extract max continously
    System.out.println("Extraction Order");
    while (!heap.isEmpty()) {
      System.out.println(heap.extractMax());       
    }
  }
}

class BinaryHeap {
  int MAX_SIZE = 100;
  int size;  // actual number of elements in the heap
  int[] heap;  // change int[] to the type of nodes you want to store

  public BinaryHeap() {
    heap = new int[MAX_SIZE];
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  // construct a heap from an array
  public void buildHeap(int[] arr) {
    // first, copy the values from array to heap
    // and set the size accordingly
    size = arr.length;
    for (int i = 0; i < size; i++) {
      heap[i] = arr[i];
    }

    // Note 1: we apply the heapify process to all internal nodes
    // because leaf nodes are heap already

    // Note 2: the number of internal nodes in a complete binary tree
    // of n nodes is floor(n/2)
    // https://en.wikipedia.org/wiki/Binary_tree#Properties_of_binary_trees

    for (int i = size / 2; i >= 0; i--) {
      heapify(i);
    }
  }

  // heapify the tree whose root node has index nodeIdx
  // precondition: its two subtrees are heap already
  public void heapify(int nodeIdx) {
    // index of left child 2 * nodeIdx + 1
    // index of right child 2 * nodeIdx + 2    

    // if this node > left and right childdren => heap already
    // otherwise, exchange it with the max(left, right)
    int biggestIdx = nodeIdx;
    int left = 2 * nodeIdx + 1;
    int right = 2 * nodeIdx + 2;
    // left child
    if (left < size && heap[left] > heap[biggestIdx]) {
      biggestIdx = left;
    }
    // right child
    if (right < size && heap[right] > heap[biggestIdx]) {
      biggestIdx = right;
    }
    // the element at nodeIdx is the maximum
    if (biggestIdx == nodeIdx) {
      return;
    }
    // otherwise, swap the element at nodeIdx with its maximum child
    int temp = heap[nodeIdx];
    heap[nodeIdx] = heap[biggestIdx];
    heap[biggestIdx] = temp;
    // recursively call heapify to the maximum child of nodeIdx
    heapify(biggestIdx);
  }

  // extract the maximum element in a heap
  public int extractMax() {
    // first, store the value of root
    int temp = heap[0];

    // now, copy the last node to root to maintain the SHAPE property
    heap[0] = heap[size - 1];

    // decrease the size
    size--;

    // make the remaining array a heap by calling heapify with the new root
    heapify(0);

    return temp;
  }

  // insert a new element
  // the element must be inserted at the end
  // and it will raise up if necessary to maintain ORDER property
  public void insert(int n) {
    // first, increase the size    
    size++;
    heap[size - 1] = n;

    // push n up until it reach the correct location
    // or reach the root
    // remember: for a node of index i, its parent index is: (i - 1) / 2
    int current = size - 1;
    int parent = (current - 1) / 2;

    while (current > 0 && heap[parent] < heap[current]) {
      // swap
      int t = heap[current];
      heap[current] = heap[parent];
      heap[parent] = t;

      // continue
      current = parent;
      parent = (current - 1) / 2;
    }
  }
}
