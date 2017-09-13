/**
  * Prog3c, @author Paul T. Nguyen, masc1760 
  * CS 108-2
  * October 1, 2015
  * Description: This program reads in 8 double values it treats them as pairs (four pairs per line).
  * Store each pair in two dimensional array where each "row" contains an array. arrayPrint prints out
  * the resultant array.
  */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Prog3c {
	
	public static void arrayPrint(double num, int count) {				// Print method to print array values in a specified arrangement 
        if (count == 1){
          System.out.println("Longitude: " + num + "  ");		
        }
        else {
          System.out.print("Latitude: " + num + ",  ");		
        }
	}
	
	public static void main(String[] args) {
		Scanner scnr = null;											// Scanner object to read
		File infile = null;												// File object
		
		if (args.length < 1){											// Check for proper command line user input
			System.out.println("Usage: java Prog3c infileName");
			System.exit(0);												// Force terminate program
		}
		infile = new File (args[0]);
		double array[][] = new double [4][2];							// Create 2D array of size four rows, two columns
		try {															// Try to read file
			scnr = new Scanner (infile);								// Checks if output file exists
					
			for (int i = 0; i < 4; i++) {								// 2D array that assigns index positions	
			  for (int j = 0; j < 2; j++) {		
			    if (j < 2) {
	              array[i][j] = scnr.nextDouble();
	              double value = array[i][j];							// Store type double value
	              arrayPrint(value, j);									// Print method
			     }
			  }
		    } 
		}
		catch(FileNotFoundException excpt) {							// Catch file not found error and prompt user
			System.out.println("File not found: " + infile);
		}
	}
}

