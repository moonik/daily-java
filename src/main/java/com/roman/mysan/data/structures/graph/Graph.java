package com.roman.mysan.data.structures.graph;

import java.util.*;

public class Graph {

    List<Vertex> vertices;

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

    public boolean bfs(Integer v) {
        Set<Vertex> discovered = new HashSet<>();
        Queue<Vertex> toVisit = new LinkedList<>();
        boolean isBipartite = true;
        toVisit.add(vertices.get(v));
        discovered.add(vertices.get(v));
        while (!toVisit.isEmpty()) {
            Vertex current = toVisit.remove();
            System.out.println(current.getValue());
            for (Vertex edge : getEdges(current.getId())) {
                if (!discovered.contains(edge)) {
                    if (edge.getColor().equals(current.getColor())) { //checks whether graph is bipartite
                        isBipartite = false;
                    }
                    toVisit.add(edge);
                    discovered.add(edge);
                }
            }
        }
        return isBipartite;
    }

    /**
     * Detects a cycle for Undirected Graph
     * @param g
     * @return
     */
    boolean hasCycle(Graph g) {
        Set<Integer> discovered = new HashSet<>();
        Map<Integer, Integer> parents = new HashMap<>();
        for (Vertex v : g.vertices) {
            if (!discovered.contains(v.getId())) {
                return dfsForCycle(v, discovered, parents);
            }
        }
        return false;
    }

    private boolean dfsForCycle(Vertex v, Set<Integer> discovered, Map<Integer, Integer> parents) {
        discovered.add(v.getId());
        for (Vertex u : getEdges(v.getId())) {
            if (!discovered.contains(u.getId())) {
                parents.put(u.getId(), v.getId());
                boolean hasCycle = dfsForCycle(u, discovered, parents);
                if (hasCycle) {
                    return hasCycle;
                }
            } else if (!parents.get(v.getId()).equals(u.getId())) {
                System.out.println("Cycle detected between: " + v.getId() + " and " + u.getId());
                printCyclePath(parents, v.getId(), u.getId());
                return true;
            }
        }
        return false;
    }

    public void printCyclePath(Map<Integer, Integer> parents, Integer end, Integer start) {
        Integer current = end;
        StringBuilder path = new StringBuilder();
        path.append(start).append(" ");
        while (current != null) {
            path.append(current).append(" ");
            current = parents.get(current);
        }
        path.reverse();
        System.out.println(path.toString());
    }

    public void dfs() {
        Set<Integer> discovered = new HashSet<>();
        Stack<Vertex> topologicalOrder = new Stack<>();
        int components = 0;
        for (Vertex vertex : this.vertices) {
            if (!discovered.contains(vertex.getId())) {
                components++;
                dfs(vertex, discovered, topologicalOrder);
            }
        }
        System.out.println("Components: " + components);

        while (!topologicalOrder.isEmpty()) {
            System.out.println(topologicalOrder.pop().getValue());
        }
    }

    void dfs(Vertex vertex, Set<Integer> discovered, Stack<Vertex> topological) {
        List<Vertex> edges = getEdges(vertex.getId());
        //System.out.println(vertex.getValue());
        discovered.add(vertex.getId());
        for (Vertex edge : edges) {
            if (!discovered.contains(edge.getId())) {
                dfs(edge, discovered, topological);
            }
        }
        //for topological sort do a post-order traversal
        //stack is needed to save the finishing time of vertices
        topological.push(vertex);
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
            vertices.add(new Vertex(i, i, "blue"));
        }

        DirectedGraph graph = new DirectedGraph(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(4, 0);

//        System.out.println("DFS: ");
//        graph.dfs();
//
        System.out.println("cycle detection in directed graph");
        graph.topologicalSort(graph);

        vertices.clear();
        for (int i = 0; i < 6; i++) {
            vertices.add(new Vertex(i, i, "blue"));
        }

        System.out.println("Cycle detection in undirected graph: ");
        WeightedGraph wgraph = new WeightedGraph(vertices);

        // graph without a cycle
        wgraph.addEdge(0, 1, 5);
        wgraph.addEdge(0, 2, 5);
        wgraph.addEdge(0, 3, 7);
        wgraph.addEdge(0, 4, 1);
        wgraph.addEdge(0, 5, 5);

        System.out.println("First execution: ");
        wgraph.hasCycle(graph);

        //adds cycle
        wgraph.addEdge(5, 2, 3);

        System.out.println("Second execution: ");
        wgraph.hasCycle(graph);
    }
}
