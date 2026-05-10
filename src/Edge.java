public class Edge {

    // The vertex where this edge begins
    private Vertex source;

    // The vertex where this edge ends
    private Vertex destination;

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Edge(" + source.getId() + " -> " + destination.getId() + ")";
    }
}