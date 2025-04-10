import java.util.Scanner;

class MinHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1];
        heap[0] = Integer.MIN_VALUE;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    private void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void minHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (heap[pos] > heap[leftChild(pos)] ||
                    (rightChild(pos) <= size && heap[pos] > heap[rightChild(pos)])) {
                if (rightChild(pos) > size || heap[leftChild(pos)] < heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(int element) {
        if (size >= capacity)
            return;
        heap[++size] = element;
        int current = size;
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int extractMin() {
        int popped = heap[1];
        heap[1] = heap[size--];
        minHeapify(1);
        return popped;
    }

    public void print() {
        System.out.println("\nMin Heap Tree Structure:");
        printHeapTree(1, 0);
        System.out.println("\nArray Representation:");
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private void printHeapTree(int index, int level) {
        if (index > size)
            return;

        printHeapTree(rightChild(index), level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(heap[index]);

        printHeapTree(leftChild(index), level + 1);
    }
}

class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1];
        heap[0] = Integer.MAX_VALUE;
    }

    private int parent(int pos) {
        return pos / 2;
    }

    private int leftChild(int pos) {
        return 2 * pos;
    }

    private int rightChild(int pos) {
        return 2 * pos + 1;
    }

    private boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    private void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    private void maxHeapify(int pos) {
        if (!isLeaf(pos)) {
            if (heap[pos] < heap[leftChild(pos)] ||
                    (rightChild(pos) <= size && heap[pos] < heap[rightChild(pos)])) {
                if (rightChild(pos) > size || heap[leftChild(pos)] > heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    maxHeapify(leftChild(pos));
                } else {
                    swap(pos, rightChild(pos));
                    maxHeapify(rightChild(pos));
                }
            }
        }
    }

    public void insert(int element) {
        if (size >= capacity)
            return;
        heap[++size] = element;
        int current = size;
        while (heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int extractMax() {
        int popped = heap[1];
        heap[1] = heap[size--];
        maxHeapify(1);
        return popped;
    }

    public void print() {
        System.out.println("\nMax Heap Tree Structure:");
        printHeapTree(1, 0);
        System.out.println("\nArray Representation:");
        for (int i = 1; i <= size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    private void printHeapTree(int index, int level) {
        if (index > size)
            return;

        printHeapTree(rightChild(index), level + 1);

        for (int i = 0; i < level; i++) {
            System.out.print("    ");
        }
        System.out.println(heap[index]);

        printHeapTree(leftChild(index), level + 1);
    }
}

public class HeapOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose Heap Type:\n1. Min Heap\n2. Max Heap");
        int heapType = scanner.nextInt();

        System.out.println("Enter capacity of heap:");
        int capacity = scanner.nextInt();

        if (heapType == 1) {
            MinHeap minHeap = new MinHeap(capacity);
            minHeapOperations(minHeap, scanner);
        } else if (heapType == 2) {
            MaxHeap maxHeap = new MaxHeap(capacity);
            maxHeapOperations(maxHeap, scanner);
        } else {
            System.out.println("Invalid choice");
        }
        scanner.close();
    }

    private static void minHeapOperations(MinHeap minHeap, Scanner scanner) {
        while (true) {
            System.out.println("\nMin Heap Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Extract Min");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter element to insert:");
                    minHeap.insert(scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Extracted Min: " + minHeap.extractMin());
                    break;
                case 3:
                    minHeap.print();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void maxHeapOperations(MaxHeap maxHeap, Scanner scanner) {
        while (true) {
            System.out.println("\nMax Heap Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Extract Max");
            System.out.println("3. Display");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter element to insert:");
                    maxHeap.insert(scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Extracted Max: " + maxHeap.extractMax());
                    break;
                case 3:
                    maxHeap.print();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}