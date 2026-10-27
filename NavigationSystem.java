import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NavigationSystem {
    private int numVertices;
    private int[][] adjMatrix;
    private String[] cityNames;

    // Constructor to initialize graph with cities
    public NavigationSystem(int numVertices, String[] cities) {
        this.numVertices = numVertices;
        this.adjMatrix = new int[numVertices][numVertices];
        this.cityNames = cities;
    }

    // Add a directed edge from source to destination
    public void addEdge(int src, int dest) {
        adjMatrix[src][dest] = 1;
    }

    // Display the Adjacency Matrix
    public void printAdjacencyMatrix() {
        System.out.println("\n--- Adjacency Matrix ---");
        System.out.print("    ");
        for (int i = 0; i < numVertices; i++) {
            System.out.print(cityNames[i] + "\t");
        }
        System.out.println();

        for (int i = 0; i < numVertices; i++) {
            System.out.print(cityNames[i] + "\t");
            for (int j = 0; j < numVertices; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    // Breadth-First Search (BFS) Traversal
    public void BFS(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.print("\nBFS Reachable Path starting from " + cityNames[startVertex] + ": ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(cityNames[current] + " ");

            for (int i = 0; i < numVertices; i++) {
                if (adjMatrix[current][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
        System.out.println();
    }

    // Depth-First Search (DFS) Traversal
    public void DFS(int startVertex) {
        boolean[] visited = new boolean[numVertices];
        System.out.print("\nDFS Reachable Path starting from " + cityNames[startVertex] + ": ");
        dfsHelper(startVertex, visited);
        System.out.println();
    }

    // Helper method for recursive DFS
    private void dfsHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(cityNames[vertex] + " ");

        for (int i = 0; i < numVertices; i++) {
            if (adjMatrix[vertex][i] == 1 && !visited[i]) {
                dfsHelper(i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of cities (vertices): ");
        int numCities = sc.nextInt();
        sc.nextLine(); // Consume newline

        String[] cities = new String[numCities];
        System.out.println("\nEnter names of the cities:");
        for (int i = 0; i < numCities; i++) {
            System.out.print("City " + i + ": ");
            cities[i] = sc.nextLine();
        }

        NavigationSystem graph = new NavigationSystem(numCities, cities);

        System.out.print("\nEnter total number of one-way roads (directed edges): ");
        int numRoads = sc.nextInt();

        System.out.println("\nEnter roads (format: source_city_index destination_city_index):");
        System.out.println("Note: City indices range from 0 to " + (numCities - 1));
        for (int i = 0; i < numRoads; i++) {
            System.out.print("Road " + (i + 1) + " (From To): ");
            int u = sc.nextInt();
            int v = sc.nextInt();
            if (u >= 0 && u < numCities && v >= 0 && v < numCities) {
                graph.addEdge(u, v);
            } else {
                System.out.println("Invalid city index! Try again.");
                i--;
            }
        }

        int choice;
        do {
            System.out.println("1. Display Adjacency Matrix");
            System.out.println("2. Explore Cities using BFS (Level by Level)");
            System.out.println("3. Explore Cities using DFS (Depth First)");
            System.out.println("4. Exit");
            System.out.print("Enter your choice (1-4): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    graph.printAdjacencyMatrix();
                    break;

                case 2:
                    System.out.print("Enter starting city index (0 to " + (numCities - 1) + "): ");
                    int startBfs = sc.nextInt();
                    if (startBfs >= 0 && startBfs < numCities) {
                        graph.BFS(startBfs);
                    } else {
                        System.out.println("Invalid starting city!");
                    }
                    break;

                case 3:
                    System.out.print("Enter starting city index (0 to " + (numCities - 1) + "): ");
                    int startDfs = sc.nextInt();
                    if (startDfs >= 0 && startDfs < numCities) {
                        graph.DFS(startDfs);
                    } else {
                        System.out.println("Invalid starting city!");
                    }
                    break;

                case 4:
                    System.out.println("Exiting Navigation System.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}