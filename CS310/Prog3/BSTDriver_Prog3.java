/**
 * Created by paultruongnguyen on 8/12/16.
 */


import data_structures.*;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class BSTDriver_Prog3 {
    private int sequenceNumber = 0;
    private DictionaryADT<TestKey,TestValue> binarySearchTree;

    public BSTDriver_Prog3() {
        initializeStuff();
        print("Is the table emtpy? : " + binarySearchTree.isEmpty());
        print("Is the table full? : " + binarySearchTree.isFull());
        print("The current size is : " + binarySearchTree.size());
        space();
        insertTesterAdd();
        space();
        insertTesterDuplicates();
        space();
        print("");
        print("Is the table emtpy? : " + binarySearchTree.isEmpty());
        print("Is the table full? : " + binarySearchTree.isFull());
        print("The current size is : " + binarySearchTree.size());
        space();
        getKeyTest();
        space();
        testDelete();
        space();
        getKeyTest();
        space();
        getValueTest();
        space();
        testContains();
        space();
        print("Now clearing...");
        binarySearchTree.clear();
        space();
        testContains();
        printArray();
        // End Tests
        print("\nDONE!");
    }

    public void initializeStuff() {
        binarySearchTree = new BinarySearchTree<TestKey, TestValue>();
    }


    // Print the entire array. You just need to fix the value iterator.
    public void printArray() {
        Iterator<TestKey> keyIter = binarySearchTree.keys();
        Iterator<TestValue> valueIter = binarySearchTree.values();
        try {
            while (keyIter.hasNext()) {
                print(keyIter.next() + "" + valueIter.next());
            }
        } catch (ConcurrentModificationException e) {
            print("Caught the ConcurrentModificationException.");
        }
    }

    private class TestKey implements Comparable<TestKey> {
        private String key;

        public TestKey(String s) {
            key = s;
        }

        public int compareTo(TestKey th) {
            return key.compareTo(th.key);
        }

        public String toString() {
            return "    Key: " + key;
        }

        public int hashCode() {
            return key.hashCode();
        }
    }

    private class TestValue implements Comparable<TestValue> {
        private Integer value;
        private int insertOrder;

        public TestValue(int n) {
            value = n;
            insertOrder = sequenceNumber++;
        }

        public int compareTo(TestValue v) {
            return value.compareTo(v.value);
        }

        public String toString() {
            return "    Value: " + value + ", Insert Order: " + insertOrder;
        }

        public int hashCode() {
            return ((Integer)value).hashCode();
        }
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void space() {
        print("");
        print("<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>");
        print("+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        print("");
    }


    private void testDelete() {
        print("--- BEGIN TESTING DELETE ---");
        boolean test;
        test = binarySearchTree.delete(new TestKey("h"));
        print("Attempting to remove key h. Was it possible? : " + test );
        test = binarySearchTree.delete(new TestKey("d"));
        print("Attempting to remove key d. Was it possible? : " + test );
        test = binarySearchTree.delete(new TestKey("b"));
        print("Attempting to remove key b. Was it possible? : " + test );
        test = binarySearchTree.delete(new TestKey("j"));
        print("Attempting to remove key j. Was it possible? : " + test );
        test = binarySearchTree.delete(new TestKey("j"));
        print("Attempting to remove key j. Was it possible? : " + test );
        test = binarySearchTree.delete(new TestKey("l"));
        print("Attempting to remove key l. Was it possible? : " + test );
        print("");
        printArray();
        print("--- FINISH TESTING DELETE ---");
    }

    public void insertTesterAdd() {
        print("--- BEGIN TESTING INSERT ---");
        binarySearchTree.add(new TestKey("e"), new TestValue(123));
        binarySearchTree.add(new TestKey("b"), new TestValue(123));
        binarySearchTree.add(new TestKey("h"), new TestValue(1234));
        binarySearchTree.add(new TestKey("d"), new TestValue(123));
        binarySearchTree.add(new TestKey("f"), new TestValue(123));
        binarySearchTree.add(new TestKey("a"), new TestValue(1234));
        binarySearchTree.add(new TestKey("k"), new TestValue(123));
        binarySearchTree.add(new TestKey("j"), new TestValue(534654));
        binarySearchTree.add(new TestKey("g"), new TestValue(1235));
        binarySearchTree.add(new TestKey("l"), new TestValue(123));
        binarySearchTree.add(new TestKey("c"), new TestValue(123));

        printArray();
        print("--- FINISHED TESTING INSERT ---");
    }

    public void getKeyTest() {
        print("What is the key for value: 123? " + binarySearchTree.getKey(new TestValue((123))));
        print("What is the key for value: 1234? " + binarySearchTree.getKey(new TestValue((1234))));
        print("What is the key for value: 534654? " + binarySearchTree.getKey(new TestValue((534654))));
    }
    
    public void getValueTest() {
        print("What is the value for key: a? " + binarySearchTree.getValue(new TestKey("a")));
        print("What is the value for key: e? " + binarySearchTree.getValue(new TestKey("e")));
        print("What is the value for key: g? " + binarySearchTree.getValue(new TestKey(("g"))));
        print("What is the value for key: j? " + binarySearchTree.getValue(new TestKey(("j"))));
    }

    public void testContains() {
        print("Does tree contain: a? " + binarySearchTree.contains(new TestKey("a")));
        print("Does tree contain: e? " + binarySearchTree.contains(new TestKey("e")));
        print("Does tree contain: g? " + binarySearchTree.contains(new TestKey("g")));
        print("Does tree contain: j? " + binarySearchTree.contains(new TestKey("j")));
        print("Does tree contain: d? " + binarySearchTree.contains(new TestKey("d")));
    }

    public void insertTesterDuplicates() {
        print("--- BEGIN TESTING INSERT DUPLICATES ---");
        print("Now testing insert duplicates...");
        print("Values should not show up as 9");
        binarySearchTree.add(new TestKey("e"), new TestValue(9));
        binarySearchTree.add(new TestKey("b"), new TestValue(9));
        binarySearchTree.add(new TestKey("h"), new TestValue(9));
        binarySearchTree.add(new TestKey("d"), new TestValue(9));
        binarySearchTree.add(new TestKey("f"), new TestValue(9));
        binarySearchTree.add(new TestKey("a"), new TestValue(9));
        binarySearchTree.add(new TestKey("k"), new TestValue(9));
        binarySearchTree.add(new TestKey("j"), new TestValue(9));
        binarySearchTree.add(new TestKey("g"), new TestValue(9));
        binarySearchTree.add(new TestKey("l"), new TestValue(9));
        binarySearchTree.add(new TestKey("c"), new TestValue(9));
        printArray();
        print("--- FINISHED TESTING INSERT ---");
    }


    public static void main(String[] agrs) {
        new BSTDriver_Prog3();
    }
}
