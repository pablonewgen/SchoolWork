# README for CS320 Assignment 3

The main requirements of this assignment are: 

 * Become more confortable and familiar with Bash and command line arguments
 * Correctly use a standard linux distribution and its tools to compile and run programs.
 * Correctly document the solutions to the programs in this README file.
 * Correctly organize my student git repository.
 * Solve the program problems.

The premise of the assignment is to cheat with complete and utter disregard for all dignity. Because another student recieved perfect scores for all assignments, the end goal is to gain a copy of that student's final project and make it appear to be as my own.

All assignments include a title string, 'Assignment #X-Y, Paul Truong Nguyen, paul.truong.nguyen@gmail.com', where X and Y are the appropriate assignment and program numbers. 

#### prog3_1.sh
prog3_1.sh is a bash script that opens provided Grades and Logins files from the bribed TA. This script will then cross reference between the two files. Grades is searched for the student that gained full points on all assignments and stores their name. That name is then searched in the Logins file to find their login credentials. 

 * Prior to running, input onto the command line: chmod +x prog3_1.sh
 * To run, input onto the command line: ./prog3_1.sh path/to/fileGrades path/to/fileLogins
 
During runtime, it is assumed the Grades and Logins files have been unzipped and in the same directory as the script, due to the fact that the prompt notes the bribed TA had provided these files and were organized this way to make things easy to run on the terminal without long file paths. 


#### prog3_2.sh
prog3_2.sh is a bash script that parses through a poorly protected repository by the smart student. The repository is cloned and then parsed. The parse specifically looks for files that contain "#include" as that will indicate whether that file is a C file. Those files are then copied into a local location while adding the .c file extension to each one. Once completed, the script outputs a list of the copied .c files. 


 * Prior to running, input onto the command line: chmod +x prog3_2.sh
 * To run, input onto the command line: ./prog3_2.sh path/to/cloned/repository

Where the path provided on the command line is to the cloned repository. For example, the path location on my machine was ~/CS320/Assignment3/cs320assignment3

#### prog3_3.sh
prog3_3.c is a bash script that takes in c files as its command line arguments to parse through those files and determine which assignment is which. The script compiles each c file, parses through its output response to identify the title string and isolate the assignment number. A flag check determines which assignment is which. It is assumed that the smart student has named each assignment appropriately.

 * Prior to running, input onto the command line: chmod +x prog3_3.sh
 * To run, input onto the command line: ./prog3_3.sh xxxx.c yyyy.c zzzz.c aaaa.c 
 
xxxxc yyyyc zzzzc aaaac are just sample filenames. The files that should be passed are the ones that were copied over from prog3_2.sh

#### prog3_4.sh
prog3_4.sh is a bash script that will replace strings inside of a file. This script takes three command line arguments. The first argument is the c file. The second argument is the string to be replaced. The third argument is the new string that will replace the string in the second argument. The main point of this program is to change the name and email in the title print out script as to not get caught cheating. 

 * Prior to running, input onto the command line: chmod +x prog3_4.sh
 * To run, input onto the command line: ./prog3_4.sh xxxx.c "oldString" "newString"

xxxx.c is the file we want to change something in, oldString is the string we're looking for to change, and newString is the string that is replacing oldString. 




