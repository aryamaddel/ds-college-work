class HashNode {
    String key;
    Client value;
    HashNode next;

    public HashNode(String key, Client value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}