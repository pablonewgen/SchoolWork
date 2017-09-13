/**
 * Prog1B
 * @author Paul T. Nguyen
 * September 15, 2015
 * This is a program that will create two one-dimensional arrays called latitude and longitude 
 * with given coordinates. The program will then print each as columns, separated by a tab space.
 * Then the distance between the second and fourth coordinate pairs are calculated, in miles. 
 *
 **/

import java.util.*;

public class Prog1b {
    public static final double EARTH_RADIUS_KM = 6371;								// Global variable. Value cannot be changed as it is required for sake of conversion

    /*
     * Method printArr formats the values to be printed in a specific manner, ending in a new line.
     */
    public static void printArr (double array1[], double array2[]) {
        int i = 0;

        for (i = 0; i < array1.length; i++) {										   // Loop to iterate through entire array
            System.out.format("%10.6f\t%10.6f%n", array1[i], array2[i]);
        }
    }

    /* This method returns the distance in miles between one geo-location's latitude and longitude
     * with the given other geo-location's latitude and longitude. This will do all of the calculations.
     * Note the use of the global variable for conversion
     */
    public static double findDistance(double x1, double y1, double x2, double y2){
        double lat1 = Math.toRadians(x1);
        double long1 = Math.toRadians(y1);
        double lat2 = Math.toRadians(x2);
        double long2 = Math.toRadians(y2);
        // apply the spherical law of cosines with a triangle composed of the
        // two locations and the north pole
        double theCos = Math.sin(lat1) * Math.sin(lat2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.cos(long1 - long2);
        double arcLength = Math.acos(theCos);
        return arcLength * EARTH_RADIUS_KM;
    }
    /*
     * Main method that will take in predetermined latitude and longitude values, to which will print
     * the initial latitude and longitude coordinates with printArr, call the findDistance method for
     * calculation, sort the array values of latitude from smallest to greatest with java method,
     * Arrays.sort, and then print the new order of latitude and longitude coordinates again with printArr
     */
    public static void main(String[] args){
        //Prog1b, Paul T. Nguyen

        double [] latitude = {48.858093, -3.070000, 27.173891, 51.501476, -22.9519};
        double [] longitude = {2.294694, 37.349998, 78.042068, -0.140634, -43.2104};
        double disLength = findDistance(latitude[1], longitude[1],
                latitude[3], longitude[3]);

        printArr(latitude, longitude);
        System.out.println("\nDistance in kilometers:  " + disLength + "\n");
        Arrays.sort(latitude);														// Java method to sort values from smallest to greatest
        printArr(latitude, longitude);
    }
}