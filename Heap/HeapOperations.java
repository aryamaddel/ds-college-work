import java.util.Scanner;

abstract class Heap {
    protected final int[] heap;
    protected int size;
    protected final int capacity;

    public Heap(int capacity, int sentinel) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity + 1];
        heap[0] = sentinel;
    }

    // Basic utility methods
    protected int parent(int pos) {
        return pos / 2;
    }

    protected int leftChild(int pos) {
        return 2 * pos;
    }

    protected int rightChild(int pos) {
        return 2 * pos + 1;
    }

    protected boolean isLeaf(int pos) {
        return pos > (size / 2) && pos <= size;
    }

    protected void swap(int fpos, int spos) {
        int tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    // Abstract methods
    public abstract void insert(int element);

    protected abstract void heapify(int pos);

    public abstract int extract();

    // Common operations
    public boolean delete(int element) {
        for (int i = 1; i <= size; i++) {
            if (heap[i] == element) {
                heap[i] = heap[size--];
                heapify(i);
                return true;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("\n" + (this instanceof MinHeap ? "Min" : "Max") + " Heap Tree Structure:");
        printHeapTree(1, 0);
        System.out.println("\nArray: " + java.util.Arrays.toString(java.util.Arrays.copyOfRange(heap, 1, size + 1)));
    }

    protected void printHeapTree(int index, int level) {
        if (index > size)
            return;
        printHeapTree(rightChild(index), level + 1);
        System.out.println("    ".repeat(level) + heap[index]);
        printHeapTree(leftChild(index), level + 1);
    }
}

class MinHeap extends Heap {
    public MinHeap(int capacity) {
        super(capacity, Integer.MIN_VALUE);
    }

    @Override
    protected void heapify(int pos) {
        if (isLeaf(pos))
            return;

        int smallest = pos;
        int left = leftChild(pos);
        int right = rightChild(pos);

        if (left <= size && heap[left] < heap[smallest])
            smallest = left;
        if (right <= size && heap[right] < heap[smallest])
            smallest = right;

        if (smallest != pos) {
            swap(pos, smallest);
            heapify(smallest);
        }
    }

    @Override
    public void insert(int element) {
        if (size >= capacity)
            return;
        heap[++size] = element;

        int current = size;
        while (current > 1 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    @Override
    public int extract() {
        if (size < 1)
            return Integer.MIN_VALUE;
        int result = heap[1];
        heap[1] = heap[size--];
        heapify(1);
        return result;
    }
}

class MaxHeap extends Heap {
    public MaxHeap(int capacity) {
        super(capacity, Integer.MAX_VALUE);
    }

    @Override
    protected void heapify(int pos) {
        if (isLeaf(pos))
            return;

        int left = leftChild(pos);
        int right = rightChild(pos);
        int largest = pos;

        // Find the largest among current node and its children
        if (left <= size && heap[left] > heap[largest])
            largest = left;
        if (right <= size && heap[right] > heap[largest])
            largest = right;

        // If largest is not current node, swap and continue heapifying
        if (largest != pos) {
            swap(pos, largest);
            heapify(largest);
        }
    }

    @Override
    public void insert(int element) {
        if (size >= capacity)
            return;

        // Insert at the end and bubble up
        heap[++size] = element;
        int current = size;

        // Bubble up while parent is smaller
        while (current > 1 && heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    @Override
    public int extract() {
        if (size < 1)
            return Integer.MAX_VALUE;

        int max = heap[1];
        heap[1] = heap[size--];
        heapify(1);
        return max;
    }
}

public class HeapOperations {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Choose: 1. Min Heap, 2. Max Heap");
            int choice = scanner.nextInt();

            System.out.println("Enter capacity:");
            int capacity = scanner.nextInt();

            Heap heap = (choice == 1) ? new MinHeap(capacity) : new MaxHeap(capacity);
            boolean isMinHeap = heap instanceof MinHeap;

            if (choice != 1 && choice != 2) {
                System.out.println("Invalid choice");
                return;
            }

            while (true) {
                String type = isMinHeap ? "Min" : "Max";
                System.out.println(
                        "\n" + type + " Heap: 1. Insert, 2. Extract " + type + ", 3. Delete, 4. Display, 5. Exit");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Insert: ");
                        heap.insert(scanner.nextInt());
                    }
                    case 2 -> System.out.println("Extracted " + (isMinHeap ? "Min" : "Max") + ": " + heap.extract());
                    case 3 -> {
                        System.out.print("Delete: ");
                        System.out.println(heap.delete(scanner.nextInt()) ? "Success" : "Not found");
                    }
                    case 4 -> heap.print();
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            }
        }
    }
}