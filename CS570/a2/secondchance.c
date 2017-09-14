/* 
/*
 Paul Nguyen cssc1143
 Ryan Ragasa cssc1144
 Assignment #2 Algorithms Analysis
 secondchance.c file
 6/19.2017 CS570 Summer 2017
 function: secondchance
 
 Note: To build the Second Chance Algorithm, we set a loop to check if the page number exists in a frame,
 setting or resetting the reference bit accordingly if frames exist or do not exist, etc. We also loop through 
 to reset the position of pages. The most difficult part of this was understanding how to cycle through the 
 frames and capture the correct frame to remove with the reference bit. 
*/

#include <stdio.h>

int secondchance(int frameNumber, int sequence[]){
	int maxFrameCount = frameNumber - 1;	
	int frames[99];
	int referenceBit[99];
	int fault = 0;


	// Setting the values for the frames to -1 and their reference bits
	int k = 0;
	for(k = 0; k <= maxFrameCount; k++){
		frames[k] = -1;					// -1 means they are null
		referenceBit[k] = 0;			// referenceBit start at 0	
	}

	int added;
	int pageAddedToFrame;
	int pIndex = 0;
	int fIndex;

	while(sequence[pIndex] != -1){				
		
		added = 0;
		pageAddedToFrame = 0;
		 
		 
		// We check to see if the page number already exists in a frame
		// If it exists, set pageAddedToFrame to 1 and the referenceBit to 1
		for(fIndex = 0; fIndex <= maxFrameCount; fIndex++){
			if(sequence[pIndex] == frames[fIndex]){
				pageAddedToFrame = 1;
				referenceBit[fIndex] = 1;
				added = 1;
				break;
			}
		}

		// If Page Number exists in a frame, move to the next number
		if(pageAddedToFrame == 1){
			pIndex++;
			continue;
		}

		// Here we check the values in the frames currently and their bits. We 
		// Replace the value with bit set to zero, reposition the pages and reset
		// Bits accordingly. 
		while(pageAddedToFrame == 0 && added == 0){
			int j;
			if (referenceBit[maxFrameCount] == 0){		
				for(j = maxFrameCount; j > 0; j--){		
					frames[j] = frames[j - 1];
					referenceBit[j] = referenceBit[j - 1];
				}
				frames[0] = sequence[pIndex];
				referenceBit[0] = 0;
				added = 1;
				fault++;
			}
			// If the last frame bit does not equal 0, then move the page numbers in the frames to their next frames
			// The last page value will then be moved to the first frame and it's ref bit set to 0.
			// increment page index
			else{
				int temp = frames[maxFrameCount];
				for(j = maxFrameCount; j > 0; j--){
					frames[j] = frames[j - 1];
					referenceBit[j] = referenceBit[j - 1];
				}			
				frames[0] = temp;
				referenceBit[0] = 0;
			}
		}
		pIndex++;
	}

	printf("Second Chance faults: %d\n", fault);
	return 1;
}

