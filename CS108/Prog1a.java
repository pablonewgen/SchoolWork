
/**
 * Prog1A
 * @author Paul T. Nguyen
 * September 15, 2015
 * This is a program that will create and display a one-dimensional array of double values, numbering from 1.0 through 10.0.
 * The program will then shuffle the array randomly and display the result
 *
 **/

import java.util.Random;

public class Prog1a {

    /*
     * shufArr is a method that will input random index values into an array at two
     * separate instances, store the array values at those random indexes, and then
     * swap those array values between one another. The swap is limited by the
     * number of times defined in main.
     */
    public static void shufArr (double arrRandom[], int shuffle){
        Random ranGen = new Random(); 								// Creates new random number generator
        ranGen.setSeed(123L);										   // Provided seed value for pseudo-random values
        int k = 0;
        double tmpStore1 = 0.0;  									   // Temporary  variable for swapping
        double tmpStore2 = 0.0;
        int randomIndex1 = 0;
        int randomIndex2 = 0;
        for (k = 0;  k < shuffle; k++) {  						// Run loop shuffle and swap through array
            randomIndex1 = ranGen.nextInt(arrRandom.length);	// Random index value #1
            randomIndex2 = ranGen.nextInt(arrRandom.length);	// Random index value #2
            tmpStore1 = arrRandom[randomIndex1]; 				// Store array value at random index value #1
            tmpStore2 = arrRandom[randomIndex2];				// Store array value at random index value #2
            arrRandom[randomIndex1] = tmpStore2; 				// Swap array value at random index value #1 to be array value at random index value #2
            arrRandom[randomIndex2] = tmpStore1;				// Swap array value at random index value #2 to be array value at random index value #1
        }
    }

    /*
     * arrPrint formats the array values to print in a specified manner. A loop
     * is used to print through all the values in the array
     */
    public static void arrPrint (double [] array ) {
        int i = 0;
        for (i = 0; i < array.length; i++ ){
            if (i < array.length - 1) {
                System.out.print(array[i] + ",");					// Condition to print most values, separated by a comma
            }
            else {
                System.out.print(array[i] + "\n");							// Condition where at the end of the printed values, a comma is not included
            }
        }
        return;
    }
    /*
     * Main method that will shuffle and swap an array with predefined indexed
     * values at a predetermined number of times. The values are first printed
     * with method arrPrint, then shuffled and swapped with method shufArr,
     * then separated by a print line, and finally, the shuffled values are
     * again printed with method arrPrint
     */
    public static void main(String[] args){
        // Prog1a, Paul T. Nguyen
        int timeShuffle = 30;
        double [] startArr =  {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};

        arrPrint(startArr);											// Print method for predetermined format
        shufArr (startArr, timeShuffle);							// Shuffle/swap method for randomizing array
        arrPrint(startArr);											// Print method for predetermined format
    }
}
