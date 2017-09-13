/*  
    Paul T. Nguyen
    masc2188

    OrderedListPriorityQueue is the Linked List implementation of 
    PriorityQueue Interface. Its implemented as an ordered linked list.
    The PriorityQueue ADT may store objects in any order.  However,
    removal of objects from the PQ must follow specific criteria.
    The object of highest priority that has been in the PQ longest
    must be the object returned by the remove() method.  FIFO return
    order must be preserved for objects of identical priority.
   
    Ranking of objects by priority is determined by the Comparable<E>
    interface.  All objects inserted into the PQ must implement this
    interface.
*/   

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;

public class OrderedListPriorityQueue<E> implements PriorityQueue<E> {
    public static final int DEFAULT_MAX_CAPACITY = 1000;
    private Node<E> head;
    private int currentSize;
    private long modCounter;
    
    private class Node<E> {
    	E data;
		Node<E> next;
	
    	public Node(E data) {
    		this.data = data;
			next = null;
    	}
    }
    
    // Default constructor for the linked list
    public OrderedListPriorityQueue() {
    	head = null;
		currentSize = 0;
		modCounter = 0;
    }
    
    //  Inserts a new object into the priority queue.  Returns true if
    //  the insertion is successful.  If the PQ is full, the insertion
    //  is aborted, and the method returns false.
    public boolean insert(E obj) {
    	Node<E> newNode = new Node<E>(obj);
		Node<E> previous = null;
		Node<E> current = head;
	
		while (current != null && ((Comparable<E>)obj).compareTo(current.data) >= 0) {
			previous = current;
			current = current.next;
		}
		if (previous == null) {
			newNode.next = head;
			head = newNode;
		}
		else {
			previous.next = newNode;
			newNode.next = current;
		}
		currentSize++;
		modCounter++;
		return true;
    }   
   
    //  Removes the object of highest priority that has been in the
    //  PQ the longest, and returns it.  Returns null if the PQ is empty.
    public E remove() {
    	if (isEmpty())
			return null;
		E temp = head.data;
		head = head.next;
    	--currentSize;
    	modCounter++;
		return temp;
    }
   
    //  Returns the object of highest priority that has been in the
    //  PQ the longest, but does NOT remove it. 
    //  Returns null if the PQ is empty.
    public E peek() {
    	if (head == null)
			return null;
		return head.data;
    }   
    
    //  Returns true if the priority queue contains the specified element
    public boolean contains(E obj) {
    	Node<E> temp = head;
		while(temp != null) {
			if (((Comparable<E>)obj).compareTo(temp.data) == 0)
				return true;
			temp = temp.next;
		}
		return false;
    }
   
    //  Returns the number of objects currently in the PQ.
    public int size() {
    	return currentSize;
    }
     
    //  Returns the PQ to an empty state.
    public void clear() {
    	head = null;
		currentSize = 0;
		modCounter = 0;
    }
   
    //  Returns true if the PQ is empty, otherwise false
    public boolean isEmpty() {
    	if (head == null)
			return true;
		return false;
    }
   
    //  Returns true if the PQ is full, otherwise false.  List based
    //  implementations should always return false.
    public boolean isFull() {
    	return false;
    }  
    
    //  Returns an iterator of the objects in the PQ, in no particular
    //  order.
    public Iterator<E> iterator() {
    	return new IteratorHelper();
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
	    if(stateCheck != modCounter) {
			throw new ConcurrentModificationException();
	    }
            return iteratorPointer != null;
        }

        public E next() {
            if(!hasNext()) {
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

	// Method used for empirical timing tests. Not originally a part of the PriorityQueue ADT.
	public void insertFirst(E obj) {
		Node<E> newNode = new Node<>(obj);
		if (head == null)
			head = newNode;
		else {
			newNode.next = head;
			head = newNode;
		}
		currentSize++;
		modCounter++;
	}
} 
