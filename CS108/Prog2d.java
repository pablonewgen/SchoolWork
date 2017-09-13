/**
 * Prog2d
 * CS 108-2
 * September 24, 2015
 * @author Paul T. Nguyen
 * Description: Recursive method that takes a user inputed value, counts descending with one method
 * and then counts up in ascending order up to user inputed value with another method.
 */

import java.util.Scanner;

public class Prog2d {
    // Method that recursively counts user inputed value in descending order and prints values.
    public static void backPrint(int input){
        if (input == 1) {
            System.out.print(input + " ");
            return;
        }
        else {
            System.out.print(input + " ");
            backPrint(input - 1);
            return;
        }
    }
    // Method that recursively counts user inputed value in ascending order and prints values.
    public static void forwardPrint(int input){
        if (input == 1) {
            System.out.print(input + " ");
            return;
        }
        else {
            forwardPrint(input - 1);
            System.out.print(input + " ");
            return;
        }
    }
    // Main method that takes user inputed value and calls recursive methods backPrint and forwardPrint
    public static void main(String[] args) {
        int userInput = 0;
        Scanner scnr = new Scanner (System.in);
        userInput = scnr.nextInt();

        backPrint(userInput);
        System.out.println();
        forwardPrint(userInput);
        scnr.close();
    }

}
