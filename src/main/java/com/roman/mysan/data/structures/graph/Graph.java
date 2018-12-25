package com.roman.mysan.data.structures.graph;

import java.util.*;

public class Graph {

    protected List<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList<>();
    }

    public Graph(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Vertex> getEdges(Integer id) {
        return this.vertices.get(id).getEdges();
    }

    void addEdge(int source, int destination) {
        this.vertices.get(source).getEdges().add(new Vertex(destination, vertices.get(destination).getValue()));
        this.vertices.get(destination).getEdges().add(new Vertex(source, vertices.get(source).getValue()));
    }

    public void bfs(Integer v) {
        Set<Vertex> discovered = new HashSet<>();
        Queue<Vertex> toVisit = new LinkedList<>();
        toVisit.add(vertices.get(v));
        discovered.add(vertices.get(v));
        while (!toVisit.isEmpty()) {
            Vertex current = toVisit.remove();
            System.out.println(current.getValue());
            for (Vertex edge : getEdges(current.getId())) {
                if (!discovered.contains(edge)) {
                    toVisit.add(edge);
                    discovered.add(edge);
                }
            }
        }
    }

    public void dfs() {
        Set<Integer> discovered = new HashSet<>();
        Set<Vertex> processed = new HashSet<>();
        Stack<Vertex> topologicalOrder = new Stack<>();
        for (Vertex vertex : this.vertices) {
            if (!discovered.contains(vertex.getId())) {
                dfs(vertex, discovered, processed, topologicalOrder);
            }
        }

        while (!topologicalOrder.isEmpty()) {
            System.out.println(topologicalOrder.pop().getValue());
        }
    }

    private void dfs(Vertex vertex, Set<Integer> discovered, Set<Vertex> processed, Stack<Vertex> topological) {
        List<Vertex> edges = getEdges(vertex.getId());
        //System.out.println(vertex.getValue());
        discovered.add(vertex.getId());
        for (Vertex edge : edges) {
            if (!discovered.contains(edge.getId())) {
                dfs(edge, discovered, processed, topological);
            }
        }
        //processed.add(vertex);

        //for topological sort do a post-order traversal
        topological.push(vertex);
//        System.out.println(vertex.getValue());
    }

    public void dfsIterative(Integer v) {
        Vertex root = vertices.get(v);
        Stack<Vertex> toVisit = new Stack<>();
        Set<Vertex> discovered = new HashSet<>();
        discovered.add(root);
        toVisit.add(root);
        while (!toVisit.isEmpty()) {
            Vertex vertex = toVisit.pop();
            System.out.println(vertex.getValue());
            for (Vertex edge : getEdges(vertex.getId())) {
                if (!discovered.contains(edge)) {
                    toVisit.push(edge);
                    discovered.add(edge);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Vertex> vertices = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex(i, i));
        }

        Graph graph = new DirectedGraph(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.dfs();
    }
}
