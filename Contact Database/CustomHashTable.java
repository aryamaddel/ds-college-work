class CustomHashTable {
    private HashNode[] buckets;
    private int capacity;
    private int size;

    public CustomHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = new HashNode[capacity];
        this.size = 0;
    }

    private int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash + key.charAt(i) * 31) % capacity;
        }
        return hash;
    }

    public void put(String key, Client value) {
        int bucketIndex = hashFunction(key);
        HashNode head = buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        size++;
        head = buckets[bucketIndex];
        HashNode newNode = new HashNode(key, value);
        newNode.next = head;
        buckets[bucketIndex] = newNode;
    }

    public Client get(String key) {
        int bucketIndex = hashFunction(key);
        HashNode head = buckets[bucketIndex];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }

        return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public Client[] getAllValues() {
        Client[] values = new Client[size];
        int index = 0;

        for (int i = 0; i < capacity; i++) {
            HashNode current = buckets[i];
            while (current != null) {
                values[index++] = current.value;
                current = current.next;
            }
        }

        return values;
    }
}