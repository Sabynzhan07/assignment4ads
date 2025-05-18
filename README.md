For this assignment, I changed it so that each `Vertex` now knows its own neighbors and how far they are (the edge weight).

 So, the `Vertex` class has a little map inside it: `adjacentVertices`. This map links directly to other `Vertex` objects and stores the weight to get to them.
 The `WeightedGraph` class is now simpler – it mostly just holds all these smart `Vertex` objects and helps connect them up.

This makes each vertex a bit more self-contained.

I implemented a few common ways to find paths in these graphs:

1.  **Breadth-First Search (BFS):** Finds the path with the fewest steps. It looks at all nearby points before moving further out. 
2.  **Dijkstra's Algorithm:** Finds the "cheapest" or shortest path if edges have different costs or distances.
3.  **Depth-First Search (DFS):** Explores one path as far as it can go before trying another.

All these search methods work with the new way vertices store their connections.

## Files in the Project

*   `Vertex.java`: The star of the show – our smart point that knows its neighbors.
*   `WeightedGraph.java`: Manages all the vertices.
*   `BreadthFirstSearch.java`, `DijkstraSearch.java`, `DepthFirstSearch.java`: The pathfinding algorithms.
*   `Search.java`: A common blueprint for the search algorithms.
*   `Main.java`: A test program to show everything working.
