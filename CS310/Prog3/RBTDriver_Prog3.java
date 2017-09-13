/**
 * Created by paultruongnguyen on 8/12/16.
 */


import data_structures.*;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class RBTDriver_Prog3 {
    private int sequenceNumber = 0;
    private DictionaryADT<TestKey,TestValue> redBlackTree;

    public RBTDriver_Prog3() {
        initializeStuff();
        print("Is the table emtpy? : " + redBlackTree.isEmpty());
        print("Is the table full? : " + redBlackTree.isFull());
        print("The current size is : " + redBlackTree.size());
        space();
        insertTesterAdd();
        print("");
        print("Is the table emtpy? : " + redBlackTree.isEmpty());
        print("Is the table full? : " + redBlackTree.isFull());
        print("The current size is : " + redBlackTree.size());
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
        redBlackTree.clear();
        space();
        testContains();
        // End Tests
        print("\nDONE!");
    }

    public void initializeStuff() {
        redBlackTree = new BinarySearchTree<TestKey, TestValue>();
    }


    // Print the entire array. You just need to fix the value iterator.
    public void printArray() {
        Iterator<TestKey> keyIter = redBlackTree.keys();
        Iterator<TestValue> valueIter = redBlackTree.values();
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
        test = redBlackTree.delete(new TestKey("h"));
        print("Attempting to remove key h. Was it possible? : " + test );
        test = redBlackTree.delete(new TestKey("d"));
        print("Attempting to remove key d. Was it possible? : " + test );
        test = redBlackTree.delete(new TestKey("b"));
        print("Attempting to remove key b. Was it possible? : " + test );
        test = redBlackTree.delete(new TestKey("j"));
        print("Attempting to remove key j. Was it possible? : " + test );
        test = redBlackTree.delete(new TestKey("j"));
        print("Attempting to remove key j. Was it possible? : " + test );
        test = redBlackTree.delete(new TestKey("l"));
        print("Attempting to remove key l. Was it possible? : " + test );
        print("");
        printArray();
        print("--- FINISH TESTING DELETE ---");
    }

    public void insertTesterAdd() {
        print("--- BEGIN TESTING INSERT ---");
        redBlackTree.add(new TestKey("e"), new TestValue(123));
        redBlackTree.add(new TestKey("b"), new TestValue(123));
        redBlackTree.add(new TestKey("h"), new TestValue(1234));
        redBlackTree.add(new TestKey("d"), new TestValue(123));
        redBlackTree.add(new TestKey("f"), new TestValue(123));
        redBlackTree.add(new TestKey("a"), new TestValue(1234));
        redBlackTree.add(new TestKey("k"), new TestValue(123));
        redBlackTree.add(new TestKey("j"), new TestValue(534654));
        redBlackTree.add(new TestKey("g"), new TestValue(1235));
        redBlackTree.add(new TestKey("l"), new TestValue(123));
        redBlackTree.add(new TestKey("c"), new TestValue(123));

        printArray();
        print("--- FINISHED TESTING INSERT ---");
    }

    public void getKeyTest() {
        print("What is the key for value: 123? " + redBlackTree.getKey(new TestValue((123))));
        print("What is the key for value: 1234? " + redBlackTree.getKey(new TestValue((1234))));
        print("What is the key for value: 534654? " + redBlackTree.getKey(new TestValue((534654))));
    }

    public void getValueTest() {
        print("What is the value for key: a? " + redBlackTree.getValue(new TestKey("a")));
        print("What is the value for key: e? " + redBlackTree.getValue(new TestKey("e")));
        print("What is the value for key: g? " + redBlackTree.getValue(new TestKey(("g"))));
        print("What is the value for key: j? " + redBlackTree.getValue(new TestKey(("j"))));
    }

    public void testContains() {
        print("Does tree contain: a? " + redBlackTree.contains(new TestKey("a")));
        print("Does tree contain: e? " + redBlackTree.contains(new TestKey("e")));
        print("Does tree contain: g? " + redBlackTree.contains(new TestKey("g")));
        print("Does tree contain: j? " + redBlackTree.contains(new TestKey("j")));
        print("Does tree contain: d? " + redBlackTree.contains(new TestKey("d")));
    }


    public static void main(String[] agrs) {
        new BSTDriver_Prog3();
    }
}