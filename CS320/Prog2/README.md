# README for CS320 Assignment 2

The main requirements of this assignment are: 

 * Become more confortable and familiar with C and command line arguments
 * Correctly use a standard linux distribution and its tools to compile and run programs.
 * Correctly document the solutions to the programs in this README file.
 * Correctly organize my student git repository.
 * Solve the program problems.

All assignments include a title string, 'Assignment #X-Y, Paul Truong Nguyen, paul.truong.nguyen@gmail.com', where X and Y are the appropriate assignment and program numbers. 

#### prog2_1.c
prog2_1.c is a C program that outputs the title string as well as the string: 
````
"Hello! My Name is Paul Truong Nguyen". 
````

 * To compile, input onto the command line: gcc prog2_1.c
 * To run, input onto the command line: ./a.out


#### prog2_2.c
prog2_2.c is a C program that outputs the title string. The program also prompts the user with the question:
```
"Hello! What is your name?".
```
The user's input will ellicit a response by the program with:
```
"Hello <User Input>. Nice to meet you!"
```
where "User Input" is the user inputted name or random string.

 * To compile, input onto the command line: gcc prog2_2.c
 * To run, input onto the command line: ./a.out


#### prog2_3.c
prog2_3.c is a C program that outputs the title string. The program also prompts the user to input a integer, hopefully a non-negative integer. 

```
"Please input an integer: "
```
The user-inputted value is then calculated as degrees of tangent. The following is an example:

```
Please input an integer:
<User Input>
<Result of tan(x)>
```

 * To compile, input onto the command line: gcc prog2_3.c -lm
 * To run, input onto the command line: ./a.out

#### prog2_4.c
prog2_4.c is a C program that outputs the title string. The program also produces and outputs a sine wave with a length and a step size of X degrees. The program requires the user to input two command line arguments to run properly. 

 * To compile, input onto the command line: gcc prog2_4.c -lm
 * To run, input onto the command line: ./a.out X Y where X is the length and Y is the step size in degrees

#### prog2_5.c
prog2_5.c is a C program that outputs the title string. The program also attempts to be a simple calculator. The program initally starts with :
```
> 
```
Where the program waits for user input. The program expects a specific format to run calculations, as follows:
```
> + 3 4
```
Where the operator is expected first and values come after. The program currently is able to perform basic functions + - * / but is unable to properly output sine and cosine. 

 * To compile, input onto the command line: gcc prog2_5.c -lm
 * To run, input onto the command line: ./a.out


