package com.roman.mysan.data.structures.trie;

public class RWayTrie {

    private static final int R = 256; //ASCII chars
    private Node root = new Node();

    private static class Node {
        private Object value;
        private Node[] next = new Node[R];
        //private Map<String, Node> children = new HashMap<>();
        //private List<String> children = new ArrayList<>();
    }

    public void put(String key, Object value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node root, String key, Object value, int charAt) {
        if (root == null) {
            root = new Node();
        }
        if (charAt == key.length()) {
            root.value = value;
            return root;
        }
        char c = key.charAt(charAt);
        root.next[c] = put(root.next[c], key, value, charAt+1);
        return root;
    }

    public boolean contains(String key) {
        return get(key) != null;
    }

    public Object get(String key) {
        Node n = get(root, key, 0);
        if (n == null) {
            return null;
        }
        return n.value;
    }

    private Node get(Node root, String key, int charAt) {
        if (root == null) {
            return null;
        }
        if (charAt == key.length()) {
            return root;
        }
        char c = key.charAt(charAt);
        return get(root.next[c], key, charAt+1);
    }

    public static void main(String[] args) {
        RWayTrie trie = new RWayTrie();
        trie.put("hello", 13);
        System.out.println(trie.contains("hello"));
    }
}
