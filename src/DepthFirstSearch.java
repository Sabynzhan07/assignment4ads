import java.util.*;

public class DepthFirstSearch<V> implements Search<V> {
    private final WeightedGraph<V> graph;

    public DepthFirstSearch(WeightedGraph<V> graph) {
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

        Stack<Vertex<V>> stack = new Stack<>();
        Map<Vertex<V>, Vertex<V>> predecessors = new HashMap<>();
        Set<Vertex<V>> visited = new HashSet<>();

        stack.push(startVertex);
        visited.add(startVertex);
        predecessors.put(startVertex, null);

        while (!stack.isEmpty()) {
            Vertex<V> current = stack.pop();

            if (current.equals(endVertex)) {
                return reconstructPath(predecessors, endVertex);
            }

            List<Vertex<V>> neighbors = new ArrayList<>(current.getAdjacentVertices().keySet());
            Collections.reverse(neighbors);

            for (Vertex<V> neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    predecessors.put(neighbor, current);
                    stack.push(neighbor);
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
