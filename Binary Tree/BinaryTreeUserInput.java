import java.util.Scanner;

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

    public void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.data + " ");
            inOrderTraversal(node.right);
        }
    }

    public void PreOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
        }
    }

    public void PostOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            inOrderTraversal(node.right);
            System.out.print(node.data + " ");
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

        System.out.println("In-order Traversal of the Tree:");
        tree.inOrderTraversal(tree.root);

        System.out.println("\nPre-order Traversal of the Tree:");
        tree.PreOrderTraversal(tree.root);

        System.out.println("\nPost-order Traversal of the Tree:");
        tree.PostOrderTraversal(tree.root);

        scanner.close();
    }
}