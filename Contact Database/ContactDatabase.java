class ContactDatabase {
    private CustomHashTable hashTable;

    public ContactDatabase(int capacity) {
        hashTable = new CustomHashTable(capacity);
    }

    public void addClient(String name, String mobile, String email, String address) {
        Client client = new Client(name, mobile, email, address);
        hashTable.put(mobile, client);
        System.out.println("Client added successfully!");
    }

    public void displayAllClients() {
        if (hashTable.isEmpty()) {
            System.out.println("No clients in the database.");
            return;
        }

        System.out.println("===== ALL CLIENTS =====");
        int count = 0;
        Client[] clients = hashTable.getAllValues();
        
        for (Client client : clients) {
            if (client.isActive()) {
                System.out.println("\nClient " + (++count) + ":");
                System.out.println(client);
                System.out.println("---------------------");
            }
        }
        System.out.println("Total active clients: " + count);
    }

    public void searchClient(String mobile) {
        Client client = hashTable.get(mobile);
        
        if (client == null) {
            System.out.println("Client with mobile number " + mobile + " not found.");
            return;
        }
        
        if (!client.isActive()) {
            System.out.println("Client with mobile number " + mobile + " has been deleted.");
            return;
        }
        
        System.out.println("===== CLIENT INFORMATION =====");
        System.out.println(client);
    }

    public void deleteClient(String mobile) {
        Client client = hashTable.get(mobile);
        
        if (client == null) {
            System.out.println("Client with mobile number " + mobile + " not found.");
            return;
        }
        
        if (!client.isActive()) {
            System.out.println("Client with mobile number " + mobile + " is already deleted.");
            return;
        }
        
        client.setActive(false);
        System.out.println("Client with mobile number " + mobile + " deleted successfully.");
    }
}