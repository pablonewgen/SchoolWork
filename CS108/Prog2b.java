/**
 * Prog2b
 * CS 108-2
 * September 24, 2015
 * @author Paul T. Nguyen
 * Description: Recursively prints out Fibonacci sequence up to a given user inputed value.
 */
import java.util.Scanner;

public class Prog2b {
    // Recursive method that will calculate Fibonacci sequence
    public static int computeFib(int num) {
        if(num == 0) {
            return 0;
        }
        else if(num == 1) {
            return 1;
        }
        else {
            return computeFib(num - 1) + computeFib(num - 2);
        }
    }
    // Main method that takes user inputed value, runs for loop to calculate up to user inputed value.
    public static void main(String[] args) {
        int N = 0;
        Scanner scnr = new Scanner (System.in);
        N = scnr.nextInt();

        for (int i = 0; computeFib(i) <= N; i++) {
            System.out.print(computeFib(i) + " ");
        }
        scnr.close();
    }
}
