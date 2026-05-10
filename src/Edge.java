/**
 * Edge.java
 * Represents a directed connection between two vertices in the graph.
 * Stores a source (starting) vertex and a destination (ending) vertex.
 */
public class Edge {

    // The vertex where this edge begins
    private Vertex source;

    // The vertex where this edge ends
    private Vertex destination;

    /**
     * Constructs an Edge from a source vertex to a destination vertex.
     *
     * @param source      the starting vertex
     * @param destination the ending vertex
     */
    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    /**
     * Returns the source (starting) vertex.
     *
     * @return source vertex
     */
    public Vertex getSource() {
        return source;
    }

    /**
     * Returns the destination (ending) vertex.
     *
     * @return destination vertex
     */
    public Vertex getDestination() {
        return destination;
    }

    /**
     * Returns a string representation of this edge.
     *
     * @return string in the form "Edge(source -> destination)"
     */
    @Override
    public String toString() {
        return "Edge(" + source.getId() + " -> " + destination.getId() + ")";
    }
}