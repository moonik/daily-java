package com.roman.mysan.data.structures.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphAdjMatrix {

    private boolean[][] adjMatrix;

    public GraphAdjMatrix(Integer v) {
        this.adjMatrix = new boolean[v][v];
    }

    private void addEdge(int source, int destination) {
        this.adjMatrix[source][destination] = true;
        this.adjMatrix[destination][source] = true;
    }

    public void dfs() {
        boolean[] visited = new boolean[adjMatrix.length];
        for (int v = 0; v < adjMatrix.length; v++) {
            if (!visited[v]) {
                dfs(v, adjMatrix, visited);
            }
        }
    }

    private void dfs(int v, boolean[][] adjMatrix, boolean[] visited) {
        visited[v] = true;
        System.out.println(v);
        for (int u = 0; u < adjMatrix[v].length; u++) {
            if (!visited[u] && adjMatrix[v][u]) {
                dfs(u, adjMatrix, visited);
            }
        }
    }

    public void dfsIter() {
        Stack<Integer> dfs = new Stack<>();
        boolean[] visited = new boolean[adjMatrix.length];
        for (int i = 0; i < adjMatrix.length; i++) {
            if (!visited[i]) {
                dfs.push(i);
                while (!dfs.empty()) {
                    Integer v = dfs.pop();
                    visited[v] = true;
                    System.out.println(v);
                    for (int u = 0; u < adjMatrix[v].length; u++) {
                        if (!visited[u] && adjMatrix[v][u]) {
                            visited[u] = true;
                            dfs.push(u);
                        }
                    }
                }
            }
        }
    }

    public void bfs() {
        Queue<Integer> bfs = new LinkedList<>();
        boolean[] visited = new boolean[adjMatrix.length];

        for (int i = 0; i < adjMatrix.length; i++) {
            if (!visited[i]) {
                bfs.add(i);
                while (!bfs.isEmpty()) {
                    Integer v = bfs.remove();
                    visited[v] = true;
                    System.out.println(v);
                    for (int u = 0; u < adjMatrix[v].length; u++) {
                        if (!visited[u] && adjMatrix[v][u]) {
                            visited[u] = true;
                            bfs.add(u);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GraphAdjMatrix graphAdjMatrix = new GraphAdjMatrix(7);
        graphAdjMatrix.addEdge(0, 1);
        graphAdjMatrix.addEdge(1, 2);
        graphAdjMatrix.addEdge(2, 3);
        graphAdjMatrix.addEdge(2, 4);
        graphAdjMatrix.addEdge(0, 5);
        graphAdjMatrix.addEdge(5, 6);
        graphAdjMatrix.addEdge(6, 2);

        //graphAdjMatrix.dfs();
        //graphAdjMatrix.dfsIter();
        graphAdjMatrix.bfs();
    }
}
