/*
 CS570 Operating Systems
 Instructor: Dr. Leonard
 Assignment 3
 June 24th 2017
 
 Ryan Ragasa (cssc1144)
 Paul Nguyen (cssc1143)
 
 File: a3.c

 Synopsis- For this assignment, I had to implement multiple processes and a time viewer.
 I had to implement two process, the first is a child that acts as a clock and prints the
 time every second. The second child process runs a countdown timer that goes down once every
 second until it reaches 00:00. And all of these processes are communicating via pipes accordingly.
 There is also the functionality of an alarm clock that was added in order to tell the user when
 a certain timestamp was met.
*/

#include "a3.h"

int main(int argc, const char * argv[]) {
    signal(SIGINT, my_function);

    int time = DEFAULT;
    
    // Parse the command line arguments
    if(argc==2) {
        time = atoi(argv[1]);
        alarmHours = 0;
        alarmMinutes = 0;
        alarmSeconds  = 0;
    }
    
    if (argc > 2 ) {
        time=atoi(argv[1]);
        alarmHours = atoi(argv[2]);
        alarmMinutes = atoi(argv[3]);
        alarmSeconds  = atoi(argv[4]);
    }
    
    // Create pipeOpen with error checking
    if (pipe(pfdOpen) == -1) {
        printf("Error opening pipe 2!\n");
        exit(1);
    }
    
    // Create pipeClose with error checking
    if (pipe(pfdClose) == -1) {
        printf("Error opening pipe close!\n");
        exit(1);
    }
    
    // First Child
    switch (fork()) {
        // Error checking for first child
        case -1:
            printf("Error forking child 1!\n");
            exit(1);
            
        // Child 1 process
        case 0:
	    if (flag == 0){
            wall_clock();
            }
            exit(1);
            
        default:
            break;
    }
    
    // Second Child
    switch (fork()) {
        // Error checking  for second child
        case -1:
            printf("Error forking child 2!\n");
            exit(1);
            
        // Child 2 process
        case 0:
            if (flag == 0){
            count_down(time);
            }
            exit(1);
        
        default:
        break;
    }
    
    // Pipes error checking
    if (close(pfdOpen[0]) == -1) {
        printf("Error closing reading end of the pipe 2\n");
        exit(EXIT_FAILURE);
    }
    
    if (close(pfdOpen[1]) == -1) {
        printf("Error closing writing end of the pipe 2\n");
        exit(EXIT_FAILURE);
    }

 // Graceful Exit with Ctrl-C
	int closeProgramTime = time;
    for( ;closeProgramTime>0; closeProgramTime--) {
		if (flag == 1) {
			printf("\na3 was forced closed. Goodbye! \n");
			exit(1);
        }
        sleep(1);
    }

    if (flag == 1) {
		printf("\na3 was forced closed. Goodbye! \n");
        exit(1);
    }

    read(pfdClose[0], bufClose, 12);
    printf("Parent process finishing, Goodbye!\n");
    exit(EXIT_SUCCESS);
}

void my_function(int sig) {
    flag = 1;
}

// Alarm clock time setting and message
void alarmTime() {
    time_t now;
    struct tm *lcltime;
    now = time ( NULL );
    lcltime = localtime ( &now );
    if (alarmHours > 0 && alarmMinutes > 0 && alarmSeconds > 0) {
        if (lcltime->tm_hour == alarmHours && lcltime->tm_min == alarmMinutes && lcltime->tm_sec == alarmSeconds) {
			printf ("ALARM TIME WOOOOO BABY DOGGY MAMA PAPA JEEBUS CHEESE %d:%d:%d\n", lcltime->tm_hour, lcltime->tm_min, lcltime->tm_sec );
			if(flag){ 
				_exit(1);
            }      
		}
    }
}

// Count down timer for wall clock into buffer
void count_down(int start)
{
    // String signal to end process
    strcpy(buf, "time to die");
    
    // Close unused pipes
    if (close(pfdOpen[0]) == -1){
        printf("Error closing reading end of pipe 2\n");
        _exit(1);
    }
    
    // Count down loop
    for( ;start>0; start--) {
        // Send signal to child 1 process not to end
        write(pfdOpen[1], "not die yet",12);
        // Count down every second
        sleep(1);
        
        if(flag){ // my action when signal set it 1
            exit(1);
        }
    }
    
    // Signal to sibling to DIE
    write(pfdOpen[1], buf, sizeof(buf));
    
    // Close pipe 2 writing end
    if (close(pfdOpen[1]) == -1) {
        printf("Error closing writing end of pipe 2");
        _exit(EXIT_FAILURE);
    }
    
    // Send back to main
    exit(1);
}

// Wall clock check
void wall_clock() {
    // Temporary buffer to grab the termination string
    char str1[BUF_SIZE];
    
    strcpy(str1, "time to die");
    
    time_t now;
    struct tm *lcltime;
    
    // Close all unsed pipes
    
    if (close(pfdOpen[1]) == -1) {
        printf("Error closing writing end of pipe 2\n");
        _exit(1);
    }

    // Display current time
    for(;;) {
        alarmTime();
        now = time ( NULL );
        lcltime = localtime ( &now );
        
        // Read in message from child 2 process
        int status = read(pfdOpen[0], buf, 12);
        
        // Compare buffer string
        int ret = strcmp(str1, buf);

            if(flag){ // my action when signal set it 1
                _exit(1);
            }

        // If not end, print out current status and buffer
        if (ret != 0) {
            // Show ever second
            printf ("The time is %d:%d:%d\n", lcltime->tm_hour, lcltime->tm_min, lcltime->tm_sec );
            sleep(1);
            
            if(flag){ // my action when signal set it 1
                _exit(1);
            }
        }
        
        // If end signal came in
        if(ret == 0) {
            printf ("The time is %d:%d:%d\n", lcltime->tm_hour, lcltime->tm_min, lcltime->tm_sec );
            // Close writing
            if (close(pfdOpen[0]) == -1) {
                printf("Error closing writing end of pipe 2\n");
                _exit(1);
            }
            if(flag){ // my action when signal set it 1
                _exit(1);
            }
            // Signal to sibling to DIE

        write(pfdClose[1], bufClose, sizeof(bufClose));
        exit(1);
        }
    }
}
