import java.util.List;
public class Experiment {

    // ---------------------------------------------------------------
    // Storage for timing results across graph sizes
    // ---------------------------------------------------------------

    // Labels for each experiment (e.g., "Small (10 vertices)")
    private String[] labels;

    // BFS execution times in nanoseconds for each experiment
    private long[] bfsTimes;

    // DFS execution times in nanoseconds for each experiment
    private long[] dfsTimes;

    // Number of experiments run
    private int experimentCount;

    public Experiment(int maxExperiments) {
        labels    = new String[maxExperiments];
        bfsTimes  = new long[maxExperiments];
        dfsTimes  = new long[maxExperiments];
        experimentCount = 0;
    }

    public void runTraversals(Graph g, String label) {
        System.out.println("\n>>> Running traversals on: " + label);
        System.out.println("    Vertices: " + g.getVertexCount()
                + "  |  Edges: " + g.getEdgeCount());

        // ---- BFS ----
        long bfsStart = System.nanoTime();
        List<Integer> bfsResult = g.bfs(0);          // Always start traversal from vertex 0
        long bfsEnd   = System.nanoTime();
        long bfsDuration = bfsEnd - bfsStart;

        System.out.println("    BFS time : " + bfsDuration + " ns");

        // Print traversal order for small graphs only
        if (g.getVertexCount() <= 15) {
            System.out.println("    BFS order: " + bfsResult);
        }

        // ---- DFS ----
        long dfsStart = System.nanoTime();
        List<Integer> dfsResult = g.dfs(0);
        long dfsEnd   = System.nanoTime();
        long dfsDuration = dfsEnd - dfsStart;

        System.out.println("    DFS time : " + dfsDuration + " ns");

        if (g.getVertexCount() <= 15) {
            System.out.println("    DFS order: " + dfsResult);
        }

        // Store results for the summary table
        if (experimentCount < labels.length) {
            labels[experimentCount]   = label;
            bfsTimes[experimentCount] = bfsDuration;
            dfsTimes[experimentCount] = dfsDuration;
            experimentCount++;
        }
    }
    public void runMultipleTests() {
        System.out.println("\n========================================");
        System.out.println("  MULTIPLE GRAPH SIZE EXPERIMENT");
        System.out.println("========================================");

        // ---- Small graph: 10 vertices ----
        Graph smallGraph = buildGraph(10);
        runTraversals(smallGraph, "Small (10 vertices)");

        // ---- Medium graph: 30 vertices ----
        Graph mediumGraph = buildGraph(30);
        runTraversals(mediumGraph, "Medium (30 vertices)");

        // ---- Large graph: 100 vertices ----
        Graph largeGraph = buildGraph(100);
        runTraversals(largeGraph, "Large (100 vertices)");
    }

    public void printResults() {
        System.out.println("\n========================================");
        System.out.println("  PERFORMANCE RESULTS SUMMARY");
        System.out.println("========================================");
        System.out.printf("%-25s %15s %15s%n", "Graph Size", "BFS Time (ns)", "DFS Time (ns)");
        System.out.println("-".repeat(57));

        for (int i = 0; i < experimentCount; i++) {
            System.out.printf("%-25s %15d %15d%n",
                    labels[i], bfsTimes[i], dfsTimes[i]);
        }

        System.out.println("-".repeat(57));

        // Determine which algorithm was faster overall
        long totalBfs = 0, totalDfs = 0;
        for (int i = 0; i < experimentCount; i++) {
            totalBfs += bfsTimes[i];
            totalDfs += dfsTimes[i];
        }

        System.out.println("\n  Total BFS time : " + totalBfs + " ns");
        System.out.println("  Total DFS time : " + totalDfs + " ns");
        System.out.println("  Faster overall : " + (totalBfs <= totalDfs ? "BFS" : "DFS"));
        System.out.println("========================================\n");
    }

    // ---------------------------------------------------------------
    // Private helper
    // ---------------------------------------------------------------

    private Graph buildGraph(int size) {
        Graph g = new Graph();

        // Add all vertices
        for (int i = 0; i < size; i++) {
            g.addVertex(new Vertex(i));
        }

        // Add edges: each vertex connects forward by 1 and by 2 (wrapping around)
        for (int i = 0; i < size; i++) {
            g.addEdge(i, (i + 1) % size);
            g.addEdge(i, (i + 2) % size);
        }

        return g;
    }
}