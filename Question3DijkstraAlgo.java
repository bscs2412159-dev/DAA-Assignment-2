import java.util.*;

class Question3DijkstraAlgo {
    static class Edge {
        int target, weight;
        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int source) {
        int V = graph.size();
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);

        dist[source] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int u = node[0];
            int d = node[1];

            if (d > dist[u]) continue;

            for (Edge e : graph.get(u)) {
                int v = e.target;
                int weight = e.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    parent[v] = u;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        System.out.println("Shortest distances from source " + source + ":");
        for (int i = 0; i < V; i++) {
            System.out.println("Node " + i + " distance = " + dist[i]);
        }

        System.out.println("\nShortest Path Tree (parent relationships):");
        for (int i = 0; i < V; i++) {
            if (i != source) {
                System.out.println("Node " + i + " <- " + parent[i]);
            }
        }
    }

    public static void main(String[] args) {
        int V = 9;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 4));
        graph.get(1).add(new Edge(0, 4));

        graph.get(0).add(new Edge(7, 8));
        graph.get(7).add(new Edge(0, 8));

        graph.get(1).add(new Edge(2, 8));
        graph.get(2).add(new Edge(1, 8));

        graph.get(1).add(new Edge(7, 11));
        graph.get(7).add(new Edge(1, 11));

        graph.get(2).add(new Edge(3, 7));
        graph.get(3).add(new Edge(2, 7));

        graph.get(2).add(new Edge(8, 2));
        graph.get(8).add(new Edge(2, 2));

        graph.get(2).add(new Edge(5, 4));
        graph.get(5).add(new Edge(2, 4));

        graph.get(3).add(new Edge(4, 9));
        graph.get(4).add(new Edge(3, 9));

        graph.get(3).add(new Edge(5, 14));
        graph.get(5).add(new Edge(3, 14));

        graph.get(4).add(new Edge(5, 10));
        graph.get(5).add(new Edge(4, 10));

        graph.get(5).add(new Edge(6, 2));
        graph.get(6).add(new Edge(5, 2));

        graph.get(6).add(new Edge(7, 1));
        graph.get(7).add(new Edge(6, 1));

        graph.get(6).add(new Edge(8, 6));
        graph.get(8).add(new Edge(6, 6));

        graph.get(7).add(new Edge(8, 7));
        graph.get(8).add(new Edge(7, 7));

        dijkstra(graph, 0);
    }
}
