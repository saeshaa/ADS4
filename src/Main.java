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
        for (int i = 0; i < 10; i++) smallGraph.addVertex(new Vertex(i));

        smallGraph.addEdge(0, 1); smallGraph.addEdge(0, 2);
        smallGraph.addEdge(1, 3); smallGraph.addEdge(1, 4);
        smallGraph.addEdge(2, 5); smallGraph.addEdge(2, 6);
        smallGraph.addEdge(3, 7); smallGraph.addEdge(4, 7);
        smallGraph.addEdge(5, 8); smallGraph.addEdge(6, 9);
        smallGraph.addEdge(7, 9); smallGraph.addEdge(8, 9);

        smallGraph.printGraph();
        System.out.println("\nVertices: " + smallGraph.getVertexCount()
                + "  |  Edges: " + smallGraph.getEdgeCount());

        System.out.println("\n--- BFS Traversal (start = 0) ---");
        long bfsStart = System.nanoTime();
        java.util.List<Integer> bfsOrder = smallGraph.bfs(0);
        long bfsEnd = System.nanoTime();
        System.out.println("BFS Order  : " + bfsOrder);
        System.out.println("BFS Time   : " + (bfsEnd - bfsStart) + " ns");

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
        Experiment experiment = new Experiment(3);
        experiment.runMultipleTests();
        experiment.printResults();

        // ============================================================
        // PART 3: Dijkstra's Shortest Path
        // ============================================================
        System.out.println("============================================================");
        System.out.println("      BONUS: DIJKSTRA'S SHORTEST PATH ALGORITHM");
        System.out.println("============================================================\n");

        Graph weightedGraph = new Graph();
        for (int i = 0; i < 6; i++) weightedGraph.addVertex(new Vertex(i));

        //  Graph layout:
        //   0 --1-- 1 --4-- 2
        //   |       |       |
        //   4       2       1
        //   |       |       |
        //   3 --3-- 4 --2-- 5
        weightedGraph.addWeightedEdge(0, 1, 1);
        weightedGraph.addWeightedEdge(0, 3, 4);
        weightedGraph.addWeightedEdge(1, 2, 4);
        weightedGraph.addWeightedEdge(1, 4, 2);
        weightedGraph.addWeightedEdge(2, 5, 1);
        weightedGraph.addWeightedEdge(3, 4, 3);
        weightedGraph.addWeightedEdge(4, 5, 2);

        weightedGraph.dijkstra(0);

        System.out.println("\n============================================================");
        System.out.println("      PROGRAM COMPLETE");
        System.out.println("============================================================");
    }
}