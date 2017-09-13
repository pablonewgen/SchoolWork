#include <stdio.h>

/**
 *
 * CS320, Assignment#4-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
 * This file is one of two files for 4-2. This C file will take in the both a 
 * command line input(The plain text) and a standard in input (the ciphered 
 * text) from the bash shell script. This program will then calculate the
 * Caeser Cipher offset and prints the result. 
 *
 */

int main(int argc, char **argv) {

//Assignment #4-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com

	int solution;
	char inputPlain = argv[1][0];
	char inputCipher;	
	scanf ("%c", &inputCipher);
	
	// The follow converts the chars to their ASCII values
	int convertedCipher = (int)inputCipher;
	int convertedPlain = (int)inputPlain; 
	// The following calculates the Caeser Cipher offset
	solution = convertedCipher - convertedPlain;
	
	printf("%d \n", solution);
	
	return 0;
}
	
	 
	









