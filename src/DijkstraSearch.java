import java.util.*;

public class DijkstraSearch<V> implements Search<V> {
    private final WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> search(V startData, V endData) {
        Vertex<V> startVertex = graph.getVertex(startData);
        Vertex<V> endVertex = graph.getVertex(endData);

        if (startVertex == null || endVertex == null) {
            System.out.println("Start or end vertex not found in the graph: " +
                    (startVertex == null ? startData : "") +
                    (endVertex == null ? (startVertex == null ? " and " : "") + endData : ""));
            return null;
        }

        Map<Vertex<V>, Double> distances = new HashMap<>();
        Map<Vertex<V>, Vertex<V>> predecessors = new HashMap<>();
        PriorityQueue<Vertex<V>> pq;

        for (Vertex<V> vertex : graph.getVertices()) {
            distances.put(vertex, Double.POSITIVE_INFINITY);
            predecessors.put(vertex, null);
        }
        distances.put(startVertex, 0.0);

        pq = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        pq.add(startVertex);

        while (!pq.isEmpty()) {
            Vertex<V> u = pq.poll();

            if (u.equals(endVertex)) {
                if (distances.get(u) == Double.POSITIVE_INFINITY) {
                    return null;
                }
                return reconstructPath(predecessors, endVertex);
            }

            if (distances.get(u) == Double.POSITIVE_INFINITY) {
                continue;
            }

            for (Map.Entry<Vertex<V>, Double> entry : u.getAdjacentVertices().entrySet()) {
                Vertex<V> v = entry.getKey();
                double weight = entry.getValue();
                double distanceThroughU = distances.get(u) + weight;

                if (distanceThroughU < distances.get(v)) {
                    distances.put(v, distanceThroughU);
                    predecessors.put(v, u);
                    pq.remove(v);
                    pq.add(v);
                }
            }
        }
        return null;
    }

    private List<Vertex<V>> reconstructPath(Map<Vertex<V>, Vertex<V>> predecessors, Vertex<V> endNode) {
        LinkedList<Vertex<V>> path = new LinkedList<>();
        Vertex<V> current = endNode;

        while (current != null) {
            path.addFirst(current);
            current = predecessors.get(current);
        }
        return path;
    }
}