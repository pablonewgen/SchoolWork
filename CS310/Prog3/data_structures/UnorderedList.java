/**
 * Paul Truong Nguyen
 * masc2188
 */
package data_structures;

// INSERT INTO package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedList<E> implements Iterable<E> {
    private Node<E> head, tail;
    private int currentSize;
    private long modCounter;

    //Unordered Linked List constructor
    public UnorderedList() {
        head = tail = null;
        currentSize = 0;
        modCounter = 0;
    }

    public void insert(E obj) {
        Node<E> newNode = new Node<>(obj);
        newNode.next = head;
        head = newNode;
        currentSize++;
        modCounter++;
    }

    // Remove from anywhere in the list
    public boolean remove(E obj) {
        Node<E> previous = null;
        Node<E> current = head;
        while (current != null) {
            if (((Comparable<E>) obj).compareTo(current.data) == 0) {
                if (current == head)
                    removeFirst();
                else if (current == tail)
                    removeLast();
                else {
                    previous.next = current.next;
                }
                currentSize--;
                modCounter++;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public E removeFirst() {
        E temp = head.data;
        head = head.next;
        if (head == null)
            tail = null;
        return temp;
    }

    public E removeLast() {
        Node<E> previous = null, current = head;
        E temp = head.data;
        head = head.next;
        if (head == tail) {
            head = tail = null;
        } else {
            while (current != tail) {
                previous = current;
                current = current.next;
            }
            previous.next = null;
            tail = previous;
        }
        return temp;
    }

    // Find the obj in the list
    public E find(E obj) {
        Node<E> temp = head;
        while (temp != null) {
            if (((Comparable<E>) obj).compareTo(temp.data) == 0)
                return temp.data;
            temp = temp.next;
        }
        return null;
    }

    public void clear() {
        head = tail = null;
        currentSize = 0;
        modCounter = 0;
    }

    // Return the current size of the structure
    public int size() {
        return currentSize;
    }

    // Checks if the list is empty
    public boolean isEmpty() {
        if (head == null)
            return true;
        return false;
    }

    // Checks to see if the list is full.
    public boolean isFull() {
        return false;
    }

    public Iterator iterator() {
        return new IteratorHelper();
    }

    // Node Class
    private class Node<T> {
        T data;
        Node<E> next;

        public Node(T data) {
            this.data = data;
            next = null;
        }
    }

    private class IteratorHelper implements Iterator<E> {
        private Node<E> iteratorPointer;
        private long stateCheck;

        // constructor
        public IteratorHelper() {
            iteratorPointer = (Node<E>) head;
            stateCheck = modCounter;
        }

        public boolean hasNext() {
            if (stateCheck != modCounter) {
                throw new ConcurrentModificationException();
            }
            return iteratorPointer != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E temp = iteratorPointer.data;
            iteratorPointer = iteratorPointer.next;
            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
