import java.util.List;

public interface Search<V> {
    List<Vertex<V>> search(V startData, V endData);
}