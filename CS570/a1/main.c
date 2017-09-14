/*
 CS570 Operating Systems
 Instructor: Dr. Leonard
 Assignment 1
 June 3th 2017
 
 Ryan Ragasa (cssc1144)
 Paul Nguyen (cssc1143)
 */

/* Synopsis - For this assignment we created a file called QUOTE.txt and wrote its process id followed by
 a carriage return and newline. Then closed the file. After that we created a sempahore called FLAG, and
 created 7 threads that managed access to the file QUOTE.txt. They all ran concurrently, even numbered
 threads every two seconds, odd numbered threads every three seconds. They all opened the file Output.TXT,
 and wrote the appropriate Thread ID, as well as the even/odd quote by either Brian Kernigan or Edsger Dijkstra.
 The program also printed the status of each thread running in the console. My prototype called assignment1,
 called the threads and opened the file before adding the Thread Ids to the file (a total of 7 times).
 */

/* Documentation - We used this website [http://www.bogotobogo.com/cplusplus/multithreading_pthread.php] as a
 reference. We used it for Thread Argument passing, creating and also terminating threads.
 */

#include "A1.h"

/* Main Program */
int main(int argc, char const *argv[])
{
	fileHandler();
	threadHandler();
    exitProgram();
}

/* This function handles opening and writing to the file QUOTE.txt */
void fileHandler() {
	char buffer[16] = {0};
    pid = getpid(); 									//Grab the PID
    processFile = fopen("QUOTE.txt", "w+"); 			//Make the QUOTE.txt writable
    if (processFile == NULL) {
        perror("Error");
    }
    else {
        sprintf(buffer, "%d", pid);

	//Print the PID to the file
        fputs(buffer, processFile);
    }
    fputs("\r\n", processFile); 						//Print the carriage return and a newline.
    fclose(processFile);
}

/* This function handles the sempahore, thread creation, thread error checking, and activates assignment1 function */
void threadHandler() {
	//Initialize the Semaphore
    sem_init(&FLAG, 0, 1);
    
    //Initialize and set the thread detached attribute
    pthread_attr_init(&attr);
    pthread_attr_setdetachstate(&attr, PTHREAD_CREATE_JOINABLE);
    
    //Create the threads
    for (currentThread = 0; currentThread < NUMBER_OF_THREADS; currentThread++) {
        createdProcess = pthread_create(&thread[currentThread], &attr, assignment1, (void *) (currentThread+1));
        createErrorCheck(createdProcess);
    }
    
    //Free the attribute and join the threads
    pthread_attr_destroy(&attr);
    for (currentThread = 0; currentThread < NUMBER_OF_THREADS; currentThread++){
        createdProcess = pthread_join(thread[currentThread], &status);
        joinErrorCheck(createdProcess);
    }
    
    //Destroy the Semaphore
    sem_destroy(&FLAG);
}

/* This function checks for an error with each of pthread_create(s) */
int createErrorCheck (int createdProcess){
    if (createdProcess) {
        printf("ERROR, return code from pthread_create() is %d\n", createdProcess);
        exit(-1);
    }
    return 0;
}

// This function checks for an error with each of pthread_join(s)
int joinErrorCheck (int createdProcess){
    if (createdProcess) {
        printf("ERROR, return code from pthread_join() is %d\n", createdProcess);
        exit(-1);
    }
    return 0;
}
/* This function handles which quote is appended to QUOTE.txt file and thread sleeping */
void *assignment1(const void *id){ 						//Traces to the pointer of assignment1
	int i;
    long threadID;
    threadID = (long)id;
    char threadIdBuffer[2] = {0}; 						//This will hold the thread number and a space
    
    char evenQuote[400]={'"','C','o','n','t','r','o','l','l','i','n','g',' ','c','o','m','p','l','e','x','i','t','y',
	' ','i','s',' ','t','h','e',' ','e','s','s','e','n','c','e',' ','o','f',' ','c','o','m','p','u','t','e','r',' ',
	'p','r','o','g','r','a','m','m','i','n','g','.','"',' ','-','-','B','r','i','a','n',' ','K','e','r','n','i','g','a','n','\0'};
    
    char oddQuote[400]={'"','C','o','m','p','u','t','e','r',' ','s','c','i','e','n','c','e',' ','i','s',' ','n','o',
	' ','m','o','r','e',' ','a','b','o','u','t',' ','c','o','m','p','u','t','e','r','s',' ','t','h','a','n',' ','a',
	's','t','r','o','n','o','m','y',' ','i','s',' ','a','b','o','u','t',' ','t','e','l','e','s','c','o','p','e','s',
	'.','"',' ','-','-','E','d','s','g','e','r',' ','D','i','j','k','s','t','r','a','\0'};
    
    
    // Run the loop for 7 times, according to the assignment guidelines
    for (i = 0; i < NUMBER_OF_THREADS; i++) {
        sem_wait(&FLAG);
        fprintf(stderr,"Thread %ld is running \n", threadID);
        processFile = fopen("QUOTE.txt", "a"); 			//Setup the QUOTE.txt file in append mode.
        if (processFile == NULL) {
            perror("Error");
        }
        else {
            if(threadID % 2 == 0){
                //Even numbered threads
                sprintf(threadIdBuffer, "%ld ", threadID);

       	        //Print the Thread ID to the file
                fputs(threadIdBuffer, processFile);

                //Print the even/Kernigan Quote to the file
                fputs(evenQuote, processFile);
            }
            else{
                //Odd numbered threads
                sprintf(threadIdBuffer, "%ld ", threadID);

                //Print the Thread ID to the file
                fputs(threadIdBuffer, processFile);

                //Print the odd/Dijkstra quote to the file
                fputs(oddQuote, processFile);
            }
            
        }
        fputs("\r\n", processFile); 					//Print the carriage return and a newline.
        fclose(processFile);
        sem_post(&FLAG);
        
        if(threadID % 2 == 0)
            //Even numbered threads wait 2 seconds.
            sleep(2);
        else
            //Odd numbered threads wait 3 seconds.
            sleep(3);
    }
    pthread_exit(0);
}

 /* This function ends the program and terminates the threads */
int exitProgram (void){
    printf("Main program has been completed. \n");
    pthread_exit(NULL);
    return 0;
}
