# README for CS320 Assignment 5

The main requirements of this assignment are: 

 * Become more confortable and familiar with Bash, C, Lua, and their interactions with one another with various methods of input and output of data.
 * Correctly use a standard linux distribution and its tools to compile and run programs.
 * Correctly document the solutions to the programs in this README file.
 * Correctly organize my student git repository.
 * Solve the program problems.

The premise of the assignment is to create a Lua interpreter in C, write a Lua file that correctly solves the FizzBuzz problem, and write a bash script to automate the comparison the outputs of a an applicants' attempt to solve FizzBuzz versus the known correct output. 

This assignment assumes the user that is automating the lua checking process has access to the latest lua libraries currently available(lua-5.3.3) and are located locally. This assignment also assumes that the debian distribution used will have build-essential and libreadline-dev also installed. 

All assignments include a title string, 'Assignment #X-Y, Paul Truong Nguyen, paul.truong.nguyen@gmail.com', where X and Y are the appropriate assignment and program numbers. 

All C files were compiled with 
`gcc <FILE.C> -llua -lm -L <Directory you compiled Lua> -I <Directory you compiled lua> -ldl`

#### prog5_1.c
prog5_1.c is a file that acts as a simple Lua interpreter. It will take in a file from the command line, execute the .lua file and then close the lua state. 

 * Prior to running, compile the file with gcc prog5_1.c -llua -lm -L <Directory you compiled Lua.> -I
<Directory you compiled lua> -ldl
 * To run, input onto the command line: ./a.out
 
Note that this interpreter simply executes lua files. It does not interpret Lua code with any standard in input.

#### prog5_2.lua
prog3_2.sh is a lua file that is the solution of how to solve the FizzBuzz problem in Lua. The program runs through a loop from 1 to 100. For every instance of 15, 3, or 5, the appropriate print statement is initiated and the cases handled. Multiples of 15 prints out "FizzBuzz", multiples of 3 prints out "Fizz", and multiples of 5 print out "Buzz"

 * Outside of what was previously stated above, not other actions are needed prior to running
 * To run, input onto the command line: ./a.out  prog5_2.lua
 * ./a.out is referencing prog5_1.c, the lua interpreter. 

#### prog5_3.sh
prog5_3.sh is a bash script that is the culmination of assignment 5. the user must run the script with a lua file as well as a corresponding cirrect output file. The script will then check the output of the lua file against the correct output file. this script will account for the assignment headers in both the lua file and the correct output file. 

 * Prior to running, input onto the command line: chmod +x prog5_3.sh
 * To run, input onto the command line: ./prog5_3.sh <FILE.LUA> <CORRECT.OUTPUT>



#### prog5_4.c
prog5_4.sh is a C program that acts as a simple Lua interpreter. It will take input from standard in. It will then attempt to do multiple-lined for do end, if then else statements. 

 * Prior to running, compile the file with gcc prog5_4.c -llua -lm -L <Directory you compiled Lua.> -I
<Directory you compiled lua> -ldl
 * To run, input onto the command line: ./a.out 