/*
 Paul Nguyen cssc1143
 Ryan Ragasa cssc1144
 Assignment #2 Algorithms Analysis
 clock.c file
 6/19.2017 CS570 Summer 2017
 function: clock

 Note: To build the clock we made the design decision to go with an int counter to keep track of the pointer cycling
 through the frames. We also made a while loop to cycle through the sequence array. The most dificult part was 
 figuring out the logic of when and where to set the reference bits. No extra features were implemented that seemed
 to be "not required" nor were there any bugs that appeared to have mainfested given that the arguments were a valid 
 int for the frameNumber and int[] for the sequence. We learned that while an actual "address pointer" could have been 
 useful it wasn't the only way to approach this problem.
 */
 
 #include <stdio.h>
 
int clock(int frameNumber, int sequence[]){
    int pageAddedToFrame;
    int frameIndex;
    int pageIndex;
    int frame[frameNumber];
    int referenceBit[frameNumber];
    int fault = 0;
    int pointerFrame = 0;
    int maxFrameCount = frameNumber - 1;
    
    //Populate all of the frames with -1 and create a reference for each frame location
    int i = 0;
    for(i = 0; i <= maxFrameCount; i++){
        referenceBit[i] = 0; //reference bits start at 0 -> maxframe
        frame[i] = -1;	//-1 means they are null
    }
    
    pageIndex = 0;
    
    //Cycle through the sequence
    while(sequence[pageIndex] != -1){
        
        pageAddedToFrame = 0;
        
        //Check if the page number already exists in a frame
        //if so, we will set the page number's reference bit to 1 (in accordance with the matching frame)
        //and then move on to the next number in the sequence
        for(frameIndex = 0; frameIndex <= maxFrameCount; frameIndex++){
            if(sequence[pageIndex] == frame[frameIndex]){
                referenceBit[frameIndex] = 1;
                pageAddedToFrame = 1;
                break;
            }
        }
        if(pageAddedToFrame == 1){
            pageIndex++;
            continue;
        }
        
        // If the page number is not in a frame we will check the pointer
        // If it is pointing to a frame that has a reference bit of 0 we add the page number to the frame
        // and then set the reference bit to 1 before incrementing the fault counter
        // If not, we make the reference bit 0 and then increment to the next one checking for a reference bit of 0
        while(pageAddedToFrame == 0){
            if (referenceBit[pointerFrame] == 0){
                referenceBit[pointerFrame] = 1;
                frame[pointerFrame] = sequence[pageIndex];
                pageAddedToFrame = 1;
                fault++;
            }
            else{
                referenceBit[pointerFrame] = 0;
            }
            
            pointerFrame++;
            if(pointerFrame > maxFrameCount){
                pointerFrame = 0;
            }
        }
        pageIndex++;
    }
    
    printf("Clock faults: %d\n", fault);
    return 1;
}
