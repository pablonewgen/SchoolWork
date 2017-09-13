/**
 * Prog3a, @author Paul T. Nguyen, masc1760
 * CS 108-2
 * October 1, 2015
 * Description: Program that reads in two values from the command line: the name of an input file
 * and a word to search for
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Prog3a {

    public static void main(String[] args) {
        Scanner scnr = null;                                                // Scanner object to read
        File file = null;                                                    // Read command line

        if (args.length < 2) {                                                // Check for proper user input
            System.out.println("Usage: java Prog3a infileName searchWord");
            System.exit(0);
        }
        String word = args[1];                                                // Store searched word
        file = new File(args[0]);                                            // Store filename

        if (file.exists()) {
            try {                                                            // Try to read file
                scnr = new Scanner(file);
                System.out.println("Opened file: " + file
                        + " to search for " + "'" + word + "'" + ".");
            } catch (IOException excpt) {                                        // Catch error and prompt user
                System.out.println("Caught IOException: "
                        + excpt.getMessage());
            }
        } else {                                                                // File not found error
            System.out.println("File not found: " + file);
        }
    }
}