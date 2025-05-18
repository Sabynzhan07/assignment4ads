import java.util.List;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>(true);

        graph.addVertex("Almaty");
        graph.addVertex("Astana");
        graph.addVertex("Shymkent");
        graph.addVertex("Kyzylorda");
        graph.addVertex("Atyrau");
        graph.addVertex("Karaganda");

        graph.addEdge("Almaty", "Astana", 2.1);
        graph.addEdge("Almaty", "Shymkent", 7.2);
        graph.addEdge("Shymkent", "Astana", 3.5);
        graph.addEdge("Shymkent", "Kyzylorda", 5.4);
        graph.addEdge("Astana", "Karaganda", 1.8);
        graph.addEdge("Karaganda", "Kyzylorda", 6.9);
        graph.addEdge("Atyrau", "Astana", 4.7);


        System.out.println("Dijkstra Algo:");
        Search<String> djk = new DijkstraSearch<>(graph);
        outputPath(djk.search("Almaty", "Kyzylorda"));

        System.out.println("\nBFS Algo:");
        Search<String> bfs = new BreadthFirstSearch<>(graph);
        outputPath(bfs.search("Almaty", "Kyzylorda"));

        System.out.println("\nDFS Algo (Example):");
        Search<String> dfs = new DepthFirstSearch<>(graph);
        outputPath(dfs.search("Almaty", "Kyzylorda"));

        System.out.println("\nPath to non-existent or unreachable node (Atyrau to Kyzylorda):");
        outputPath(djk.search("Atyrau", "Kyzylorda"));

        System.out.println("\nPath from Almaty to Atyrau:");
        outputPath(djk.search("Almaty", "Atyrau"));

        System.out.println("\nTesting with a non-existent vertex:");
        outputPath(djk.search("Almaty", "Moscow"));
    }

    public static void outputPath(List<Vertex<String>> path) {
        if (path == null || path.isEmpty()) {
            System.out.println("No path found or path is empty.");
        } else {
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i).getData());
                if (i < path.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
        }
    }
}