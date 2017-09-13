/**
 * Prog2c
 * CS 108-2
 * September 24, 2015
 * @author Paul T. Nguyen
 * Description: Recursively calls method gCD to find the greatest common denominator between two user inputed values
 */
import java.util.Scanner;

public class Prog2c {
    // Recursive method to find the greatest common denominator
    public static int gCD(int input1, int input2){
        if (input2 == 0){
            return input1;
        }
        else {
            return gCD(input2, input1%input2);
        }
    }
    // Main method that takes user inputed values and then runs recursive gCD method.
    public static void main(String[] args) {
        int user1 = 0;
        int user2 = 0;
        Scanner scnr = new Scanner (System.in);

        user1 = scnr.nextInt();
        user2 = scnr.nextInt();
        System.out.println(gCD(user1, user2));

        scnr.close();
    }

}
