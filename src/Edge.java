public class Edge {

    // The vertex where this edge begins
    private Vertex source;

    // The vertex where this edge ends
    private Vertex destination;

    private int weight;

    public Edge(Vertex source, Vertex destination) {
        this.source = source;
        this.destination = destination;
        this. weight = 1;
    }
    //new weighted constructor
    public Edge(Vertex source, Vertex destination, int weight){
        this. source = source;
        this.destination= destination;
        this. weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getDestination() {
        return destination;
    }

    public int getWeight(){return weight;}

    @Override
    public String toString() {
        return "Edge(" + source.getId() + " -> " + destination.getId() + ", w= " + weight+ ")";
    }
}