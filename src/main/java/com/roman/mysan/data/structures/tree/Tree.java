package com.roman.mysan.data.structures.tree;

import com.roman.mysan.algorithms.TreeSearch;

public class Tree {

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        root.addChildren(node2);
        root.addChildren(node3);
        root.addChildren(node4);
        node4.addChildren(new Node<>(99));

        Node<Integer> n1 = new Node<>(5);
        Node<Integer> n2 = new Node<>(6);
        Node<Integer> n3 = new Node<>(7);
        Node<Integer> n4 = new Node<>(8);
        Node<Integer> n5 = new Node<>(9);
        Node<Integer> n6 = new Node<>(10);
        node2.addChildren(n1);
        node2.addChildren(n2);
        node3.addChildren(n3);
        node3.addChildren(n4);
        node3.addChildren(n5);
        node3.addChildren(n6);

        Node<Integer> n11 = new Node<>(11);
        Node<Integer> n22 = new Node<>(12);
        Node<Integer> n33 = new Node<>(13);
        n1.addChildren(n11);
        n11.addChildren(n22);
        n11.addChildren(n33);
        TreeSearch treeSearch = new TreeSearch();
        treeSearch.bfsTree(root);
        //treeSearch.dfsTree(root);
        //treeSearch.bfsTreeIteration(root);
        //treeSearch.dfsTreeIteration(root);
    }
}
