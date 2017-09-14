/*
/*
 Paul Nguyen cssc1143
 Ryan Ragasa cssc1144
 Assignment #2 Algorithms Analysis
 opt.c file
 6/19.2017 CS570 Summer 2017
 function: opt
 
 Note: To build the opt we made the design decision to make 2 separate counters when figuring out whether or not the frame 
 was actually in the remaining pages of the sequence. We also made a while loop to cycle through the sequence array itself. 
 The most dificult part was figuring out the logic of when and where to set the counters as well as utilizing the 
 "furthest away" frame as well as whether or not it was in the sequence at all. No extra features were implemented that 
 seemed to be "not required" nor were there any bugs that appeared to have manifested given that the arguments were a valid 
 int for the frameNumber and int[] for the sequence. We learned that while setting one variable to be the furthest frame down 
 the sequence was a good option, using two was just as effective.
 */
 
 #include <stdio.h>
 
int opt(int frameNumber, int sequence[]){
    int maxFrameCount = frameNumber - 1;
    int frames[frameNumber];
    int fault;
	
    //Populate all of the frames with -1
    int i = 0;
    for(i = 0; i <= maxFrameCount; i++){
        frames[i] = -1;
    }
    
    // set first frame
    frames[0] = sequence[0];
    fault = 1;
    
    int pageAddedToFrame;
    int pageIndex = 1;
    int frameIndex;
    int pageCheckerIndex;
    int furthestDistance;
    int currentDistance;
    int furthestFrameIndex;
    int ExistinFuturePages;
    int frameIndexNotInFuturePages;
    
    //Cycle through the sequence
    while(sequence[pageIndex] != -1){
        
        pageCheckerIndex = 0;
        furthestDistance = 0;
        currentDistance = 0;
        furthestFrameIndex = 0;
        ExistinFuturePages = 0;
        frameIndexNotInFuturePages = -1;
        pageAddedToFrame = 0;
        
        //Check if the page number already exists in a frame
        //if so, we move on to the next number in the sequence
        for(frameIndex = 0; frameIndex <= maxFrameCount; frameIndex++){
            if(sequence[pageIndex] == frames[frameIndex]){
                pageAddedToFrame = 1;
                break;
            }
        }
        if(pageAddedToFrame == 1){
            pageIndex++;
            continue;
        }
        
        // If Page Number is not in the frame and it has not been added
        // we will cycle through the sequence to find out where to place it
        while(pageAddedToFrame == 0){
            
            // If there's an empty slot in one of the frames, we will add it there
            for(frameIndex = 1; frameIndex <= maxFrameCount; frameIndex++){
                if(frames[frameIndex] < 0){
                    frames[frameIndex] = sequence[pageIndex];
                    pageAddedToFrame = 1;
                    fault++;
                    break;
                }
            }
            if(pageAddedToFrame == 1){
                break;
            }
            
            // If there is not an empty frame we will find the page number in the frame that is furthest away in the sequence
            for(frameIndex = 0; frameIndex <= maxFrameCount; frameIndex++){
                currentDistance = 0;
                ExistinFuturePages = 0;
                pageCheckerIndex = pageIndex + 1;
                
                // Here we will compare each frame to the sequence until a match is found or reaches the end
                // recording it as the "furthestFrameIndex" and the "furthestDistance"
                while(sequence[pageCheckerIndex]){
                    if(frames[frameIndex] == sequence[pageCheckerIndex]){
                        ExistinFuturePages = 1;
                        currentDistance = pageCheckerIndex - pageIndex;
                        if (currentDistance > furthestDistance){
                            furthestDistance = currentDistance;
                            furthestFrameIndex = frameIndex;
                        }
                    }
                    pageCheckerIndex++;
                }
                
                // If the frame does not exist in the sequence, we record that frame in a separate index (it is the furthest)
                if(ExistinFuturePages == 0){
                    frameIndexNotInFuturePages = frameIndex;
                }
            }
            
            // Once we know both the "frameIndexNotInFuturePages" as well as the "furthestFrameIndex" we can replace the frame based on whichever is farther and then increment the fault
            if (frameIndexNotInFuturePages > -1){
                frames[frameIndexNotInFuturePages] = sequence[pageIndex];
                pageAddedToFrame = 1;
                fault++;
            }
            else{
                frames[furthestFrameIndex] = sequence[pageIndex];
                pageAddedToFrame = 1;
                fault++;
            }
        }
        pageIndex++;
        
    }
    printf("OPT faults: %d\n", fault);
    return 1;
}
