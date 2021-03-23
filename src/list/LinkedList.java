package list;

import static list.Position.*;

public class LinkedList <G> implements List<G>{

    private static class Node <T> {

        private final T data;
        private Node<T> previous;
        private Node<T> next;

        Node(T data){
            this.data=data;
        }
    }

    private Node<G> head;
    private Node<G> tail;
    private int size;

    public LinkedList(){
        listsCount++;
    }

    private static int listsCount = 0;

    public static int getListsCount() { return listsCount;}

    public class Iterator{

        private Node<G> currentNode;

        public Iterator(){
            this.currentNode = head;
        }

        public Iterator(Iterator iterator){
            this.currentNode = iterator.currentNode;
        }

        public boolean hasNext(){
            return currentNode != null;
        }

        public G next(){
            G data = currentNode.data;

            currentNode = currentNode.next;

            return data;
        }

        Node<G> getCurrentNode(){ return currentNode;}
    }

    public class ReverseIterator {

        private Node<G> currentNode;

        public ReverseIterator() {
            this.currentNode = tail;
        }


        public boolean hasNext(){
            return currentNode != null;
        }

        public G next(){
            G data = currentNode.data;

            currentNode = currentNode.previous;

            return data;
        }
    }

    public void add(G data) {
        Node<G> node = new Node<>(data);

        node.previous = tail;

        if (tail != null) {
            tail.next = node;
        }

        if (head == null) {
            head = node;
        }

        tail = node;
        size++;
    }

    public G get(int index) {
        Node<G> currentNode = head;
        int currentIndex = 0;

        while (currentIndex < index) {
            currentNode = currentNode.next;
            currentIndex++;
        }

        return currentNode.data;
    }

    public void delete(int index) {
        Node<G> currentNode = head;
        int currentIndex = 0;

        if (index < 0 || index >= size) {
            return;
        }

        size--;

        if (size == 0) {
            head = null;
            tail = null;
            return;
        }

        if (index == 0) {
            head = head.next;
            head.previous = null;
        }

        if (index == size) {
            tail = tail.previous;
            tail.next = null;
        }

        if (index > 0 && index < size) {
            while (currentIndex < index) {
                currentNode = currentNode.next;
                currentIndex++;
            }
            currentNode.previous.next = currentNode.next;
            currentNode.next.previous = currentNode.previous;
        }


    }

    public Iterator getIterator() {
        return new Iterator();
    }

    public ReverseIterator getReverseIterator() {
        return new ReverseIterator();
    }

    public void insert(G data, Position position, Iterator it) {

        Node<G> newNode = new Node<>(data);
        Node<G> currentNode = it.getCurrentNode();

        if (position == AFTER) {
            newNode.next = currentNode.next;
            newNode.previous = currentNode;
            currentNode.next = newNode;
            if (newNode.next != null) {
                newNode.next.previous = newNode;
            } else {
                tail = newNode;
            }
        } else if (position == BEFORE) {
            newNode.previous = currentNode.previous;
            newNode.next = currentNode;
            currentNode.previous = newNode;
            if (newNode.previous != null) {
                newNode.previous.next = newNode;
            } else {
                head = newNode;
            }
        } else {
            System.out.println("No conozco el valor de position");
        }
        size++;
    }

    public int getSize() {
        return size;
    }

}
