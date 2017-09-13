/**
 * Paul Truong Nguyen
 * masc2188
 */

package data_structures;
// INSERT INTO package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class HashTable<K, V> implements DictionaryADT<K, V> {
    private int currentSize, maxSize, tableSize;
    private long modCounter;
    private UnorderedList<DictionaryNode<K, V>>[] list;

    public HashTable(int n) {
        currentSize = 0;
        maxSize = n;
        modCounter = 0;
        tableSize = (int) (maxSize * 1.3f);
        list = new UnorderedList[tableSize];
        for (int i = 0; i < tableSize; i++)
            list[i] = new UnorderedList<DictionaryNode<K, V>>();
    }

    // Returns true if the dictionary has an object identified by
    // key in it, otherwise false.
    public boolean contains(K key) {
        DictionaryNode<K, V> temp = new DictionaryNode<K, V>(key, null);
        return list[getIndex(key)].find(temp) != null;
    }

    // Adds the given key/value pair to the dictionary.  Returns
    // false if the dictionary is full, or if the key is a duplicate.
    // Returns true if addition succeeded.
    public boolean add(K key, V value) {
        if (isFull() || contains(key))
            return false;
        DictionaryNode<K, V> newNode = new DictionaryNode<K, V>(key, value);
        list[getIndex(key)].insert(newNode);
        currentSize++;
        modCounter++;
        return true;
    }

    // Deletes the key/value pair identified by the key parameter.
    // Returns true if the key/value pair was found and removed,
    // otherwise false.
    public boolean delete(K key) {
        if (isEmpty())
            return false;
        if (contains(key)) {
            V value = getValue(key);
            DictionaryNode<K, V> temp = new DictionaryNode<K,V>(key, value);
            list[getIndex(key)].remove(temp);
            currentSize--;
            modCounter++;
            return true;
        }
        return false;
    }

    // Returns the value associated with the parameter key.  Returns
    // null if the key is not found or the dictionary is empty.
    public V getValue(K key) {
        DictionaryNode<K, V> temp = new DictionaryNode<K, V>(key, null);
        DictionaryNode<K, V> returnValue = list[getIndex(key)].find(temp);
        if (returnValue == null)
            return null;
        return returnValue.value;
    }

    // Returns the key associated with the parameter value.  Returns
    // null if the value is not found in the dictionary.  If more
    // than one key exists that matches the given value, returns the
    // first one found.
    public K getKey(V value) {
        int j = 0;
        if (!isEmpty()) {
            for (int i = 0; i < tableSize; i++)
                for (DictionaryNode n : list[i])
                    if (((Comparable<V>) value).compareTo((V) n.value) == 0)
                        return (K) n.key;
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
        return currentSize == maxSize;
    }

    // Returns true if the dictionary is empty
    public boolean isEmpty() {
        return currentSize == 0;
    }

    // Returns the Dictionary object to an empty state.
    public void clear() {
        currentSize = 0;
        modCounter = 0;
        for (int i = 0; i < tableSize; i++)
            list[i].clear();
    }

    // Returns an Iterator of the keys in the dictionary, in ascending
    // sorted order.  The iterator must be fail-fast.
    public Iterator<K> keys() {
        KeyIteratorHelper keyIteratorHelper = new KeyIteratorHelper();
        return keyIteratorHelper;
    }

    // Returns an Iterator of the values in the dictionary.  The
    // order of the values must match the order of the keys.
    // The iterator must be fail-fast.
    public Iterator<V> values() {
        ValueIteratorHelper valueIteratorHelper = new ValueIteratorHelper();
        return valueIteratorHelper;
    }

    private int getIndex(K key) {
        return ((key.hashCode() & 0x7FFFFFFF) % tableSize);
    }

    class DictionaryNode<K,V> implements Comparable<DictionaryNode<K,V>> {
        K key;
        V value;

        public DictionaryNode(K k, V v) {
            key = k;
            value = v;
        }

        public int hashCode() {
            return key.hashCode();
        }

        public int compareTo(DictionaryNode<K, V> node) {
            return ((Comparable<K>) key).compareTo((K) node.key);
        }
    }

    abstract class IteratorHelper<E> implements Iterator<E> {
        protected DictionaryNode<K, V>[] nodes;
        protected int index;
        protected long stateCheck;

        protected IteratorHelper() {
            nodes = new DictionaryNode[currentSize];
            index = 0;
            int j = 0;
            stateCheck = modCounter;
            for (int i = 0; i < tableSize; i++) {
                for (DictionaryNode n : list[i]) {
                    nodes[j++] = n;
                }
            }
            nodes = (DictionaryNode<K, V>[]) shellSort(nodes);
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

        private DictionaryNode<K, V>[] shellSort(DictionaryNode<K, V>[] array) {
            DictionaryNode<K, V>[] on = array;
            int in, out, h = 1;
            DictionaryNode<K, V> temp;
            int size = on.length;
            while (h <= size / 3)  // Calculate gaps
                h = h * 3 + 1;
            while (h > 0) {
                for (out = h; out < size; out++) {
                    temp = on[out];
                    in = out;
                    while (in > h - 1 && ((Comparable<K>) on[in - h].key).compareTo(temp.key) >= 0) {
                        on[in] = on[in - h];
                        in -= h;
                    }
                    on[in] = temp;
                }
                h = (h - 1) / 3;
            }
            return array;
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
