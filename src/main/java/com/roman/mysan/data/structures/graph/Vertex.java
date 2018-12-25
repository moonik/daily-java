package com.roman.mysan.data.structures.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private Integer weight;
    private Integer value;
    private int id;
    private List<Vertex> edges = new ArrayList<>();

    public Vertex(Integer id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Vertex(Integer id, Integer value, Integer weight) {
        this.id = id;
        this.weight = weight;
        this.value = value;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<Vertex> getEdges() {
        return edges;
    }

    public void setEdges(List<Vertex> edges) {
        this.edges = edges;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}