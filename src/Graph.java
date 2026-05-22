import java.util.*;

public class Graph {

    // Maps each vertex ID to its Vertex object for quick lookup
    private Map<Integer, Vertex> vertexMap;

    // Adjacency list: maps each Vertex to its list of adjacent Vertices
    private Map<Integer, List<Integer>> adjacencyList;

    private Map<Integer, List<int[]>> weightedAdjList;
    //Weighted list: maps each vertex ID to its list of weighted edges
    public Graph() {
        vertexMap = new HashMap<>();
        adjacencyList = new HashMap<>();
        weightedAdjList = new HashMap<>();
    }

    public void addVertex(Vertex v) {
        if (!vertexMap.containsKey(v.getId())) {
            vertexMap.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
            weightedAdjList.put(v.getId(), new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        // Only add edge if both vertices exist
        if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
            System.out.println("Warning: Cannot add edge " + from + " -> " + to
                    + " because one or both vertices do not exist.");
            return;
        }
        adjacencyList.get(from).add(to);
    }
//New weighted addEdge
    public void addWeightedEdge(int from, int to, int weight) {
         if (!weightedAdjList.containsKey(from) || !weightedAdjList.containsKey(to)) {
             System.out.println("Warning: Cannot add weighted edge " + from + " -> " + to
                + " because one or both vertices do not exist.");
        return;
    }
    // Store as int[] {neighborId, weight}
    weightedAdjList.get(from).add(new int[]{to, weight});
    weightedAdjList.get(to).add(new int[]{from, weight}); // undirected
}

    //Dijkstra's algorithm
    public void dijkstra ( int start){
        if (!vertexMap.containsKey(start)) {
            System.out.println("Dijkstra Error: vertex "+ start + "not found" );
            return;
        }

        int n = vertexMap.size();
        int[]dist = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start]=0;
        for (int i = 0; i < n - 1; i++) {
            // Pick unvisited vertex with smallest known distance
            int u = minDistance(dist, visited, n);
            if (u == -1) break; // remaining vertices are unreachable

            visited[u] = true;

            // Relax neighbors of u
            for (int[] edge : weightedAdjList.get(u)) {
                int v = edge[0];
                int w = edge[1];
                if (!visited[v] && dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        printDijkstraResult(start, dist, n);
    }

    private int minDistance(int[] dist, boolean[] visited, int n) {
        int minVal   = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int v = 0; v < n; v++) {
            if (!visited[v] && dist[v] <= minVal) {
                minVal   = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    private void printDijkstraResult(int start, int[] dist, int n) {
        System.out.println("\n--- Dijkstra's Shortest Paths (start = " + start + ") ---");
        System.out.printf("  %-10s %-15s%n", "Vertex", "Distance");
        System.out.println("  " + "-".repeat(25));
        for (int i = 0; i < n; i++) {
            String d = (dist[i] == Integer.MAX_VALUE) ? "Unreachable" : String.valueOf(dist[i]);
            System.out.printf("  %-10d %-15s%n", i, d);
        }
    }
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

    public int getVertexCount() {
        return vertexMap.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            count += neighbors.size();
        }
        return count;
    }
}