CS570 Operating Systems Summer 2017

Ryan Ragasa (cssc1144)

Paul Nguyen (cssc1143)

Assignment #3

README FILE


This assignment contains the following files, a header file called a3.h and a main program called main.c. 
Inside the main program, we included a synopsis, and also the appropriate documentation that we used in 
order to make this program. The following command line options include both: no arguments and 4 separate 
ones which identify the countdown timer (in seconds), the hours for the alarm, the minutes for the alarm 
and the seconds for the alarm. 

We made a design decision to use only 2 child processes to handle both the 
countdown timer and the display of the actual clock itself (all operations are in military time 24HR cycle). 
The use of pipes was necessary in order to coordinate the different processes asynchronously. 



We also did complete the “extra credit” portion of the assignment which allowed us to exit the entire program gracefully 
by means of hitting ctrl-c on the keyboard while the countdown was still active. We also debated on whether 
or not to display the countdown timer, but in the end we decided not to. 

There were no known bugs or deficiencies 
that we had previously known as well.    

We learned extensively on how to create and manipulate multiple processes 
using fork() and pipes. We had never worked with pipes or fork()before, it was a great programming experience. 



a3.c - main program

a3.h - header file

Makefile - makefile
bots - outfile


To Run this program call 
make run to compile
Then enter bots to execute
or call gcc a3.c -o bots -lrt -lpthread


