package com.roman.mysan.data.structures.trie;

public class TernarySearchTrie {

    private Node root;

    private static class Node {
        private Object value;
        private char c;
        private Node left, mid, right;
    }

    public void put(String key, Object value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node root, String key, Object value, int charAt) {
        char c = key.charAt(charAt);

        if (root == null) {
            root = new Node();
            root.c = c;
        }
        if (c < root.c) {
            root.left = put(root.left, key, value, charAt);
        } else if (c > root.c) {
            root.right = put(root.right, key, value, charAt);
        } else if (c < key.length()-1) {
            root.mid = put(root.mid, key, value, charAt+1);
        } else
            root.value = value;

        return root;
    }

    public Object get(String key) {
        return get(root, key, 0);
    }

    private Object get(Node root, String key, int charAt) {
        if (root == null) {
            return null;
        }
        char c = key.charAt(charAt);

        if (c < root.c) {
            return get(root.left, key, charAt);
        } else if (c > root.c) {
            return get(root.right, key, charAt);
        } else if (c < key.length()-1) {
            return get(root.mid, key, charAt+1);
        } else
            return root;
    }
}
