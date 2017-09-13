/*  Alan Riggins
    CS310 Summer 2016

    This is a simple tester program designed as an example to help you test
    your program.  Perfect results with this tester does not mean that your
    program is error free; many methods are not tested here.
*/

import data_structures.*;

public class Tester {
    private OrderedListADT<Thing> v;
    private int sequenceNumber;

    public Tester() {
        sequenceNumber = 0;
        v = new OrderedArrayList<Thing>(10000);
        try {
            v.insert(new Thing("B"));
            v.insert(new Thing("B"));
            v.insert(new Thing("C"));
            v.insert(new Thing("C"));
            v.insert(new Thing("A"));
            v.insert(new Thing("A"));


            print("Now printing contents:");
            for(Thing t : v)
                print(t.toString());

            print("");
            print("Now checking search/remove for correct order");
            print("Should print 'A 4':  " + v.get(new Thing("A")));
            print("Should print 'B 0':  " + v.get(new Thing("B")));
            print("Should print 'C 2':  " + v.get(new Thing("C")));

            print("Should print '0':  " + v.find(new Thing("A")));
            print("Should print '2':  " + v.find(new Thing("B")));
            print("Should print '4':  " + v.find(new Thing("C")));

            print("Should print 'A 4':  " + v.remove(new Thing("A")));
            print("Should print 'B 0':  " + v.remove(new Thing("B")));
            print("Should print 'C 2':  " + v.remove(new Thing("C")));
            print("");
            print("Now printing contents after removal of first A B C:");
            print("'A 4', 'B 0', and 'C 8' should NOT be in the output.");
            for(Thing t : v)
                print(t.toString());
            v.remove(0);
            print("Now printing contents after removal of A:");
            for(Thing t : v)
                print(t.toString());

            print("Now searching for an element NOT in the list");
            if(v.get(new Thing ("Z")) != null) print("ERROR, wrong return value");
            if(v.find(new Thing ("Z")) != -1) print("ERROR, wrong return value");
            if(v.remove(new Thing ("Z")) != null) print("ERROR, wrong return value");

            v.clear();

            for(int i=0; i < 10000; i++)
                v.insert(new Thing(""+i));
            if(v.size() != 10000)
                print("ERROR in size, should be 10000 but is " + v.size());
            if(v.get(new Thing(""+2000)) == null    )
                print("ERROR, cannot find valid element");
            if(v.get(new Thing(""+20)) == null    )
                print("ERROR, cannot find valid element");

            if(v.get(new Thing("Hello World")) != null)
                print("ERROR, wrong return value");

            if(v.remove(new Thing("Hello World")) != null)
                print("ERROR, wrong return value");

        }
        catch(Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void print(String val) {
        System.out.println(val);
    }

    public static void main(String [] args) {
        new Tester();
    }

    //////////////////////////////////////////////////////////////
    class Thing implements Comparable<Thing> {
        private String label;
        private int insertOrder;

        public Thing(String l) {
            label = l;
            insertOrder = sequenceNumber++;
        }

        public int compareTo(Thing t) {
            return label.compareTo(t.label);
        }

        public String toString() {
            return label + " " + insertOrder;
        }
    }
//////////////////////////////////////////////////////////////
}