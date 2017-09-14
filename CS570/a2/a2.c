/*
 Paul Nguyen cssc1143
 Ryan Ragasa cssc1144
 Assignment #2 Algorithms Analysis
 a2.c file
 6/19.2017 CS570 Summer 2017
 function: main
 
 Main file that will initiate all other c files to test our three algorithms; OPT, Clock, and Second Chance.
 a2.c contains the main function, promptUser(), and getPagesInfo(). Prompt user is a simple function which 
 reads user input and passes it onto the global variable, frameNumber. There is no error checking on the user
 input as there was no need/ not in the requirements. We assumed the user would input a reasonable frame number
 that would not ultimately destroy our hard work =(. getPagesInfo() has two primary goals; parse the pages.txt
 file into an array that we could use and fill all empty pages in the array with -1. As C does not differentiate 
 between 0 and null, -1 is being used to signify a null/non-existant term. This will allow us to us 0 in as a possible
 page number. The main function executes these two functions, as well as call functions for our three algorithms as
 stated previously, before gracefully exiting out with a simple message. 
 
*/
#include "a2.h"

int main(int argc, const char * argv[])
{
	getPagesInfo();
    frameNumber = promptUser();
	
    opt(frameNumber, pages);
	clock(frameNumber, pages);
    secondchance(frameNumber, pages);
	
	printf("All algorithms processed for frame size %d. Thank you. \n", frameNumber);
    return 0;
}

// User Prompt and error checking input.
int promptUser() {
    int valueInput;
    char term;
	
    printf("Please enter desired number of frames: ");
	scanf("%d", &valueInput);
    return valueInput;
}

// Reads through pages.txt file and stores into an array
int getPagesInfo(){
	FILE *fp;
    fp = fopen("pages.txt", "r");
    if(!fp){
        printf("Could not open file. Exiting application.");
        return 1;
    }
	int i;
	for (i = 0; i < 100; i++){					// Setting empty pages to -1 
		pages[i] = -1;							// Because 0 and null are not distinguished in C
	}
    int pageNumber = 0;
    while(!feof(fp)){
        fscanf(fp,"%d", &pages[pageNumber]);    //Get text for Page numbers
        pageNumber++;
    }
    fclose(fp);
}

