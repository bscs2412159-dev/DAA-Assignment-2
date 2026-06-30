import java.util.*;

public class Question1EdmondsAlgo {
    static final int INF = Integer.MAX_VALUE;

    private static boolean bfs(int[][] residualGraph, int source, int sink, int[] parent) {
        int V = residualGraph.length;
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < V; v++) {
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return visited[sink];
    }

    public static int edmondsKarp(int[][] graph, int source, int sink) {
        int V = graph.length;
        int[][] residualGraph = new int[V][V];

        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                residualGraph[u][v] = graph[u][v];
            }
        }

        int[] parent = new int[V];
        int maxFlow = 0;

        while (bfs(residualGraph, source, sink, parent)) {
            int pathFlow = INF;
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }
            for (int v = sink; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }

        System.out.println("The maximum possible flow is " + maxFlow);

        // Identify minimum cut edges
        boolean[] visited = new boolean[V];
        dfs(residualGraph, source, visited);

        System.out.println("Minimum cut edges:");
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] > 0 && visited[u] && !visited[v]) {
                    System.out.println((char)('A' + u) + " -> " + (char)('A' + v));
                }
            }
        }

        return maxFlow;
    }

    private static void dfs(int[][] residualGraph, int u, boolean[] visited) {
        visited[u] = true;
        for (int v = 0; v < residualGraph.length; v++) {
            if (residualGraph[u][v] > 0 && !visited[v]) {
                dfs(residualGraph, v, visited);
            }
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
            {0, 3, 3, 3, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 4, 0, 1, 2, 0, 0},
            {0, 0, 0, 0, 2, 6, 0},
            {0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 9},
            {0, 0, 0, 0, 0, 0, 0}
        };

        int source = 0;
        int sink = 6;

        edmondsKarp(graph, source, sink);
    }
}
