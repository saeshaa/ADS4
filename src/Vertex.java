
public class Vertex {

    // Unique identifier for this vertex
    private int id;

    public Vertex(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Vertex(" + id + ")";
    }
}