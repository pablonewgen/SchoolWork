/* Timer.java
   Here is a simple timing test framework.  This timer uses
   your array-based list from project #1.
   Alan Riggins, CS310, Summer 2016

   Here are the results/output on my computer:

Structure size: 100 Time: 3
Structure size: 200 Time: 1
Structure size: 400 Time: 2
Structure size: 800 Time: 1
Structure size: 1600 Time: 2
Structure size: 3200 Time: 3
Structure size: 6400 Time: 9
Structure size: 12800 Time: 34
Structure size: 25600 Time: 127
Structure size: 51200 Time: 346
Structure size: 102400 Time: 772
Structure size: 204800 Time: 1621
Structure size: 409600 Time: 3334
Structure size: 819200 Time: 6784
Structure size: 1638400 Time: 13612

*/


public class LinkTimer {
    private static final int SIZE = 10000000;
    private static final int WORK_CONSTANT = 10000;
    private static final int STEP_SIZE = 2000;
    private static int STRUCTURE_SIZE_N = 2000;

    private int [] arr;
    private OrderedListPriorityQueue<Integer> linkedList;

    private long start, stop;

    public LinkTimer() {
        linkedList = new OrderedListPriorityQueue<>();
        arr = new int[SIZE];
        //normal = new int[SIZE];
        System.out.println("Testing...");
        System.out.println();
        runInsertLinkTimer();
        System.out.println();
        runRemoveLinkTimer();
    }

    private void initializeList() {
        int newIndex = 0;
        // fill array with sequential integers
        for(int i=0; i < SIZE; i++)
            arr[i] = i;

        //randomize
        for(int i=0; i < SIZE; i++) {
            newIndex = (int)(STRUCTURE_SIZE_N*Math.random());
            int tmp = arr[i];
            arr[i] = arr[newIndex];
            arr[newIndex] = i;

        }
    }


    private void runRemoveLinkTimer() {
        STRUCTURE_SIZE_N = 2000;
        System.out.println("Timing OrderListPriorityQueue Remove....");
        for(int outer=0; outer < 20; outer++) {
            // Build initial structure
            for(int i=0; i < STRUCTURE_SIZE_N; i++) {
                linkedList.insert(arr[i]);
            }
            start = System.currentTimeMillis();
            for(int i=0; i < WORK_CONSTANT; i++) {
                linkedList.insertFirst(i); // O(1);
                linkedList.remove(); // O(n);
            }
            stop = System.currentTimeMillis();
            long numberOfMilliseconds = stop-start;
            System.out.println("Structure size: " + STRUCTURE_SIZE_N +
                    " Time: " + numberOfMilliseconds);
            STRUCTURE_SIZE_N += STEP_SIZE;   // INCREMENT N BY STEP SIZE
            //STRUCTURE_SIZE_N <<= 1; // DOUBLE N
            linkedList.clear();
        } // end inner loop
    }  // end method

    private void runInsertLinkTimer() {
        STRUCTURE_SIZE_N = 2000;
        System.out.println("Timing OrderedListPriorityQueue Insert...");
        for(int outer=0; outer < 20; outer++) {
            // Build initial structure
            for(int i=0; i < STRUCTURE_SIZE_N; i++) {
                linkedList.insert(arr[i]);
            }
            start = System.currentTimeMillis();
            for(int i=0; i < WORK_CONSTANT; i++) {
                linkedList.insert(arr[i]); // O(n);
                linkedList.remove(); // O(1);
            }
            stop = System.currentTimeMillis();
            long numberOfMilliseconds = stop-start;
            System.out.println("Structure size: " + STRUCTURE_SIZE_N +
                    " Time: " + numberOfMilliseconds);
            STRUCTURE_SIZE_N += STEP_SIZE;   // INCREMENT N BY STEP SIZE
            //STRUCTURE_SIZE_N <<= 1; // DOUBLE N
            linkedList.clear();
        } // end inner loop
    }  // end method

/*
* four methods to write about
* insert into ordered array is big o of n because... it takes log n steps to find location of insertion and n/2 steps on average to shift over, therefore log n plus n/2 is big o of n
*
* what are the steps for an ordered linked list implementation
* LL - traverse the list until you find the insertion point, on average n/2. Insertion is hooking up pointers O(1)
*
*
*
*
* */

    private class Job implements Comparable<Job> {
        private int priority;
        private String label;

        public Job(int p, String s) {
            priority = p;
            label = s;
        }

        public int compareTo(Job j) {
            return priority-j.priority;
        }

        public String toString() {
            return label + " priority=" + priority;
        }
    }

    public static void main(String [] args) {
        new LinkTimer();
    }
}
