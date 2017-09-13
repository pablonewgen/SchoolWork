/**
 * Program 1C
 * @author Paul T. Nguyen
 * September 15, 2015
 * This is a program that will create a two-dimensional array that has the number of columns and rows specified
 * by the user. The two-dimensional array will be arrange such that the indexed values are of a specific output
 * Ex: 10 + row, 10 + r + 1....etc. Values will be printed normally and transposed. 
 *
 **/

import java.util.*;

public class Prog1c {
    /*
     * arrGen is a method taking in user inputed values for the two-dimensional array's
     * rows and columns. The method will then populate the array based on a set formula.
     * The method will also print the values indexed from the array in standard and
     * transposed formats.
     */
    public static int[][] arrGen (int userRow, int userCol) {
        int [][] array = new int [userRow][userCol];
        int i = 0;
        int j = 0;

        for (i = 0; i < userRow; i++) {			// For row population
            for (j = 0; j < userCol; j++) {		// For column population
                if (j < userCol) {
                    array[i][j] = array[i][j] + (10 + userRow) + (userRow*i) + j;		// Store values into array
                    if (j == userCol - 1){
                        System.out.println(array[i][j] + "  ");		// Condition to print value + new line at end of column
                    }
                    else {
                        System.out.print(array[i][j] + "  ");		// Condition to print just the values
                    }
                }
            }
        }

        System.out.println();							               // Separates standard and transposed outputs

        for (j = 0; j < userCol; j++) {
            for (i = 0; i < userRow; i++) {
                if (i < userRow) {									      // No values are inputed into array as array is merely transposed
                    if (i == userRow - 1){
                        System.out.println(array[i][j] + "  ");		// Condition to print value + new line at end of column
                    }
                    else {
                        System.out.print(array[i][j] + "  ");			// Condition to print just the values
                    }
                }
            }
        }
        return array;
    }
    /*
     * Main method to which will receive user inputed values for rows and columns.
     * User inputed values for rows and columns are fed into the arrGen method,
     * to which generate values by a predetermined formula, that are fed into
     * the user defined rows and columns. Those values are then transposed with
     * the same arrGen method.
     */
    public static void main(String[] args) {
        // Prog1c, Paul T. Nguyen
        int row = 0;
        int col = 0;

        Scanner scnr = new Scanner(System.in); 		// Reads inputed value into variable

        System.out.print("");
        row = scnr.nextInt();								// Reads first value into row dimension
        System.out.print("");
        col = scnr.nextInt();								// Reads second value into column dimension

        arrGen(row, col);									// Launch arrGen method
        scnr.close();										   // Close scanner object scnr
    }

}