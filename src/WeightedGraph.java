import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WeightedGraph<V> {
    private final boolean undirected;
    private final Map<V, Vertex<V>> map;

    public WeightedGraph() {
        this(false);
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
        this.map = new HashMap<>();
    }

    public void addVertex(V data) {
        map.putIfAbsent(data, new Vertex<>(data));
    }

    public Vertex<V> getVertex(V data) {
        return map.get(data);
    }

    public Collection<Vertex<V>> getVertices() {
        return map.values();
    }

    public void addEdge(V sourceData, V destData, double weight) {
        Vertex<V> source = getVertex(sourceData);
        if (source == null) {
            addVertex(sourceData);
            source = getVertex(sourceData);
        }

        Vertex<V> dest = getVertex(destData);
        if (dest == null) {
            addVertex(destData);
            dest = getVertex(destData);
        }

        source.addAdjacentVertex(dest, weight);

        if (undirected) {
            dest.addAdjacentVertex(source, weight);
        }
    }

    public Set<V> getVertexKeys() {
        return map.keySet();
    }
}
