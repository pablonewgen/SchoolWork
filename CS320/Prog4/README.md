# README for CS320 Assignment 4

The main requirements of this assignment are: 

 * Become more confortable and familiar with Bash, C, and their interactions with one another with various methods of input and output of data.
 * Correctly use a standard linux distribution and its tools to compile and run programs.
 * Correctly document the solutions to the programs in this README file.
 * Correctly organize my student git repository.
 * Solve the program problems.

The premise of the assignment is to commit corporate espionage. Figure out how to decipher encrypted files with the stolen user's encryptor.

All assignments include a title string, 'Assignment #X-Y, Paul Truong Nguyen, paul.truong.nguyen@gmail.com', where X and Y are the appropriate assignment and program numbers. 

#### prog4_1.sh
prog3_1.sh is a bash script that opens provided Logins file. Then, the script will search for user Yoko Jamika, store their login information and remote access into sd.lindeneau.com. Once accessed, the script will then copy all files within the root directory. 

 * Prior to running, input onto the command line: chmod +x prog4_1.sh
 * To run, input onto the command line: ./prog4_1.sh path/to/fileLogins sd.lindeneau.com "Yoko Jamika"
 
During runtime, it is assumed the Logins files have been unzipped and in the same directory as the script, due to the fact that the prompt notes the bribed TA had provided these files  in the previous assignment and were organized this way to make things easy to run on the terminal without long file paths. 


#### prog4_2.sh
prog3_2.sh is a bash script that will run the encryptor executable. The output of the encryptor is stored and echoed as standard input into the compiled prog4_2.c file. The script will then recieve an output from the prog4_2.c file and display the caeser cipher key on the terminal


 * Prior to running, input onto the command line: chmod +x prog4_2.sh
 * To run, input onto the command line: ./prog4_2.sh x

Where x is any plaintext alpha letter. 

#### prog4_2.c
prog3_3.c is a C program that takes in a command line argument to pass to the encryptor executable. It will then wait for the encryptor output on its standard input and then decipher the caeser cipher by determining the distance between the plaintext commandline argument letter and the output encryptor cipher letter.

 * No special things need to be done on terminal as the script will copile the program.
 * To run, input onto the command line: ./prog4.2.sh as it will handle both the bash script and the .c file. 

#### prog4_3.sh
prog3_4.sh is a bash script that will loop through all available .enc files. It will then store the filenames and send a commandline arguement of the offset to the prog4_3.c file as well as the specific .enc file to be deciphered. The script will also clean and parse the completed deciphered file, and store this output into a new file, a .dec, with the original .enc's filename. The script will finally list the new .dec files.

 * Prior to running, input onto the command line: chmod +x prog4_3.sh
 * To run, input onto the command line: ./prog3_4.sh x

Where x is the number offset given to us in prog4_2.sh.

#### prog4_3.c
prog3_4.sh is a C program that will take in two inputs, a commandline argument for the caeser cipher offset and a stanard input of the .enc file to be deciphered. The C program will handle multiple cases to decipher all characters in each .enc file. The C program will print the results back into the bash script standard input. 

 * No special things need to be done on terminal as the script will copile the program.
 * To run, input onto the command line: ./prog4.3.sh as it will handle both the bash script and the .c file. 