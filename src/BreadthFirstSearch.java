import java.util.*;

public class BreadthFirstSearch<V> implements Search<V> {
    private final WeightedGraph<V> graph;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    @Override
    public List<Vertex<V>> search(V startData, V endData) {
        Vertex<V> startVertex = graph.getVertex(startData);
        Vertex<V> endVertex = graph.getVertex(endData);

        if (startVertex == null || endVertex == null) {
            System.out.println("Start or end vertex not found in the graph.");
            return null;
        }

        Queue<Vertex<V>> queue = new LinkedList<>();
        Map<Vertex<V>, Vertex<V>> predecessors = new HashMap<>();
        Set<Vertex<V>> visited = new HashSet<>();

        queue.add(startVertex);
        visited.add(startVertex);
        predecessors.put(startVertex, null);
        while (!queue.isEmpty()) {
            Vertex<V> current = queue.poll();

            if (current.equals(endVertex)) {
                return reconstructPath(predecessors, endVertex);
            }

            for (Vertex<V> neighbor : current.getAdjacentVertices().keySet()) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }
        return null;
    }

    private List<Vertex<V>> reconstructPath(Map<Vertex<V>, Vertex<V>> predecessors, Vertex<V> endVertex) {
        LinkedList<Vertex<V>> path = new LinkedList<>();
        Vertex<V> current = endVertex;
        while (current != null) {
            path.addFirst(current);
            current = predecessors.get(current);
        }
        return path;
    }
}