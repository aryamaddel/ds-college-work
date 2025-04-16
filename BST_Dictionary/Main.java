import java.util.Scanner;
import java.util.Stack;

class Node {
    Node left, right;
    String word, meaning;

    Node(String key, String value) {
        this.word = key;
        this.meaning = value;
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
        if (key.compareTo(node.word) < 0) {
            node.left = insert(node.left, key, value);
        } else if (key.compareTo(node.word) > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.meaning = value;
        }
        return node;
    }

    void delete(String key) {
        root = remove(root, key);
    }

    Node remove(Node node, String key) {
        if (node == null)
            return null;
        if (key.compareTo(node.word) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.word) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;
            Node temp = minValueNode(node.right);
            node.word = temp.word;
            node.meaning = temp.meaning;
            node.right = remove(node.right, temp.word);
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

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.word + ": " + node.meaning);
            inorder(node.right);
        }
    }

    void preorder(Node node) {
        if (node != null) {
            System.out.println(node.word + ": " + node.meaning);
            preorder(node.left);
            preorder(node.right);
        }
    }

    void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.word + ": " + node.meaning);
        }
    }

    void iterativeInorder() {
        if (root == null) return;
        
        Stack<Node> stack = new Stack<>();
        Node current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            System.out.println(current.word + ": " + current.meaning);
            
            current = current.right;
        }
    }
    
    void iterativePreorder() {
        if (root == null) return;
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.println(current.word + ": " + current.meaning);
            
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }
    
    void iterativePostorder() {
        if (root == null) return;
        
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node current = stack1.pop();
            stack2.push(current);
            
            if (current.left != null) {
                stack1.push(current.left);
            }
            if (current.right != null) {
                stack1.push(current.right);
            }
        }
        
        while (!stack2.isEmpty()) {
            Node node = stack2.pop();
            System.out.println(node.word + ": " + node.meaning);
        }
    }

    String search(String key) {
        Node result = searchNode(root, key);
        return result != null ? result.meaning : null;
    }

    Node searchNode(Node node, String key) {
        if (node == null || node.word.equals(key)) {
            return node;
        }
        if (key.compareTo(node.word) < 0) {
            return searchNode(node.left, key);
        }
        return searchNode(node.right, key);
    }

    void update(String key, String newValue) {
        Node node = searchNode(root, key);
        if (node != null) {
            node.meaning = newValue;
        }
    }

    void displayAllRecursiveTraversals() {
        System.out.println("\n--- Inorder Traversal ---");
        inorder(root);
        System.out.println("\n--- Preorder Traversal ---");
        preorder(root);
        System.out.println("\n--- Postorder Traversal ---");
        postorder(root);
    }
    
    void displayAllIterativeTraversals() {
        System.out.println("\n--- Iterative Inorder Traversal ---");
        iterativeInorder();
        System.out.println("\n--- Iterative Preorder Traversal ---");
        iterativePreorder();
        System.out.println("\n--- Iterative Postorder Traversal ---");
        iterativePostorder();
    }
}

public class Main {
    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== BST Dictionary Menu =====");
            System.out.println("1. Add Word");
            System.out.println("2. Delete Word");
            System.out.println("3. Search Word");
            System.out.println("4. Update Word");
            System.out.println("5. Display Dictionary (Traversals)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter word: ");
                    String key = scanner.nextLine();
                    System.out.print("Enter meaning: ");
                    String value = scanner.nextLine();
                    dict.add(key, value);
                    break;
                case 2:
                    System.out.print("Enter word to delete: ");
                    String delKey = scanner.nextLine();
                    dict.delete(delKey);
                    break;
                case 3:
                    System.out.print("Enter word to search: ");
                    String searchKey = scanner.nextLine();
                    String result = dict.search(searchKey);
                    if (result != null) {
                        System.out.println("Meaning: " + result);
                    } else {
                        System.out.println("Word not found in dictionary");
                    }
                    break;
                case 4:
                    System.out.print("Enter word to update: ");
                    String updateKey = scanner.nextLine();
                    System.out.print("Enter new meaning: ");
                    String newValue = scanner.nextLine();
                    dict.update(updateKey, newValue);
                    break;
                case 5:
                    System.out.print("Use recursive traversals? (y/n): ");
                    String traversalChoice = scanner.nextLine().toLowerCase();
                    
                    if (traversalChoice.startsWith("y")) {
                        dict.displayAllRecursiveTraversals();
                    } else {
                        dict.displayAllIterativeTraversals();
                    }
                    break;
                case 6:
                    System.out.println("Exiting dictionary application. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}