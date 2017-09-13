/**
  * Prog3b, @author Paul T. Nguyen, masc1760 
  * CS 108-2
  * October 1, 2015
  * Description: This program reads in a file of double values, 8 per line with each number
  * separated by a space and then outputs a new file with the same values, 8 per line, each 
  * number separated by a comma. 
  */

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Prog3b {

	public static void main(String[] args) {
		Scanner scnr = null;											// Scanner object to read
		File infile = null;												// File object
		File outfile = null;
		PrintWriter out = null;
		
		if (args.length < 2){											// Check for proper command line user input
			System.out.println("Usage: java Prog3b infileName outfileName");
			System.exit(0);
		}
		infile = new File (args[0]);
		outfile = new File (args[1]);
		try {															// Try to read file
			int i = 0;
			scnr = new Scanner (infile);
			if (!outfile.exists()) {									// Checks if output file exists
				out = new PrintWriter(outfile);
				while (scnr.hasNextDouble()) {							// Formats text file
					if (i == 7 || i == 15) {
						out.print(scnr.nextDouble() + "\r\n");
					}
					else {
						out.print(scnr.nextDouble() + ",");
					}
					i++;	
				}
				out.close();											// Closes writer
			}
			else {
				System.out.println("File " + outfile + " already exists");
			}
		}
		catch(FileNotFoundException excpt) {							// Catch file not found error and prompt user
			System.out.println("File not found: " + infile);
		}
	}
}
