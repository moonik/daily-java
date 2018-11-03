package com.roman.mysan.data.structures.tree;

import java.util.Iterator;
import java.util.LinkedList;

public class Node<T> {
    private T value;
    private LinkedList<Node<T>> children;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
        this.value = value;
        this.children = new LinkedList<>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public Iterator<Node<T>> getChildren() {
        return children.iterator();
    }

    public Iterator<Node<T>> getDescChildren() {
        return children.descendingIterator();
    }

    public void addChildren(Node<T> child) {
        this.children.add(child);
    }
}
