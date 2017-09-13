/**
 * Prog2a
 * CS 108-2
 * September 24, 2015
 * @author Paul T. Nguyen
 * Description: Recursively print digits in a number backwards.
 */

import java.util.Scanner;

public class Prog2a {
    // Method that recursively prints out user inputed value backwards
    public static void digitBackwards( long userIn) {
        if (userIn < 10) {
            System.out.print(userIn);
            return;
        }
        else {
            System.out.print(userIn % 10);
            digitBackwards(userIn/10);
            return;
        }
    }
    // Main method taking user inputed value and running recursive method digitBackwards.
    public static void main(String[] args) {
        long input = 0;

        Scanner scnr = new Scanner (System.in);
        input = scnr.nextLong();
        digitBackwards(input);
        scnr.close();
    }
}
