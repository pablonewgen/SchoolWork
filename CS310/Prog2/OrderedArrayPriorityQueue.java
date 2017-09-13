/*    
    Paul T. Nguyen
    masc2188
    
    OrderedArrayPriorityQueue is the Array-Based implementation of 
    PriorityQueue Interface. It is implemented as an Ordered Array. 
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

public class OrderedArrayPriorityQueue<E> implements PriorityQueue<E> {
    private static final int DEFAULT_MAX_CAPACITY = 1000;
    private OrderedArrayList<E> orderedArrayList;
    
    // Constructor that is created by a defined array size.
    public OrderedArrayPriorityQueue(int size) {
    	orderedArrayList = new OrderedArrayList<E>(size);
    }
    
    // Default constructor with predefined array size.	
    public OrderedArrayPriorityQueue() {
    	this(DEFAULT_MAX_CAPACITY);
    }
    
    //  Inserts a new object into the priority queue.  Returns true if
    //  the insertion is successful.  If the PQ is full, the insertion
    //  is aborted, and the method returns false.
    public boolean insert(E obj) {
    	if (isFull())
		    return false;
	    orderedArrayList.insert(obj);
    	return true;
    }  
   
    //  Removes the object of highest priority that has been in the
    //  PQ the longest, and returns it.  Returns null if the PQ is empty.
    public E remove() {
    	if (isEmpty())
		    return null;
    	return orderedArrayList.removeMin();
    }

    // Method used for empirical timing tests. Not originally a part of the PriorityQueue ADT.
    public E removeMax() {
        if (isEmpty())
            return null;
        return orderedArrayList.removeMax();
    }
   
    //  Returns the object of highest priority that has been in the
    //  PQ the longest, but does NOT remove it. 
    //  Returns null if the PQ is empty.
    public E peek() {
        if (isEmpty())
		    return null;
    	return orderedArrayList.get(0);
    }   
    
    //  Returns true if the priority queue contains the specified element
    public boolean contains(E obj) {
    	return orderedArrayList.contains(obj);
    } 
   
    //  Returns the number of objects currently in the PQ.
    public int size() {
    	 return orderedArrayList.size();
    }
      
    //  Returns the PQ to an empty state.
    public void clear() {
    	orderedArrayList.clear();
    }
   
    //  Returns true if the PQ is empty, otherwise false
    public boolean isEmpty() {
    	return orderedArrayList.isEmpty();
    }
   
    //  Returns true if the PQ is full, otherwise false.  List based
    //  implementations should always return false.
    public boolean isFull() {
    	return orderedArrayList.isFull();
    }  
    
    //  Returns an iterator of the objects in the PQ, in no particular
    //  order.
    public Iterator<E> iterator() {
    	return orderedArrayList.iterator();
    }
            
} 
