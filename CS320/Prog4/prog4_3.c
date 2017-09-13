#include <stdio.h>

/**
 *
 * CS320, Assignment#4-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
 * This file is one of two files for 4-3. Prog4_3.c is the C program that will
 * take a command line in with the step to encrypt/decrypt. This program also
 * takes a standard input of a file to encrypt/decrypt.
 *
 */

int main(int argc, char **argv) {

	int character;
	int inputOffset = atoi(argv[1]);
	char convertS;
	// The following handles files being passed in to decrypt
	char filename[100];
	scanf("%s", &filename[0]);
	printf("%s \n", filename);
	FILE *readFile=fopen(filename,"r");
	while((character = fgetc(readFile)) !=EOF)
	{
	// Cases to handle situation where cipher passes the 26th avialable
	// character
		if(character == 32) {
			convertS = (char) character;
			printf("%c", convertS);
		}
		else if (character == 97 || character == 65) {
			character += (26-inputOffset);
			convertS = (char) character;
			printf("%c", convertS);
		}
		else if (character == 98 || character == 66) {
			character += (26-inputOffset);
			convertS = (char) character;
			printf("%c", convertS);
		}
		else if (character == 99 || character == 67) {
			character += (26-inputOffset);
			convertS = (char) character;
			printf("%c", convertS);
		}
		else if (character == 100 || character == 68) {
			character += (26-inputOffset);
			convertS = (char) character;
			printf("%c", convertS);
		}
		// default case
		else {
		character-=inputOffset;
		convertS = (char) character;
		printf("%c", convertS);
		}
	}
	fclose(readFile);	
}
