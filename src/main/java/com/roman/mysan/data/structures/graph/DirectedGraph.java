package com.roman.mysan.data.structures.graph;

import java.util.*;

public class DirectedGraph extends Graph {

    public DirectedGraph(List<Vertex> vertices) {
        super(vertices);
    }

    @Override
    void addEdge(int source, int destination) {
        this.vertices.get(source).getEdges().add(new Vertex(destination, vertices.get(destination).getValue()));
    }

    @Override
    public boolean hasCycle(Graph g) {
        Set<Integer> discovered = new HashSet<>();
        Set<Integer> explored = new HashSet<>();
        Map<Integer, Integer> parents = new HashMap<>();
        for (Vertex v : g.vertices) {
            if (!discovered.contains(v.getId())) {
                return dfs(v, discovered, explored, parents);
            }
        }
        return false;
    }

    private boolean dfs(Vertex v, Set<Integer> discovered, Set<Integer> explored, Map<Integer, Integer> parents) {
        discovered.add(v.getId());
        for (Vertex u : getEdges(v.getId())) {
            if (!discovered.contains(u.getId())) {
                parents.put(u.getId(), v.getId());
                boolean hasCycle = dfs(u, discovered, explored, parents);
                if (hasCycle) {
                    return hasCycle;
                }
            } else if (!explored.contains(u.getId())) {
                System.out.println("Found a cycle!");
                printCyclePath(parents, v.getId(), u.getId());
                return true;
            }
        }
        explored.add(v.getId());
        return false;
    }

    /**
     * Check topological sort using DFS in Graph.java
     * @return vertices in topological order
     */
    public List<Vertex> topologicalSort(Graph g) {
        if (hasCycle(g)) {
            System.out.println("Detected graph with cycle!");
            return null;
        }
        List<Vertex> result = new ArrayList<>();
        Map<Integer, Integer> inDegree = countInDegree(g);
        Queue<Vertex> queue = new LinkedList<>();

        inDegree.forEach((key, value) -> {
            if (value == 0) {
                queue.add(g.vertices.get(key));
            }
        });

        int c = 0; // counter to check whether we have a cycle
        while (!queue.isEmpty()) {
            Vertex v = queue.remove();
            result.add(v);

            for (Vertex u : getEdges(v.getId())) {
                Integer n = inDegree.get(u.getId());
                if (--n == 0) {
                    queue.add(u);
                } else
                    inDegree.put(u.getId(), n);
            }
        }
        if (c > g.vertices.size()) {
            System.out.println("Cycle encountered :c");
        }
        result.forEach(s -> System.out.println(s.getValue()));
        return result;
    }

    private Map<Integer, Integer> countInDegree(Graph g) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        for (Vertex v : g.vertices) {
            inDegree.put(v.getId(), 0);
        }
        for (Vertex v : g.vertices) {
            for (Vertex u : v.getEdges()) {
                Integer n = inDegree.get(u.getId());
                inDegree.put(u.getId(), n+1);
            }
        }
        return inDegree;
    }

    /**
     * Strongly connected components
     * @param graph
     * @return list of strongly connected components
     */
    public List<List<Integer>> scc(Graph graph) {
        Set<Integer> discovered = new HashSet<>();
        Stack<Vertex> finishedTime = new Stack<>();
        for (Vertex v : graph.vertices) {
            if (!discovered.contains(v.getId())) {
                dfs(v, discovered, finishedTime);
            }
        }

        Map<Integer, List<Integer>> reversed = reverse(graph);
        List<List<Integer>> components = new ArrayList<>();
        discovered.clear();
        while (!finishedTime.isEmpty()) {
            Vertex v = finishedTime.pop();
            List<Integer> component = new ArrayList<>();
            if (!discovered.contains(v.getId())) {
                reversedDfs(v.getId(), reversed, discovered, component);
                components.add(component);
            }
        }
        return components;
    }

    private void reversedDfs(Integer v, Map<Integer, List<Integer>> reversed, Set<Integer> visited, List<Integer> component) {
        visited.add(v);
        component.add(v);
        for (Integer u : reversed.get(v)) {
            if (!visited.contains(u)) {
                reversedDfs(u, reversed, visited, component);
            }
        }
    }

    private Map<Integer, List<Integer>> reverse(Graph graph) {
        Map<Integer, List<Integer>> reversed = new HashMap<>();
        for (Vertex v : graph.vertices) {
            for (Vertex u : v.getEdges()) {
                List<Integer> vertices = reversed.get(u.getId());
                if (vertices == null) {
                    vertices = new ArrayList<>();
                }
                vertices.add(v.getId());
                reversed.put(u.getId(), vertices);
            }
        }
        return reversed;
    }

    public static void main(String[] args) {
        List<Vertex> vertices = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            vertices.add(new Vertex(i, i));
        }

        DirectedGraph graph = new DirectedGraph(vertices);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 6);
        graph.addEdge(1, 3);
        graph.addEdge(2, 0);
        graph.addEdge(3, 5);
        graph.addEdge(4, 3);
        graph.addEdge(5, 4);
        graph.addEdge(6, 5);

        graph.scc(graph);
    }
}
