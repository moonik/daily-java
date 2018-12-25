package com.roman.mysan.data.structures.graph;

import java.util.*;

public class WeightedGraph {

    private final List<Vertex> vertices;

    public WeightedGraph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    private void addEdge(int source, int destination, int weight) {
        this.vertices.get(source).getEdges().add(new Vertex(destination, vertices.get(destination).getValue(), weight));
        this.vertices.get(destination).getEdges().add(new Vertex(source, vertices.get(source).getValue(), weight));
    }

    public void primsAlgorithm(WeightedGraph g) {
        Set<Integer> discovered = new HashSet<>();
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getWeight));
        Map<Integer, Integer> weights = new HashMap<>(g.vertices.size());
        Map<Integer, Integer> parents = new HashMap<>();

        for (Vertex v : g.vertices) {
            weights.put(v.getValue(), Integer.MAX_VALUE);
        }

        weights.put(0, 0);
        queue.add(g.vertices.get(0));

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            discovered.add(v.getId());

            for (Vertex u : this.vertices.get(v.getId()).getEdges()) {
                if (!discovered.contains(u.getId())) {
                    if(u.getWeight() < weights.get(u.getValue())) {
                        parents.put(u.getValue(), v.getValue());
                        weights.put(u.getValue(), u.getWeight());
                        queue.add(u);
                    }
                }
            }
        }
        parents.forEach((key, value) -> System.out.println(key + " -> " + value));
    }

    public void dijkstra(WeightedGraph g, int source, int dest) {
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(Vertex::getWeight));
        Map<Integer, Integer> weights = new HashMap<>(g.vertices.size());
        Map<Integer, Integer> parents = new HashMap<>();

        for (Vertex vertex : g.vertices) {
            weights.put(vertex.getValue(), Integer.MAX_VALUE);
        }

        weights.put(source, 0);
        queue.add(g.vertices.get(source));

        while (!queue.isEmpty()) {
            Vertex v = queue.poll();

            for (Vertex u : this.vertices.get(v.getId()).getEdges()) {
                if(weights.get(v.getId()) + u.getWeight() < weights.get(u.getId())) {
                    parents.put(u.getId(), v.getId());
                    weights.put(u.getId(), u.getWeight() + weights.get(v.getId()));
                    queue.add(u);
                }
            }
        }

        parents.forEach((key, value) -> System.out.println(key + " -> " + value));
        StringBuilder shortestPath = new StringBuilder();
        Integer current = dest;
        while (current != null) {
            shortestPath.append(current).append(" ");
            current = parents.get(current);
        }
        System.out.println(shortestPath.reverse().toString());
    }

    public static void main(String[] args) {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            vertices.add(new Vertex(i, i));
        }

        WeightedGraph graph = new WeightedGraph(vertices);

        //test Prim's
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(0, 3, 5);
        graph.addEdge(0, 4, 1);

        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 4);

        graph.addEdge(3, 4, 3);

        graph.primsAlgorithm(graph);

        //Dijkstra's test
        vertices = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex(i, i));
        }

        graph = new WeightedGraph(vertices);

        //test Prim's
        graph.addEdge(0, 1, 5);
        graph.addEdge(0, 2, 5);
        graph.addEdge(0, 3, 7);
        graph.addEdge(0, 4, 1);
        graph.addEdge(0, 5, 5);

        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 4, 1);

        graph.addEdge(2, 5, 3);

        graph.addEdge(3, 4, 2);

        //graph.dijkstra(graph, 0, 2);
    }
}