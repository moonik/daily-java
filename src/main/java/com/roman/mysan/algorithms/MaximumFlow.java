package com.roman.mysan.algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumFlow {

    private boolean bfs(int[][] rGraph, int s, int t, int[] parent) {
        boolean[] visited = new boolean[rGraph.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while (!queue.isEmpty()) {
            int v = queue.poll();
            visited[v] = true;

            for (int u = 0; u < rGraph.length; u++) {
                if (!visited[u] && rGraph[v][u] > 0) {
                    queue.add(u);
                    parent[u] = v;
                    visited[u] = true;
                }
            }
        }
        return visited[t]; // if we reached sink
    }

    public int fordFulkerson(int[][] graph, int s, int t) {
        int v, u;
        // Residual graph where rGraph[i][j] indicates
        // residual capacity of edge from i to j (if there
        // is an edge. If rGraph[i][j] is 0, then there is
        // not)
        int[][] rGraph = new int[graph.length][graph.length];

        for (v = 0; v < graph.length; v++) {
            for (u = 0; u < graph.length; u++) {
                rGraph[v][u] = graph[v][u];
            }
        }

        int[] parent = new int[graph.length];
        int maxFlow = 0;

        while (bfs(rGraph, s, t, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            // Find minimum residual capacity of the edhes
            // along the path filled by BFS. Or we can say
            // find the maximum flow through the path found.
            for (u = t; u != s; u = parent[u]) {
                v = parent[u];
                pathFlow = Math.min(pathFlow, rGraph[v][u]);
            }

            // update residual capacities of the edges and
            // reverse edges along the path
            for (u = t; u != s; u = parent[u]) {
                v = parent[u];
                rGraph[v][u] -= pathFlow;
                rGraph[u][v] += pathFlow;
            }
            maxFlow += pathFlow;
        }
        return maxFlow;
    }

    public static void main(String[] args) {
        // Let us create a graph shown in the above example
        int graph[][] =new int[][] {
                {0, 16, 13, 0, 0, 0},
                {0, 0, 10, 12, 0, 0},
                {0, 4, 0, 0, 14, 0},
                {0, 0, 9, 0, 0, 20},
                {0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0}
        };

        MaximumFlow m = new MaximumFlow();
        System.out.println("The maximum possible flow is " +
                m.fordFulkerson(graph, 0, 5));
    }
}
