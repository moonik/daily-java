package com.roman.mysan.data.structures.tree;

public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T data) {
        root = insert(root, data);
    }

    private Node<T> insert(Node<T> root, T data) {
        if (root == null) {
            return new Node<T>(data);
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

    public boolean isBst(Node<Integer> root) {
        return isBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBst(Node<Integer> root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        int cmpToMin = root.getValue().compareTo(min);
        int cmpToMax = root.getValue().compareTo(max);
        return cmpToMin > 0 && cmpToMax < 0
                && isBst(root.getLeft(), min, root.getValue())
                && isBst(root.getRight(), root.getValue(), max);
    }

    public T getSuccessor(T value) {
        return getSuccessor(root, value);
    }

    private T getSuccessor(Node<T> root, T value) {
        Node<T> current = root;
        while (current != null) {
            int cmp = current.getValue().compareTo(value);
            if (cmp > 0) {
                current = current.getLeft();
            } else if (cmp < 0) {
                current = current.getRight();
            } else
                break;
        }
        if (current == null) {
            return null;
        }
        if (current.getRight() != null) {
            return findMin(current.getRight()).getValue();
        } else {
            Node<T> successor = null;
            Node<T> ancestor = root;
            while (ancestor != null) {
                int cmp = ancestor.getValue().compareTo(value);
                if (cmp > 0) {
                    successor = ancestor;
                    ancestor = ancestor.getLeft();
                } else
                    ancestor = ancestor.getRight();
            }
            return successor.getValue();
        }
    }

    public int getNodeCount() {
        return getNodeCount(root);
    }

    private int getNodeCount(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + getNodeCount(root.getLeft()) + getNodeCount(root.getRight());
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(height(root.getLeft()), height(root.getRight()));
    }
}