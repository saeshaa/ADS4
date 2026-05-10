import java.util.List;

/**
 * Experiment.java
 * Handles execution and performance analysis of graph traversals.
 *
 * Responsibilities:
 *   - Run BFS and DFS on a given graph and print traversal results
 *   - Run multiple timed experiments on graphs of different sizes
 *   - Print a formatted summary table of execution times
 */
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

    /**
     * Constructs an Experiment container for up to the given number of test cases.
     *
     * @param maxExperiments the maximum number of graph sizes to test
     */
    public Experiment(int maxExperiments) {
        labels    = new String[maxExperiments];
        bfsTimes  = new long[maxExperiments];
        dfsTimes  = new long[maxExperiments];
        experimentCount = 0;
    }

    /**
     * Runs BFS and DFS on the provided graph, prints traversal orders,
     * and measures execution time using System.nanoTime().
     *
     * Traversal order is printed only for small graphs (≤ 15 vertices)
     * to keep output readable.
     *
     * @param g     the graph to traverse
     * @param label a descriptive label (e.g., "Small (10 vertices)")
     */
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

    /**
     * Runs timed traversals on all three standard graph sizes
     * (small, medium, large) and stores the results internally.
     *
     * Graphs are built with a simple deterministic edge pattern so results
     * are reproducible: each vertex i connects to (i+1) and (i+2).
     */
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

    /**
     * Prints a formatted table comparing BFS vs DFS execution times
     * for all experiments run so far.
     */
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

    /**
     * Builds a directed graph with 'size' vertices (IDs 0 to size-1).
     * Edge pattern: each vertex i connects to (i+1) % size and (i+2) % size,
     * creating a moderately connected cyclic graph suitable for testing.
     *
     * @param size number of vertices
     * @return the constructed Graph
     */
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