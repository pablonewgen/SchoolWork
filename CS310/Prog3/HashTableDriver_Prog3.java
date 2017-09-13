/*  Prog3 HashTable Driver

*/

import data_structures.*;
import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import java.util.Random;

public class HashTableDriver_Prog3 {
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int MAX_SIZE = 26;
    private int sequenceNumber = 0;
    private Random rndInt;
    private DictionaryADT<TestKey,TestValue> hashTable;

    public HashTableDriver_Prog3() {
        initializeStuff();
        space();
        print("Is the table emtpy? : " + hashTable.isEmpty());
        print("Is the table full? : " + hashTable.isFull());
        print("The current size is : " + hashTable.size());
        testInsert();
        space();
        print("Is the table emtpy? : " + hashTable.isEmpty());
        print("Is the table full? : " + hashTable.isFull());
        print("The current size is : " + hashTable.size());
        space();
	    testGetValue();
	    space();
        testContains();
        space();
        testDelete();
        space();
        testContains(); // should show all false now
        space();
        printArray();
        space();
	    testGetValue();
        space();
        hashTable.clear();
        testInsertOverfill();
        space();
        // End Tests
        print("\nDONE!");
    }

    public void initializeStuff() {
        hashTable = new HashTable<TestKey,TestValue>(MAX_SIZE-1);
        rndInt = new Random(10);
    }

    public void testInsert() {
        space();
        print("--- BEGIN TESTING INSERT ---");
        print("There are 40 total inserts, but some of them are duplicates.");
        print("The duplicates should NOT show up.");
        for (int i = 0; i < 15; i++) {
            int rndTemp = rndInt.nextInt(MAX_SIZE);
            String key = Character.toString(ALPHABET.charAt(rndTemp));
            hashTable.add(new TestKey(key), new TestValue(rndInt.nextInt(10)));
        }
        printArray();
        print("--- FINISHED TESTING INSERT ---");
    }

    // Print the entire array. You just need to fix the value iterator.
    public void printArray() {
        Iterator<TestKey> keyIter = hashTable.keys();
        Iterator<TestValue> valueIter = hashTable.values();
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
    private void testContains() {
        print("--- BEGIN TESTING CONTAINS ---");
        boolean test = hashTable.contains(new TestKey("C"));
        print("Attempting to check if contains key C. Was it possible? : " + test );
        test = hashTable.contains(new TestKey("L"));
        print("Attempting to check if contains key L. Was it possible? : " + test );
        test = hashTable.contains(new TestKey("R"));
        print("Attempting to check if contains key R. Was it possible? : " + test );
        test = hashTable.contains(new TestKey("V"));
        print("Attempting to check if contains key V. Was it possible? : " + test );
        test = hashTable.contains(new TestKey("Z"));
        print("Attempting to check if contains key Z. Was it possible? : " + test );
        test = hashTable.contains(new TestKey("A"));
        print("Attempting to check if contains key A. Was it possible? (FALSE): " + test );
        test = hashTable.contains(new TestKey("B"));
        print("Attempting to check if contains key B. Was it possible? (FALSE): " + test );
        test = hashTable.contains(new TestKey("M"));
        print("Attempting to check if contains key M. Was it possible? (FALSE): " + test );
        test = hashTable.contains(new TestKey("X"));
        print("Attempting to check if contains key X. Was it possible? (FALSE): " + test );
        test = hashTable.contains(new TestKey("Y"));
        print("Attempting to check if contains key Y. Was it possible? (FALSE): " + test );
        print("--- FINISH TESTING CONTAINS ---");
    }

    private void testDelete() {
        print("--- BEGIN TESTING DELETE ---");
        boolean test = hashTable.delete(new TestKey("C"));
        print("Attempting to remove key C. Was it possible? : " + test );
        test = hashTable.delete(new TestKey("L"));
        print("Attempting to remove key L. Was it possible? : " + test );
        test = hashTable.delete(new TestKey("R"));
        print("Attempting to remove key R. Was it possible? : " + test );
        test = hashTable.delete(new TestKey("V"));
        print("Attempting to remove key V. Was it possible? : " + test );
        test = hashTable.delete(new TestKey("Z"));
        print("Attempting to remove key Z. Was it possible? : " + test );
        test = hashTable.delete(new TestKey("A"));
        print("Attempting to remove key A. Was it possible? (FALSE): " + test );
        test = hashTable.delete(new TestKey("B"));
        print("Attempting to remove key B. Was it possible? (FALSE): " + test );
        test = hashTable.delete(new TestKey("M"));
        print("Attempting to remove key M. Was it possible? (FALSE): " + test );
        test = hashTable.delete(new TestKey("X"));
        print("Attempting to remove key X. Was it possible? (FALSE): " + test );
        test = hashTable.delete(new TestKey("Y"));
        print("Attempting to remove key Y. Was it possible? (FALSE): " + test );
        print("--- FINISH TESTING DELETE ---");
    }

    private void testGetValue() {
        print("--- BEGIN TESTING GETVALUE ---");
        print("Checking to see if key C is inside: " + hashTable.getValue(new TestKey("C")));
        print("Checking to see if key D is inside: " + hashTable.getValue(new TestKey("D")));
        print("Checking to see if key E is inside: " + hashTable.getValue(new TestKey("E")));
        print("Checking to see if key F is inside: " + hashTable.getValue(new TestKey("F")));
        print("Checking to see if key L is inside: " + hashTable.getValue(new TestKey("L")));
        print("Checking to see if key P is inside: " + hashTable.getValue(new TestKey("P")));
        print("Checking to see if key R is inside: " + hashTable.getValue(new TestKey("R")));
        print("Checking to see if key S is inside: " + hashTable.getValue(new TestKey("S")));
        print("Checking to see if key V is inside: " + hashTable.getValue(new TestKey("V")));
        print("Checking to see if key Z is inside: " + hashTable.getValue(new TestKey("Z")));
        print("--- FINISH TESTING GETVALUE ---");
        
    }
    
    private void testGetKey() {
        print("--- BEGIN TESTING GETKEY ---");
        print("Checking to see if key 3 is inside: " + hashTable.getKey(new TestValue(3)));
        print("Checking to see if key 9 is inside: " + hashTable.getKey(new TestValue(9)));
        print("Checking to see if key 6 is inside: " + hashTable.getKey(new TestValue(6)));
        print("Checking to see if key 8 is inside: " + hashTable.getKey(new TestValue(8)));
        print("Checking to see if key 0 is inside: " + hashTable.getKey(new TestValue(0)));
        print("Checking to see if key 0 is inside: " + hashTable.getKey(new TestValue(0)));
        print("Checking to see if key 9 is inside: " + hashTable.getKey(new TestValue(9)));
        print("Checking to see if key 0 is inside: " + hashTable.getKey(new TestValue(0)));
        print("Checking to see if key 4 is inside: " + hashTable.getKey(new TestValue(4)));
        print("Checking to see if key 8 is inside: " + hashTable.getKey(new TestValue(8)));
        print("--- FINISH TESTING GETKEY ---");
    }

    private void testInsertOverfill() {
        print("--- BEGIN TESTING MAXINSERT ---");
        print("There are 40 total inserts.");
        print("End size should only be 25.");
        for (int i = 0; i < 40; i++) {
            String key = Integer.toString(i);
            hashTable.add(new TestKey(key), new TestValue(rndInt.nextInt(10)));
        }
        printArray();
        print("--- END TESTING MAXINSERT ---");
    }


    public static void main(String[] agrs) {
        new HashTableDriver_Prog3();
    }
}
