import java.util.Scanner;
import java.util.Stack;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    Node root;
    Scanner scanner;

    public BinaryTree(Scanner scanner) {
        this.scanner = scanner;
    }

    public void insert(int data) {
        if (root == null) {
            root = new Node(data);
            System.out.println("Inserted as root node.");
            return;
        }

        Node current = root;
        OUTER: while (true) {
            System.out.print("Enter direction (L/R) from " + current.data + ": ");
            String direction = scanner.next();
            switch (direction) {
                case "L" -> {
                    if (current.left == null) {
                        current.left = new Node(data);
                        System.out.println("Inserted at left of " + current.data);
                        break OUTER;
                    } else {
                        current = current.left;
                    }
                }
                case "R" -> {
                    if (current.right == null) {
                        current.right = new Node(data);
                        System.out.println("Inserted at right of " + current.data);
                        break OUTER;
                    } else {
                        current = current.right;
                    }
                }
                default -> System.out.println("Invalid direction! Enter either 'L' or 'R'.");
            }
        }
    }

    // Recursive traversals
    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    public void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    public void postOrderTraversal(Node node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Iterative traversals
    public void iterativeInOrderTraversal(Node root) {
        if (root == null) return;
        
        Stack<Node> stack = new Stack<>();
        Node current = root;
        
        while (current != null || !stack.isEmpty()) {
            // Reach the leftmost node
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            // Process current node
            current = stack.pop();
            System.out.print(current.data + " ");
            
            // Move to right subtree
            current = current.right;
        }
    }
    
    public void iterativePreOrderTraversal(Node root) {
        if (root == null) return;
        
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            // Pop and process current node
            Node current = stack.pop();
            System.out.print(current.data + " ");
            
            // Push right child first so that left is processed first
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }
    
    public void iterativePostOrderTraversal(Node root) {
        if (root == null) return;
        
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        
        stack1.push(root);
        
        // Fill stack2 in post-order
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
        
        // Print nodes in post-order
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }
}

public class BinaryTreeUserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree(scanner);

        System.out.print("Enter number of nodes: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter value for node: ");
            int value = scanner.nextInt();
            tree.insert(value);
        }

        System.out.println("\n--- Recursive Traversals ---");
        System.out.println("In-order Traversal:");
        tree.inOrderTraversal(tree.root);

        System.out.println("\nPre-order Traversal:");
        tree.preOrderTraversal(tree.root);

        System.out.println("\nPost-order Traversal:");
        tree.postOrderTraversal(tree.root);
        
        System.out.println("\n\n--- Non-Recursive Traversals ---");
        System.out.println("In-order Traversal (Iterative):");
        tree.iterativeInOrderTraversal(tree.root);

        System.out.println("\nPre-order Traversal (Iterative):");
        tree.iterativePreOrderTraversal(tree.root);

        System.out.println("\nPost-order Traversal (Iterative):");
        tree.iterativePostOrderTraversal(tree.root);

        scanner.close();
    }
}