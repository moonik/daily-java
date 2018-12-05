package com.roman.mysan.data.structures.list.linked;

public class MyLinkedList<T extends Comparable<T>> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean empty() {
        return size == 0;
    }

    @Override
    public T valueAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size-1) {
            return tail.getValue();
        }
        Node<T> current = head;
        while (index != 0) {
            current = current.getNext();
            index--;
        }
        return current.getValue();
    }

    @Override
    public void pushFront(T value) {
        head = new Node<>(head, value);
        size++;
    }

    @Override
    public T popFront() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        T value = head.getValue();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public void pushBack(T value) {
        Node<T> node = new Node<>(null, value);
        if (empty()) {
            head = tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    @Override
    public T popBack() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().equals(tail)) {
                T value = current.getNext().getValue();
                tail = current;
                current.setNext(null);
                size--;
                return value;
            }
            current = current.getNext();
        }
        return popFront(); //if there is only 1 element in the list
    }

    @Override
    public T popBackWithNoTail() {
        Node<T> prev = null;
        Node<T> current = head;

        while (current.getNext() != null) {
            prev = current;
            current = current.getNext();
        }

        T value = current.getValue();

        if (prev == null) {
            head = null;
        } else
            prev.setNext(null);

        return value;
    }

    @Override
    public T front() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        return head.getValue();
    }

    @Override
    public T back() {
        if (empty()) {
            throw new IndexOutOfBoundsException();
        }
        return tail.getValue();
    }

    @Override
    public void insert(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = head;
        while (index-1 != 0) {
            index--;
            current = current.getNext();
        }
        Node<T> currentNext = current.getNext();
        Node<T> node = new Node<>(currentNext, value);
        current.setNext(node);
        if (node.getNext() == null) {
            tail = node;
        }
        size++;
    }

    @Override
    public void erase(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            popFront();
            return;
        }
        if (index == size-1) {
            popBack();
            return;
        }
        Node<T> current = head;
        while (index-1 != 0) {
            index--;
            current = current.getNext();
        }
        current.setNext(current.getNext().getNext());
        if (current.getNext() == null) {
            tail = current;
        }
        size--;
    }

    @Override
    public T valueNfromEnd(int n) {
        Node<T> current = head;
        while (n > 0) {
            current = current.getNext();
            if (current == null) {
                return null;
            }
            n--;
        }
        Node<T> behind = head;
        while (current.getNext() != null) {
            current = current.getNext();
            behind = behind.getNext();
        }
        return behind.getValue();
    }

    @Override
    public void reverse() {
        if (empty()) {
            return;
        }

        Node<T> current = head;
        Node<T> prev = null;
        Node<T> next;
        tail = current;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;
    }

    @Override
    public void removeValue(T value) {
        if (empty()) {
            return;
        }
        if (head.getValue().equals(value)) {
            popFront();
        } else if (tail.getValue().equals(value)) {
            popBack();
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                if (current.getNext().getValue().equals(value)) {
                    current.setNext(current.getNext().getNext());
                    if (current.getNext() == null) {
                        tail = current;
                    }
                }
            }
        }
    }

    @Override
    public Node<T> getMiddle() {
        Node<T> current = head;
        Node<T> next = current.getNext();
        if (next == null) {
            return current;
        }

        while (next != null) {
//            current = current.getNext();  if you want the left halve to be bigger
//                                          (1 element after middle and 2 before) [1,2,3,4] => 3
            if (next.getNext() != null) {
                next = next.getNext().getNext();
            } else
                break;
            current = current.getNext(); //left halve will be smaller
                                        // (1 element before middle and 2 after) [1,2,3,4] => 2
        }
        return current;
    }

    public Node<T> mergeSort(Node<T> head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        Node first = head;
        Node second = head.getNext();

        while (second != null) {
            if (second.getNext() != null) {
                second = second.getNext().getNext();
            } else
                break;
            first = first.getNext();
        }
        second = first.getNext();
        first.setNext(null);

        Node left = mergeSort(head);
        Node right = mergeSort(second);

        return merge(left, right);
    }

    private Node<T> merge(Node first, Node second) {
        Node dummy = new Node(null, 0);
        Node tail = dummy;

        while (true) {
            if (first == null) {
                tail.setNext(second);
                break;
            }
            if (second == null) {
                tail.setNext(first);
                break;
            }
            if (first.getValue().compareTo(second.getValue()) >= 0) {
                tail.setNext(second);
                second = second.getNext();
            } else {
                tail.setNext(first);
                first = first.getNext();
            }
            tail = tail.getNext();
        }
        return dummy.getNext();
    }

    public static void main(String[] args) {
        Node h1 = new Node(null, 99);
        Node n1 = new Node(null, 5);
        Node n2 = new Node(null, 91);
        Node n3 = new Node(null, 7);
        h1.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);

        Node h2 = new Node(null, 0);
        n3.setNext(h2);
        Node n4 = new Node(null, 4);
        Node hn5 = new Node(null, 3);
        h2.setNext(n4);
        n4.setNext(hn5);

        MyLinkedList<Integer> s = new MyLinkedList<>();
        Node head = s.mergeSort(h1);
        while (head != null) {
            System.out.println(head.getValue());
            head = head.getNext();
        }
    }
}
