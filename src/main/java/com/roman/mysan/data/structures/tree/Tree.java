package com.roman.mysan.data.structures.tree;

public class Tree<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        bst.insert(24);
        bst.insert(98);
        bst.insert(0);
        bst.insert(283);
        bst.insert(3);
        bst.insert(97);
        bst.insert(99);
        bst.insert(100);
        System.out.print(bst.getSuccessor(0));
    }
}
