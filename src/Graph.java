import java.util.*;

/**
 * Graph.java
 * Represents a graph using an adjacency list.
 *
 * The adjacency list maps each Vertex to a list of its neighboring Vertices.
 * This representation is efficient for sparse graphs: O(V + E) space.
 *
 * Supports:
 *   - Adding vertices
 *   - Adding directed edges
 *   - Printing the adjacency list
 *   - BFS traversal (Breadth-First Search)
 *   - DFS traversal (Depth-First Search)
 */
public class Graph {

    // Maps each vertex ID to its Vertex object for quick lookup
    private Map<Integer, Vertex> vertexMap;

    // Adjacency list: maps each Vertex to its list of adjacent Vertices
    private Map<Integer, List<Integer>> adjacencyList;

    /**
     * Constructs an empty Graph with no vertices or edges.
     */
    public Graph() {
        vertexMap = new HashMap<>();
        adjacencyList = new HashMap<>();
    }

    /**
     * Adds a vertex to the graph.
     * If a vertex with the same ID already exists, it is not added again.
     *
     * @param v the Vertex to add
     */
    public void addVertex(Vertex v) {
        if (!vertexMap.containsKey(v.getId())) {
            vertexMap.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
        }
    }

    /**
     * Adds a directed edge from vertex 'from' to vertex 'to'.
     * Both vertices must already exist in the graph.
     *
     * @param from the ID of the source vertex
     * @param to   the ID of the destination vertex
     */
    public void addEdge(int from, int to) {
        // Only add edge if both vertices exist
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            System.out.println("Warning: Cannot add edge " + from + " -> " + to
                    + " because one or both vertices do not exist.");
            return;
        }
        adjacencyList.get(from).add(to);
    }

    /**
     * Prints the full adjacency list representation of the graph.
     * Each line shows a vertex and all vertices it connects to.
     */
    public void printGraph() {
        System.out.println("=== Graph Adjacency List ===");
        // Sort by vertex ID for consistent, readable output
        List<Integer> sortedIds = new ArrayList<>(adjacencyList.keySet());
        Collections.sort(sortedIds);

        for (int id : sortedIds) {
            List<Integer> neighbors = adjacencyList.get(id);
            System.out.print("  " + id + " -> ");
            if (neighbors.isEmpty()) {
                System.out.print("[none]");
            } else {
                System.out.print(neighbors);
            }
            System.out.println();
        }
        System.out.println("============================");
    }

    /**
     * Performs a Breadth-First Search (BFS) starting from a given vertex.
     *
     * BFS explores vertices layer by layer:
     *   1. Enqueue the start vertex and mark it as visited.
     *   2. Dequeue a vertex, record it in traversal order.
     *   3. Enqueue all unvisited neighbors and mark them visited.
     *   4. Repeat until the queue is empty.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for the visited set and queue.
     *
     * @param start the ID of the starting vertex
     * @return a list of vertex IDs in BFS traversal order
     */
    public List<Integer> bfs(int start) {
        List<Integer> traversalOrder = new ArrayList<>();

        // Guard: starting vertex must exist
        if (!adjacencyList.containsKey(start)) {
            System.out.println("BFS Error: Vertex " + start + " not found in graph.");
            return traversalOrder;
        }

        // Track which vertices have been visited to avoid revisiting
        Set<Integer> visited = new HashSet<>();

        // Queue drives the layer-by-layer exploration
        Queue<Integer> queue = new LinkedList<>();

        // Step 1: Initialize — visit the start vertex
        visited.add(start);
        queue.add(start);

        // Step 2: Process vertices until queue is empty
        while (!queue.isEmpty()) {
            // Dequeue the front vertex
            int current = queue.poll();
            traversalOrder.add(current);

            // Step 3: Visit all unvisited neighbors
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);   // Mark visited before enqueuing to prevent duplicates
                    queue.add(neighbor);
                }
            }
        }

        return traversalOrder;
    }

    /**
     * Performs a Depth-First Search (DFS) starting from a given vertex.
     *
     * DFS explores as far as possible along each branch before backtracking:
     *   1. Mark the start vertex as visited.
     *   2. Recursively visit each unvisited neighbor.
     *   3. Backtrack when no unvisited neighbors remain.
     *
     * This implementation uses an iterative approach with an explicit stack
     * to avoid potential stack overflow on very large graphs.
     *
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for the visited set and stack.
     *
     * @param start the ID of the starting vertex
     * @return a list of vertex IDs in DFS traversal order
     */
    public List<Integer> dfs(int start) {
        List<Integer> traversalOrder = new ArrayList<>();

        // Guard: starting vertex must exist
        if (!adjacencyList.containsKey(start)) {
            System.out.println("DFS Error: Vertex " + start + " not found in graph.");
            return traversalOrder;
        }

        // Track which vertices have been visited
        Set<Integer> visited = new HashSet<>();

        // Stack drives the deep-first exploration
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 1: Push the start vertex onto the stack
        stack.push(start);

        // Step 2: Process vertices until stack is empty
        while (!stack.isEmpty()) {
            // Pop the top vertex
            int current = stack.pop();

            // If already visited, skip (can happen due to multiple pushes)
            if (visited.contains(current)) {
                continue;
            }

            // Mark as visited and record
            visited.add(current);
            traversalOrder.add(current);

            // Step 3: Push unvisited neighbors onto stack
            // Push in reverse order so lower-ID neighbors are explored first
            List<Integer> neighbors = adjacencyList.get(current);
            for (int i = neighbors.size() - 1; i >= 0; i--) {
                int neighbor = neighbors.get(i);
                if (!visited.contains(neighbor)) {
                    stack.push(neighbor);
                }
            }
        }

        return traversalOrder;
    }

    /**
     * Returns the number of vertices in the graph.
     *
     * @return vertex count
     */
    public int getVertexCount() {
        return vertexMap.size();
    }

    /**
     * Returns the total number of directed edges in the graph.
     *
     * @return edge count
     */
    public int getEdgeCount() {
        int count = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            count += neighbors.size();
        }
        return count;
    }
}