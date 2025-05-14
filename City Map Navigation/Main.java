import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("===== CITY MAP NAVIGATION SYSTEM =====");
        System.out.print("Enter the number of cities: ");
        int numCities = scanner.nextInt();
        scanner.nextLine();

        Graph cityMap = new Graph(numCities);

        System.out.println("\nEnter names for each city:");
        for (int i = 0; i < numCities; i++) {
            System.out.print("Name for City " + i + ": ");
            String cityName = scanner.nextLine();
            cityMap.addCity(i, cityName);
        }

        System.out.print("\nEnter the number of roads: ");
        int numRoads = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEnter road connections (city names):");
        for (int i = 0; i < numRoads; i++) {
            System.out.print("Road " + (i + 1) + " connects: ");
            String cityA = scanner.nextLine();
            System.out.print("To: ");
            String cityB = scanner.nextLine();

            int cityAId = cityMap.getCityId(cityA);
            int cityBId = cityMap.getCityId(cityB);

            if (cityAId != -1 && cityBId != -1) {
                cityMap.addEdge(cityAId, cityBId);
                System.out.println("Road added between " + cityA + " and " + cityB);
            } else {
                System.out.println("Invalid city name(s)! Please enter valid city names.");
                i--;
            }
        }

        int choice;
        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Display the city map");
            System.out.println("2. DFS traversal");
            System.out.println("3. BFS traversal");
            System.out.println("4. Check if path exists between two cities");
            System.out.println("5. Find a path between two cities");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n===== CITY MAP =====");
                    cityMap.printGraph();
                    break;
                case 2:
                    System.out.print("Enter starting city name for DFS: ");
                    String dfsStartCity = scanner.nextLine();
                    int dfsStartId = cityMap.getCityId(dfsStartCity);
                    if (dfsStartId != -1) {
                        cityMap.DFS(dfsStartId);
                    } else {
                        System.out.println("Invalid city name!");
                    }
                    break;
                case 3:
                    System.out.print("Enter starting city name for BFS: ");
                    String bfsStartCity = scanner.nextLine();
                    int bfsStartId = cityMap.getCityId(bfsStartCity);
                    if (bfsStartId != -1) {
                        cityMap.BFS(bfsStartId);
                    } else {
                        System.out.println("Invalid city name!");
                    }
                    break;
                case 4:
                    System.out.print("Enter source city name: ");
                    String sourceCity = scanner.nextLine();
                    System.out.print("Enter destination city name: ");
                    String destCity = scanner.nextLine();

                    int sourceId = cityMap.getCityId(sourceCity);
                    int destId = cityMap.getCityId(destCity);

                    if (sourceId != -1 && destId != -1) {
                        boolean pathExists = cityMap.checkPathExists(sourceId, destId);
                        if (pathExists) {
                            System.out.println("A path exists between " + sourceCity + " and " + destCity);
                        } else {
                            System.out.println("No path exists between " + sourceCity + " and " + destCity);
                        }
                    } else {
                        System.out.println("Invalid city name(s)!");
                    }
                    break;
                case 5:
                    System.out.print("Enter source city name: ");
                    String pathSourceCity = scanner.nextLine();
                    System.out.print("Enter destination city name: ");
                    String pathDestCity = scanner.nextLine();

                    int pathSourceId = cityMap.getCityId(pathSourceCity);
                    int pathDestId = cityMap.getCityId(pathDestCity);

                    if (pathSourceId != -1 && pathDestId != -1) {
                        List<Integer> path = cityMap.findPath(pathSourceId, pathDestId);
                        if (!path.isEmpty()) {
                            System.out.print("Path from " + pathSourceCity + " to " + pathDestCity + ": ");
                            for (int i = 0; i < path.size(); i++) {
                                City city = cityMap.getCity(path.get(i));
                                System.out.print(city.getName());
                                if (i < path.size() - 1) {
                                    System.out.print(" -> ");
                                }
                            }
                            System.out.println();
                        } else {
                            System.out.println("No path exists between " + pathSourceCity + " and " + pathDestCity);
                        }
                    } else {
                        System.out.println("Invalid city name(s)!");
                    }
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }
}