import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Graph {
    private int V;
    private LinkedList<Integer>[] adjList;
    private Map<Integer, City> cities;
    private Map<String, Integer> cityNameToId;

    public Graph(int v) {
        V = v;
        adjList = new LinkedList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new LinkedList<>();
        }
        cities = new HashMap<>();
        cityNameToId = new HashMap<>();
    }

    public void addCity(int id, String name) {
        City city = new City(id, name);
        cities.put(id, city);
        cityNameToId.put(name.toLowerCase(), id);
    }

    public int getCityId(String name) {
        return cityNameToId.getOrDefault(name.toLowerCase(), -1);
    }

    public City getCity(int id) {
        return cities.get(id);
    }

    public void addEdge(int src, int dest) {
        adjList[src].add(dest);
        adjList[dest].add(src);
    }

    public void printGraph() {
        for (int i = 0; i < V; i++) {
            City city = cities.get(i);
            System.out.print(city.getName() + " is connected to: ");
            for (Integer neighborId : adjList[i]) {
                City neighbor = cities.get(neighborId);
                System.out.print(neighbor.getName() + " ");
            }
            System.out.println();
        }
    }

    public void DFS(int startCity) {
        boolean[] visited = new boolean[V];
        System.out.println("DFS traversal starting from " + cities.get(startCity).getName() + ":");
        DFSUtil(startCity, visited);
        System.out.println();
    }

    private void DFSUtil(int cityId, boolean[] visited) {
        visited[cityId] = true;
        System.out.print(cities.get(cityId).getName() + " ");

        for (Integer neighborId : adjList[cityId]) {
            if (!visited[neighborId]) {
                DFSUtil(neighborId, visited);
            }
        }
    }

    public void BFS(int startCity) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[startCity] = true;
        queue.add(startCity);

        System.out.println("BFS traversal starting from " + cities.get(startCity).getName() + ":");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(cities.get(current).getName() + " ");

            for (Integer neighborId : adjList[current]) {
                if (!visited[neighborId]) {
                    visited[neighborId] = true;
                    queue.add(neighborId);
                }
            }
        }
        System.out.println();
    }

    public boolean checkPathExists(int source, int destination) {
        if (source == destination) {
            return true;
        }

        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Integer neighborId : adjList[current]) {
                if (neighborId == destination) {
                    return true;
                }

                if (!visited[neighborId]) {
                    visited[neighborId] = true;
                    queue.add(neighborId);
                }
            }
        }

        return false;
    }

    public List<Integer> findPath(int source, int destination) {
        if (source == destination) {
            List<Integer> path = new ArrayList<>();
            path.add(source);
            return path;
        }

        boolean[] visited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.add(source);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Integer neighborId : adjList[current]) {
                if (!visited[neighborId]) {
                    visited[neighborId] = true;
                    parent[neighborId] = current;
                    queue.add(neighborId);

                    if (neighborId == destination) {
                        return reconstructPath(parent, source, destination);
                    }
                }
            }
        }

        return new ArrayList<>();
    }

    private List<Integer> reconstructPath(int[] parent, int source, int destination) {
        List<Integer> path = new ArrayList<>();
        for (int at = destination; at != -1; at = parent[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
