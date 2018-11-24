package com.roman.mysan.algorithms;

import com.roman.mysan.data.structures.tree.Node;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeSearch<T extends Comparable<T>> {

    public void bfs(Node<T> root) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        bfs(queue);
    }

    private void bfs(Queue<Node<T>> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Node<T> node = queue.remove();
        System.out.println(node.getValue());
        if (node.getLeft() != null) {
            queue.add(node.getLeft());
        }
        if (node.getRight() != null) {
            queue.add(node.getRight());
        }
        bfs(queue);
    }

    public void bfsIteration(Node<T> root) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            System.out.println(node.getValue());
            if (node.getLeft() != null) {
                queue.add(node.getLeft());
            }
            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }
    }

    public void dfs(Node<T> node) {
        if (node == null) {
            return;
        }
        System.out.println(node.getValue()); // pre-order
        dfs(node.getLeft());
        System.out.println(node.getValue()); // inorder
        dfs(node.getRight());
        System.out.println(node.getValue()); // post-order
    }

    public void dfsIteration(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<T> node = stack.pop();
            System.out.println(node.getValue());
            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public void bfsTree(Node<T> root) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        bfsTree(queue);
    }

    private void bfsTree(Queue<Node<T>> queue) {
        if (queue.isEmpty()) {
            return;
        }
        Node<T> node = queue.remove();
        System.out.println(node.getValue());
        Iterator<Node<T>> children = node.getChildren();
        children.forEachRemaining(queue::add);
        bfsTree(queue);
    }

    public void bfsTreeIteration(Node<T> root) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> node = queue.remove();
            System.out.println(node.getValue());
            Iterator<Node<T>> children = node.getChildren();
            children.forEachRemaining(queue::add);
        }
    }

    public void dfsTree(Node<T> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue()); // pre-order
        Iterator<Node<T>> children = root.getChildren();
        children.forEachRemaining(this::dfsTree);
    }

    public void dfsTreeIteration(Node<T> root) {
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            Node<T> node = stack.pop();
            System.out.println(node.getValue());
            Iterator<Node<T>> children = node.getDescChildren();
            children.forEachRemaining(stack::push);
        }
    }
}
