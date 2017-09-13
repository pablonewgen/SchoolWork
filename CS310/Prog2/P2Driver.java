/*  P2Driver.java
    A simple driver program for project #2.  Enable the implementation you 
    want to test.  
    CS310 Summer 2016
    Alan Riggins
    
//////////////////////////////////////////////////////////////////////    
   ** Correct output is: **
    
AAAA
CCCC
PPPP
ZZZZ
F priority=1
H priority=1
C priority=2
A priority=5
E priority=5
G priority=5
B priority=7
D priority=10
//////////////////////////////////////////////////////////////////////
*/  


public class P2Driver {

    public P2Driver() {
        doStringTest();
        doJobTest();
        }
        
    private void doStringTest() {
        PriorityQueue<String> pq = 
                    //new OrderedArrayPriorityQueue<String>();
                    new OrderedListPriorityQueue<String>();
                                       
        pq.insert("ZZZZ");
        pq.insert("PPPP");
        pq.insert("AAAA");
        pq.insert("CCCC");
        
        while(!pq.isEmpty())
            System.out.println(pq.remove());
        }  
        
    private void doJobTest() {
        PriorityQueue<Job> pq = 
                    //new OrderedArrayPriorityQueue<Job>();
                    new OrderedListPriorityQueue<Job>();
        pq.insert(new Job(5,"A"));      
        pq.insert(new Job(7,"B"));
        pq.insert(new Job(2,"C"));
        pq.insert(new Job(10,"D"));
        pq.insert(new Job(5,"E"));
        pq.insert(new Job(1,"F"));
        pq.insert(new Job(5,"G"));
        pq.insert(new Job(1,"H"));
        
// Enable this to test the iterator
        System.out.println();
        System.out.println("Now printing contents of the PQ with the Iterator");
        for(Job j : pq)
            System.out.println(j);

        System.out.println("Is this empty? (Should be false)");
        System.out.println(pq.isEmpty());

        System.out.println("Is this full? (Should be false)");
        System.out.println(pq.isFull());

        System.out.println(pq.size());
        System.out.println("Now peeking (Should be 'F priority=1')");
        System.out.println(pq.peek());

        Job in = new Job(1,"F");
        Job out = new Job(3,"L");
        System.out.println("Now checking contains (Should be true)");
        System.out.println(pq.contains(in));

        System.out.println("Now checking not contains (Should be false)");
        System.out.println(pq.contains(out));

        System.out.println("Now dequeuing, they must come out in proper order");        
        while(!pq.isEmpty()) {
            System.out.println(pq.size());
            System.out.println(pq.remove());
        }

        System.out.println("Is this empty? (Should be true)");
        System.out.println(pq.isEmpty());
        System.out.println(pq.size());

        System.out.println();
        PriorityQueue<Job> aq =
                //new OrderedArrayPriorityQueue<Job>();
                new OrderedListPriorityQueue<Job>();
        aq.insert(new Job(5,"A"));
        aq.insert(new Job(7,"B"));
        aq.insert(new Job(2,"C"));
        aq.insert(new Job(10,"D"));
        aq.insert(new Job(5,"E"));
        aq.insert(new Job(1,"F"));
        aq.insert(new Job(5,"G"));
        aq.insert(new Job(1,"H"));

        System.out.println("Now printing contents of the AQ with the Iterator");
        for(Job p : aq)
            System.out.println(p);

        System.out.println(aq.size());
        System.out.println("Is this empty? (Should be false)");
        System.out.println(aq.isEmpty());

        System.out.println("***...Now clearing priority queue...***");
        aq.clear();

        System.out.println("Is this empty? (Should be true)");
        System.out.println(aq.isEmpty());
        System.out.println(aq.size());

        System.out.println();
        System.out.println("Creating array based priority queue...");
        PriorityQueue<Job> bbq =
            new OrderedArrayPriorityQueue<Job>(23);

            System.out.println("Is this empty? (Should be true)");
            System.out.println(bbq.isEmpty());

            for( int i = 1; i < 24; i++) {
                bbq.insert(new Job(i,"A"));
            }

            System.out.println("Is this empty? (Should be false)");
            System.out.println(bbq.isEmpty());

            System.out.println("Is this full? (Should be true)");
            System.out.println(bbq.isFull());

            System.out.println("Now printing contents of the BBQ with the Iterator");
            for(Job b : bbq)
            System.out.println(b);
            System.out.println("Now peeking (Should be 'A priority=1')");
            System.out.println(bbq.peek());

            System.out.println(bbq.size());
            System.out.println("peek " + bbq.peek());
            System.out.println(bbq.remove());
            System.out.println(bbq.size());
            System.out.println("peek " + bbq.peek());
            System.out.println(bbq.remove());
            System.out.println(bbq.size());
            System.out.println("peek " + bbq.peek());

        System.out.println("***...Now clearing priority queue...***");
            bbq.clear();

            System.out.println("Is this empty? (Should be true)");
            System.out.println(bbq.isEmpty());
            System.out.println(bbq.size());

        }


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
        new P2Driver();
        }
    }
