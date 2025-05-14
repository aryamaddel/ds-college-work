import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ContactDatabase contactDB = new ContactDatabase(100);

    public static void main(String[] args) {
        int choice;
        
        do {
            System.out.println("\n===== CONTACT DATABASE MENU =====");
            System.out.println("1. Add a new client");
            System.out.println("2. Display all clients");
            System.out.println("3. Search for a client");
            System.out.println("4. Delete a client");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addNewClient();
                    break;
                case 2:
                    contactDB.displayAllClients();
                    break;
                case 3:
                    searchClient();
                    break;
                case 4:
                    deleteClient();
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        
        scanner.close();
    }

    private static void addNewClient() {
        System.out.println("\n===== ADD NEW CLIENT =====");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter mobile number: ");
        String mobile = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        contactDB.addClient(name, mobile, email, address);
    }

    private static void searchClient() {
        System.out.println("\n===== SEARCH CLIENT =====");
        System.out.print("Enter mobile number to search: ");
        String mobile = scanner.nextLine();
        contactDB.searchClient(mobile);
    }

    private static void deleteClient() {
        System.out.println("\n===== DELETE CLIENT =====");
        System.out.print("Enter mobile number to delete: ");
        String mobile = scanner.nextLine();
        contactDB.deleteClient(mobile);
    }
}