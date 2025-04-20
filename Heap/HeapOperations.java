import java.util.Scanner;

class MinHeap {
    private int[] heap;
    private int capacity;
    private int current_size;

    MinHeap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        current_size = 0;
    }

    private int getMin() {
        return heap[0];
    }

    private int getMax() {
        if (current_size == 0)
            throw new IllegalStateException("Heap is full");

        int max = heap[0];
        for (int val : heap)
            if (val > max)
                max = val;
        return max;
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;

    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;

    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private void insert(int value) {
        if (current_size < capacity) {
            heap[current_size] = value;
            upAjust(current_size++);
        } else
            throw new IllegalStateException("Heap is full!");
    }

    private void delete(int key) {
        if (current_size == 0) {
            throw new IllegalStateException("Heap is empty!");
        }

        heap[key] = heap[--current_size];
        downAdjust(key);
        upAjust(key);
        System.out.println("Key " + heap[key] + " deleted!");
    }

    private void update(int key, int val) {
        if (current_size > 0) {
            heap[key] = val;
            // After updating, we need to restore heap property
            downAdjust(key);
            upAjust(key);
            System.out.println("Key at index " + key + " updated to " + val);
        } else {
            throw new IllegalStateException("Heap is empty!");
        }
    }

    private void upAjust(int i) {
        while (i != 0 && heap[i] < heap[parent(i)]) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    private void downAdjust(int i) {
        int smallest = i;
        int left_child = left(i);
        int right_child = right(i);
        if (left_child < capacity && heap[left_child] < heap[smallest])
            smallest = left_child;
        if (right_child < capacity && heap[right_child] < heap[smallest])
            smallest = right_child;

        if (smallest != i) {
            swap(smallest, i);
            downAdjust(smallest);
        }
    }

    public static void main(String[] args) {
        // Create a heap with capacity 10
        MinHeap minHeap = new MinHeap(10);

        // Insert operations
        System.out.println("Inserting elements: 45, 20, 14, 12, 31, 7, 11");
        minHeap.insert(45);
        minHeap.insert(20);
        minHeap.insert(14);
        minHeap.insert(12);
        minHeap.insert(31);
        minHeap.insert(7);
        minHeap.insert(11);

        // Display heap after insertion
        System.out.println("Heap after insertion:");
        for (int i = 0; i < minHeap.current_size; i++) {
            System.out.print(minHeap.heap[i] + " ");
        }
        System.out.println();

        // Get min and max values
        System.out.println("Min value: " + minHeap.getMin());
        System.out.println("Max value: " + minHeap.getMax());

        // Delete operation
        System.out.println("Deleting element at index 2");
        minHeap.delete(2);

        // Display heap after deletion
        System.out.println("Heap after deletion:");
        for (int i = 0; i < minHeap.current_size; i++) {
            System.out.print(minHeap.heap[i] + " ");
        }
        System.out.println();

        // Update operation
        System.out.println("Updating element at index 1 to value 5");
        minHeap.update(1, 5);

        // Display heap after update
        System.out.println("Heap after update:");
        for (int i = 0; i < minHeap.current_size; i++) {
            System.out.print(minHeap.heap[i] + " ");
        }
        System.out.println();
    }

}