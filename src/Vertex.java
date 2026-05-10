/**
 * Vertex.java
 * Represents a single node in the graph.
 * Each vertex has a unique integer identifier.
 */
public class Vertex {

    // Unique identifier for this vertex
    private int id;

    /**
     * Constructs a Vertex with the given identifier.
     *
     * @param id the unique integer ID for this vertex
     */
    public Vertex(int id) {
        this.id = id;
    }

    /**
     * Returns the unique ID of this vertex.
     *
     * @return the vertex ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns a string representation of this vertex.
     *
     * @return string in the form "Vertex(id)"
     */
    @Override
    public String toString() {
        return "Vertex(" + id + ")";
    }
}