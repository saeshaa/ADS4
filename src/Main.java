public class Main {

    public static void main(String[] args) {

        System.out.println("============================================================");
        System.out.println("      GRAPH TRAVERSAL AND REPRESENTATION SYSTEM");
        System.out.println("============================================================\n");

        // ============================================================
        // PART 1: Small graph (10 vertices) — structure + traversals
        // ============================================================

        System.out.println("--- Building Small Graph (10 vertices) ---\n");

        Graph smallGraph = new Graph();

        // Add 10 vertices (IDs 0 through 9)
        for (int i = 0; i < 10; i++) {
            smallGraph.addVertex(new Vertex(i));
        }

        // Add directed edges to create an interesting, connected structure
        smallGraph.addEdge(0, 1);
        smallGraph.addEdge(0, 2);
        smallGraph.addEdge(1, 3);
        smallGraph.addEdge(1, 4);
        smallGraph.addEdge(2, 5);
        smallGraph.addEdge(2, 6);
        smallGraph.addEdge(3, 7);
        smallGraph.addEdge(4, 7);
        smallGraph.addEdge(5, 8);
        smallGraph.addEdge(6, 9);
        smallGraph.addEdge(7, 9);
        smallGraph.addEdge(8, 9);

        // Print the adjacency list
        smallGraph.printGraph();

        System.out.println("\nVertices: " + smallGraph.getVertexCount()
                + "  |  Edges: " + smallGraph.getEdgeCount());

        // ---- BFS on small graph ----
        System.out.println("\n--- BFS Traversal (start = 0) ---");
        long bfsStart = System.nanoTime();
        java.util.List<Integer> bfsOrder = smallGraph.bfs(0);
        long bfsEnd = System.nanoTime();

        System.out.println("BFS Order  : " + bfsOrder);
        System.out.println("BFS Time   : " + (bfsEnd - bfsStart) + " ns");

        // ---- DFS on small graph ----
        System.out.println("\n--- DFS Traversal (start = 0) ---");
        long dfsStart = System.nanoTime();
        java.util.List<Integer> dfsOrder = smallGraph.dfs(0);
        long dfsEnd = System.nanoTime();

        System.out.println("DFS Order  : " + dfsOrder);
        System.out.println("DFS Time   : " + (dfsEnd - dfsStart) + " ns");

        // ============================================================
        // PART 2: Performance experiments on multiple graph sizes
        // ============================================================

        System.out.println("\n============================================================");
        System.out.println("      PERFORMANCE EXPERIMENTS");
        System.out.println("============================================================");

        // Create an Experiment instance that can hold 3 test cases
        Experiment experiment = new Experiment(3);

        // Run BFS + DFS on small, medium, and large graphs
        experiment.runMultipleTests();

        // Print the formatted summary table
        experiment.printResults();

        System.out.println("============================================================");
        System.out.println("      PROGRAM COMPLETE");
        System.out.println("============================================================");
    }
}