
/*  Paul Truong Nguyen
 *  masc2188
 *  
 *  Assignment 1: Implement the OrderedListADT interface and create an OrderedArrayList.class.
 *  The class is not an array so elements will be accessed through methods inside of the class.
 **/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayList<E> implements OrderedListADT<E> {
    private int maxSize, currentSize;
    private E[] storage;
    
    // Default constructor for class OrderedArrayList
    public OrderedArrayList(int size) {
        maxSize = size;
        currentSize = 0;
        storage = (E[]) new Object[maxSize];
    }

    //  Adds the Object obj to the list in the correct position as determined by the Comparable interface.
    public void insert(E obj) {
        if (isFull())
            throw new RuntimeException("Cannot insert into container with full storage.");
        int where = findInsertionPoint(obj, 0, currentSize - 1);
	    insertShift(obj, where);
    }

    //  Removes and returns the object located at the parameter index position (zero based).
    //  Throws IndexOutOfBoundsException if the index does not map to a valid position within the list.
    public E remove(int index) {
    	if((index < 0) && (index > storage.length))
            throw new IndexOutOfBoundsException("Accessing outside of storage size");
        return removeShift(index);
    }

    //  Removes and returns the parameter object obj from the list if the list contains it, null otherwise.
    public E remove(E obj) {
    	if(!contains(obj))
	        return null;
    	int where = find(obj);
        return removeShift(where);
    }

    //  Removes and returns the smallest element in the list and null if the it is empty.
    public E removeMin() {
    	if (!isEmpty())
        	return remove(0);
	    return null;
    }

    //  Removes and returns the largest element in the list and null if the it is empty.
    public E removeMax() {
    	if(!isEmpty())
        	return remove(currentSize - 1);
	    return null;
    }

    //  Returns the parameter object located at the parameter index position (zero based).
    //  Throws IndexOutOfBoundsException if the index does not map to a valid position within the underlying array
    public E get(int index) {
        if((index < 0) && (index > storage.length))
            throw new IndexOutOfBoundsException("Accessing outside of storage size");
        return storage[index];
    }

    //  Returns the list object that matches the parameter, and null if the list is empty.
    //  Also returns null if the obj is NOT in the list.
    //  This method is stable, if obj matches more than one element, the element that
    //  has been in the list longest is returned.
    public E get(E obj) {
        if (isEmpty())
            return null;
        int temp = find(obj);
        if(temp >= 0)
            return get(temp); 
        return null;
    }

    //  Returns the index of the first element that matches the parameter obj
    //  and -1 if the item is not in the list.
    public int find(E obj) {
	    return binSearch(obj, 0, currentSize - 1);
    }

    //  Returns true if the parameter object obj is in the list, false otherwise.
    public boolean contains(E obj) {
        return (find(obj) >= 0);
    }

    //  The list is returned to an empty state.
    public void clear() {
        currentSize = 0;
    }

    //  Returns true if the list is empty, otherwise false
    public boolean isEmpty() {
        return currentSize == 0;
    }

    //  Returns true if the list is full, otherwise false
    public boolean isFull() {
        return currentSize == maxSize;
    }

    //  Returns the number of Objects currently in the list.
    public int size() {
        return currentSize;
    }

    //  Returns an Iterator of the values in the list, presented in
    //  the same order as the list.
    public Iterator<E> iterator() {
        return new IteratorHelper();
    }
    
    // IteratorHelper class implements iterator interface and methods
    class IteratorHelper implements Iterator<E> {
        private int iteratorIndex;

        // constructor
        public IteratorHelper() {
            iteratorIndex = 0;
        }

        public boolean hasNext() {
            return iteratorIndex < currentSize;
        }

        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return storage[iteratorIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    // Returns the identified list object that matches the parameters. 
    // Non-recursive binary search method idenifites first instance of any existing duplicates in the list
    private int binSearch(E key, int lo, int hi){
	    int mid;
	    int result = -1;
	    while(lo <= hi) {
		    if (lo == hi)
			    mid = lo;
		    mid = ((lo + hi) / 2);
		    if ((((Comparable<E>)key).compareTo(storage[mid])) == 0) {
			    result = mid;
		    	hi = mid - 1;
		    } else if ((((Comparable<E>)key).compareTo(storage[mid])) < 0)
		    	hi = mid - 1;
	    	else
		    	lo = mid + 1;
	    }
	    return result;
    }
    
    // Binary search method that continuously returns a not-found condition to find proper
    // insertion point. Recursive method.
    private int findInsertionPoint(E key, int lo, int hi) {
        if (hi < lo)
            return lo;
        int mid = (lo + hi) / 2;
        if ((((Comparable<E>)key).compareTo(storage[mid])) < 0)
            return findInsertionPoint(key, lo, mid - 1);
        return findInsertionPoint(key, mid + 1, hi);
    }

    // Shift method for when method insert is called
    // Shifts list elements to the right of the insert element
     private void insertShift(E obj, int location) {
         currentSize++;
         if (currentSize < maxSize){
             if (storage[location] == null)
                 storage[location] = obj;
             for (int i = currentSize - 1; i >= location; i--)
                 storage[i + 1] = storage[i];
             storage[location] = obj;
         }
    } 
    
    // Shift method for when method remove is called
    // Shifts list elements left from all elements after the removed element
    private E removeShift(int location) {
        E save = storage[location];
        for(int i = location; i <= currentSize - 2; i++)
            storage[i] = storage[i + 1];

        currentSize--;
        return save;
    }
}
