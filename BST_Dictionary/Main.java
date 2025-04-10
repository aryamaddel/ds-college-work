import java.util.Scanner;

class Node {
    Node left, right;
    String key, value;

    Node(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

class Dictionary {
    Node root;

    void add(String key, String value) {
        root = insert(root, key, value);
    }

    Node insert(Node node, String key, String value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    void delete(String key) {
        root = remove(root, key);
    }

    Node remove(Node node, String key) {
        if (node == null)
            return null;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            Node temp = minValueNode(node.right);
            node.key = temp.key;
            node.value = temp.value;
            node.right = remove(node.right, temp.key);
        }
        return node;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    void display() {
        inorder(root);
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.key + ": " + node.value);
            inorder(node.right);
        }
    }

    String search(String key) {
        Node result = searchNode(root, key);
        return result != null ? result.value : null;
    }

    Node searchNode(Node node, String key) {
        if (node == null || node.key.equals(key)) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return searchNode(node.left, key);
        }
        return searchNode(node.right, key);
    }

    void update(String key, String newValue) {
        Node node = searchNode(root, key);
        if (node != null) {
            node.value = newValue;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add  2. Delete  3. Display  4. Search  5. Update  6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    String key = scanner.nextLine();
                    System.out.print("Enter value: ");
                    String value = scanner.nextLine();
                    dict.add(key, value);
                    break;
                case 2:
                    System.out.print("Enter key to delete: ");
                    String delKey = scanner.nextLine();
                    dict.delete(delKey);
                    break;
                case 3:
                    dict.display();
                    break;
                case 4:
                    System.out.print("Enter key to search: ");
                    String searchKey = scanner.nextLine();
                    String result = dict.search(searchKey);
                    System.out.println(result != null ? result : "Not found");
                    break;
                case 5:
                    System.out.print("Enter key to update: ");
                    String updateKey = scanner.nextLine();
                    System.out.print("Enter new value: ");
                    String newValue = scanner.nextLine();
                    dict.update(updateKey, newValue);
                    break;
                case 6:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}