package com.roman.mysan.data.structures.tree;

public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {
        root = insert(root, data);
    }

    private Node<T> insert(Node<T> root, T data) {
        if (root == null) {
            return new Node<>(data);
        }
        int cmp = root.getValue().compareTo(data);
        if (cmp > 0) {
            root.setLeft(insert(root.getLeft(), data));
        } else if (cmp < 0) {
            root.setRight(insert(root.getRight(), data));
        } else
            root.setValue(data);
        return root;
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    private Node<T> delete(Node<T> root, T data) {
        if (root == null) {
            return null;
        }
        int cmp = root.getValue().compareTo(data);
        if (cmp > 0) {
            root.setLeft(delete(root.getLeft(), data));
        } else if (cmp < 0) {
            root.setRight(delete(root.getRight(), data));
        } else {
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            } else if (root.getRight() == null) {
                return root.getLeft();
            } else if (root.getLeft() == null) {
                return root.getRight();
            } else {
                Node<T> successor = findMin(root.getRight());
                root.setValue(successor.getValue());
                root.setRight(delete(root.getRight(), successor.getValue()));
            }
        }
        return root;
    }

    public Node<T> findMin(Node<T> root) {
        if (root.getLeft() == null) {
            return root;
        }
        return findMin(root.getLeft());
    }

    public void print() {
        print(root);
    }

    private void print(Node<T> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue());
        print(root.getLeft());
        print(root.getRight());
    }
}
