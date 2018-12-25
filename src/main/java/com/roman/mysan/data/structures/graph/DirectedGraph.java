package com.roman.mysan.data.structures.graph;

import java.util.List;

public class DirectedGraph extends Graph {

    public DirectedGraph(List<Vertex> vertices) {
        super(vertices);
    }

    @Override
    void addEdge(int source, int destination) {
        this.vertices.get(source).getEdges().add(new Vertex(destination, vertices.get(destination).getValue()));
    }
}
