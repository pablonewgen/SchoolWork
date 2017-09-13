/**
 * Paul Truong Nguyen
 * masc2188
 */

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K, V> implements DictionaryADT<K, V> {
    private Node<K, V> root;
    private int currentSize;
    private long modCounter;
    private boolean usedSuccessorLast;
    private K foundKey;

    public BinarySearchTree() {
        root = null;
        foundKey = null;
        currentSize = 0;
        modCounter = 0;
        usedSuccessorLast = false;
    }

    // Returns true if the dictionary has an object identified by
    // key in it, otherwise false.
    public boolean contains(K key) {
        return find(key, root) != null;
    }

    // Adds the given key/value pair to the dictionary.  Returns
    // false if the dictionary is full, or if the key is a duplicate.
    // Returns true if addition succeeded.
    public boolean add(K key, V value) {
        if (root == null) {
            root = new Node<K, V>(key, value);
            currentSize++;
            modCounter++;
            return true;
        } else
            return insert(key, value, root, null, false);
    }

    // Deletes the key/value pair identified by the key parameter.
    // Returns true if the key/value pair was found and removed,
    // otherwise false.
    public boolean delete(K key) {
        if (!remove(key, root, null, false))
            return false;
        currentSize--;
        modCounter++;
        return true;
    }

    // Returns the value associated with the parameter key.  Returns
    // null if the key is not found or the dictionary is empty.
    public V getValue(K key) {
        return find(key, root);
    }

    // Returns the key associated with the parameter value.  Returns
    // null if the value is not found in the dictionary.  If more
    // than one key exists that matches the given value, returns the
    // first one found.
    public K getKey(V value) {
        // traverse the tree to find. use recursion. Using iterators is bad/inefficient.
        // Traverse the tree using the going left/ going right
        foundKey = null;
	    if (root != null) {
            preOrderKey(root, value);
            return foundKey;
        }
    return null;
    }

    // Returns the number of key/value pairs currently stored
    // in the dictionary
    public int size() {
        return currentSize;
    }

    // Returns true if the dictionary is at max capacity
    public boolean isFull() {
        return false;
    }

    // Returns true if the dictionary is empty
    public boolean isEmpty() {
        return root == null;
    }

    // Returns the Dictionary object to an empty state.
    public void clear() {
        root = null;
        currentSize = 0;
        modCounter = 0;
    }

    // Returns an Iterator of the keys in the dictionary, in ascending
    // sorted order.  The iterator must be fail-fast.
    public Iterator<K> keys() {
        KeyIteratorHelper<K> keyIter = new KeyIteratorHelper<>();
        return keyIter;
    }

    // Returns an Iterator of the values in the dictionary.  The
    // order of the values must match the order of the keys.
    // The iterator must be fail-fast.
    public Iterator<V> values() {
        ValueIteratorHelper valueIter = new ValueIteratorHelper();
        return valueIter;
    }

    private V find(K key, Node<K, V> node) {
        if (node == null)
            return null;
        int compare = (((Comparable<K>) key).compareTo(node.key));
        if (compare < 0)
            return find(key, node.leftChild);
        if (compare > 0)
            return find(key, node.rightChild);
        return (V) node.value;
    }

    private void preOrderKey(Node <K,V> node, V value) {
        if (node != null) {
            if (((Comparable<V>) value).compareTo(node.value) == 0)
                if (foundKey == null)
                    foundKey = node.key;
            preOrderKey(node.leftChild, value);
            preOrderKey(node.rightChild, value);
        }
    }

    private boolean insert(K key, V value, Node<K, V> nodePassedIn, Node<K, V> parent, boolean wasLeft) {
        if (nodePassedIn == null) {
                if (wasLeft)
                    parent.leftChild = new Node<K, V>(key, value);
                else
                    parent.rightChild = new Node<K, V>(key, value);
                currentSize++;
                modCounter++;
                return true;
            } else if (((Comparable<K>)key).compareTo(nodePassedIn.key) < 0)
                insert(key, value, nodePassedIn.leftChild, nodePassedIn, true);
            else if (((Comparable<K>)key).compareTo(nodePassedIn.key) > 0)
                insert(key, value, nodePassedIn.rightChild, nodePassedIn, false);
        return false;
    }

    private boolean remove(K key, Node<K, V> node, Node<K, V> parent, boolean wentLeft) {
        if (node == null)                                                               // Tree is empty
            return false;
        int compare = (((Comparable<K>)key).compareTo(node.key));
        if (compare < 0)                                                                // SEARCHING FOR THE NODE
            return remove(key, node.leftChild, node, true);                             // Go left
        else if (compare > 0)
            return remove(key, node.rightChild, node, false);                           // Go right
        else {                                                                          // Found it, now to delete
            if (node.leftChild == null && node.rightChild == null) {                    // Zero Children
                if (parent == null)
                    root = null;                                                        // Root is the only node
                else if (wentLeft)
                    parent.leftChild = null;
                else
                    parent.rightChild = null;
            } else if (node.leftChild == null) {                                        // One right child
                if (parent == null)                                                     // root node
                    root = node.rightChild;
                else if (wentLeft)
                    parent.leftChild = node.rightChild;
                else
                    parent.rightChild = node.rightChild;
            } else if (node.rightChild == null) {                                        // One left child
                if (parent == null)                                                     // root node
                    root = node.leftChild;
                else if (wentLeft)
                    parent.leftChild = node.leftChild;
                else
                    parent.rightChild = node.leftChild;
            } else {                                                                    // two children, where?
                if (parent == null) {
                    twoChildrenDelete(node);
                    root = node;
                } else
                    twoChildrenDelete(node);
            }
            return true;
        }
    }

    private Node<K, V> getSuccessor(Node<K, V> node) {    // CALL THIS WITH THE RIGHT CHILD with two children case
        Node<K, V> parent = null;
        while (node.leftChild != null) {
            parent = node;
            node = node.leftChild;
        }
        if (parent == null)
            return null;
        else {
            parent.leftChild = node.rightChild;
            return node;
        }
    }

    private Node<K, V> getPredecessor(Node<K, V> node) {    // CALL THIS WITH THE Left CHILD with two children case
        Node<K, V> parent = null;
        while (node.rightChild != null) {
            parent = node;
            node = node.rightChild;
        }
        if (parent == null)
            return null;
        else {
            parent.rightChild = node.leftChild;
            return node;
        }
    }
    
    private void twoChildrenDelete( Node<K,V> nodePassedIn ) {
        Node<K, V> tempS;
        Node<K, V> tempP;
        tempS = getSuccessor(nodePassedIn.rightChild);
        if (tempS == null) {
            tempP = getPredecessor(nodePassedIn.leftChild);
            if (tempP == null) {
                if (usedSuccessorLast) {
                    twoChildrenOverwrite(nodePassedIn.leftChild, nodePassedIn);
                    nodePassedIn.leftChild = nodePassedIn.leftChild.leftChild;
                    usedSuccessorLast = false;
                } else {
                    twoChildrenOverwrite(nodePassedIn.rightChild, nodePassedIn);
                    nodePassedIn.rightChild = nodePassedIn.rightChild.rightChild;
                    usedSuccessorLast = true;
                }
            } else {
                twoChildrenOverwrite(tempP, nodePassedIn);
                usedSuccessorLast = false;
            }
        } else {
            twoChildrenOverwrite(tempS, nodePassedIn);
            usedSuccessorLast = true;
        }
    }

    private void twoChildrenOverwrite(Node<K, V> successorNode, Node<K, V> nodeToOverwrite) {
        nodeToOverwrite.key = successorNode.key;
        nodeToOverwrite.value = successorNode.value;
    }

    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> leftChild;
        private Node<K, V> rightChild;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            leftChild = rightChild = null;
        }
    } // end of class Node

    abstract class IteratorHelper<E> implements Iterator<E> {
        protected Node<K,V> [] nodes;
        protected int index;
        protected int iterIndex;
        protected long stateCheck;

        public IteratorHelper() {
            nodes = new Node[currentSize];
            index = 0;
            iterIndex = 0;
            inOrderFill(root);
            stateCheck = modCounter;
        }

        public boolean hasNext() {
            if (stateCheck != modCounter)
                throw new ConcurrentModificationException();
            return index < currentSize;
        }

        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void inOrderFill(Node<K,V> node) {
            if (node != null) {
                inOrderFill(node.leftChild);
                nodes[iterIndex++] = node;
                inOrderFill(node.rightChild);
            }
        }
    }

    class KeyIteratorHelper<K> extends IteratorHelper<K> {
        public KeyIteratorHelper() {
            super();
        }

        public K next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (K) nodes[index++].key;
        }
    }

    class ValueIteratorHelper<V> extends IteratorHelper<V> {
        public ValueIteratorHelper() {
            super();
        }

        public V next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return (V) nodes[index++].value;
        }
    }

}
