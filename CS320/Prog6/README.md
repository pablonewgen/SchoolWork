# README for CS320 Assignment 6

The main requirements of this assignment are: 

 * Become more confortable and familiar with python.
 * Correctly use a standard linux distribution and its tools to compile and run programs.
 * Correctly document the solutions to the programs in this README file.
 * Correctly organize my student git repository.
 * Solve the program problems.

The premise of the assignment is to create three python files. The first python file is to read through a text file and create a list of lists. The second python file is meant to define what is a Token, LiteralIntToken, and a NameToken. The third python file is a script that will execute the previous two python files on a text file given by the user on the command line. 

All assignments include a title string, 'Assignment #X-Y, Paul Truong Nguyen, paul.truong.nguyen@gmail.com', where X and Y are the appropriate assignment and program numbers. 

It is assumed that the python files will be tested with python3 (The version this was developed on was python 3.5.1).
It is also assumed that all files are located in the local directory. 

#### prog6_1.py
prog6_1.py is a tokenizing function written in python. The function will take in a text file to which it will splut on the lines of the text file. It will then split on the available white spaces of wach individual line. This results in a list of lists. 

 * This file does not execute on its own and therefore does not require special handling. 

#### prog6_2.py
prog6_2.py is a python file that contains three classes: Token, LiteralIntToken, and NameToken. LiteralIntToken and NameToken are children to the Token class and inherit the GetStringValue function. GetElementType is modified in each child to suit their specific needs. The purpose of these classes is to convert the list items to their string representations and help assist prog6_3.py in formatting the proper output for the lists' value type.

 * This file does not execute on its own and therefore does not require special handling. 

#### prog6_3.py
prog6_3.py is a python script that will execute prog6_1.py and prog6_2.py. The script will import the previously noted files and execute various functions to tokenize a file of random strings and values. Once this is done, the script will determine what they are, label them on output onto the terminal, either a Literal-Int or a Name token. 

 * To run, input onto the command line: python3 prog6_3.py <textFile.txt> 

Where the "<>" indicates the text file being tokenized and checked. 
