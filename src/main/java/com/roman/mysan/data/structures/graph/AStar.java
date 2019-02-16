package com.roman.mysan.data.structures.graph;

import java.util.*;

public class AStar {

    class Vertex {
        int fScore;
        int index;

        public Vertex(int fScore, int index) {
            this.fScore = fScore;
            this.index = index;
        }
    }

    class FScoreComparator implements Comparator<Vertex> {
        @Override
        public int compare(Vertex o1, Vertex o2) {
            return o1.fScore - o2.fScore;
        }
    }

    public void addEdge(int[][] graph, int source, int destination, int distance) {
        graph[source][destination] = distance;
        graph[destination][source] = distance;
    }

    public String aStar(int source, int destination, int[][] graph, int[] heuristic) {
        final int V = graph.length;
        int[] gScores = new int[V];
        int[] parents = new int[V];
        boolean[] inOpenSet = new boolean[V];

        for (int i = 0; i < V; i++) {
            gScores[i] = Integer.MAX_VALUE;
        }
        gScores[source] = 0;

        Queue<Vertex> open = new PriorityQueue<>(new FScoreComparator());
        open.add(new Vertex(0, source));

        Set<Integer> closed = new HashSet<>();
        closed.add(source);
        inOpenSet[source] = true;

        while (!open.isEmpty()) {
            Vertex current = open.poll();
            int v = current.index;
            inOpenSet[v] = false;
            if (v == destination) {
                return reconstruct(parents, source, destination);
            }
            closed.add(v);

            for (int u = 0; u < V; u++) {
                if (graph[v][u] > 0 && !closed.contains(u)) {
                    int tentativeGScore = gScores[v] + graph[v][u];
                    if (!inOpenSet[u] && tentativeGScore < gScores[u]) {
                        inOpenSet[u] = true;
                        parents[u] = v;
                        gScores[u] = tentativeGScore;
                        int fScore = gScores[u] + heuristic[u];
                        open.add(new Vertex(fScore, u));
                    }
                }
            }
        }
        return null;
    }

    public String reconstruct(int[] path, int source, int destination) {
        StringBuilder sb = new StringBuilder();
        sb.append(destination).append(",");
        int current = path[destination];
        while (current != source) {
            sb.append(current).append(",");
            current = path[current];
        }
        sb.append(source);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int[][] graph = new int[5][5];
        AStar aStar = new AStar();

        aStar.addEdge(graph, 0, 1, 7);
        aStar.addEdge(graph, 0, 2, 3);

        aStar.addEdge(graph, 1, 3, 4);

        aStar.addEdge(graph, 2, 3, 2);
        aStar.addEdge(graph, 2, 4, 5);

        aStar.addEdge(graph, 3, 4, 2);

        int[] heuristic = new int[5];
        heuristic[0] = 10;
        heuristic[1] = 6;
        heuristic[2] = 5;
        heuristic[3] = 3;

        String path = aStar.aStar(0, 4, graph, heuristic);
        System.out.println(path);
    }
}
