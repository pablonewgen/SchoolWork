#!/bin/bash

## Assignment #5-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
## The following file is a bash script that is the culmination of assignment 5.
## The user must run the script with a lua file as well as a corresponding 
## correct output file. The script will then check the output of the lua file 
## against the correct output file
##


echo "Assignment #5-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com"

# The following stores command line values into variables
file=$1
outputFile=$2
correctOutput=`cat $outputFile`

# The follow compiles and formats the resulting output from the lua file
gcc prog5_1.c -o prog5_1 -llua -lm -L lua-5.3.3/src -I lua-5.3.3/src -ldl 
testOutput=$(./prog5_1 $file)
lines=$(./prog5_1 $file | awk 'NR<3')
newline=$'\n'

compareOutput=$compareOutput$lines$newline$correctOutput

# The following compares the output from the lua file to the correct output file
if test "$testOutput" = "$compareOutput";
then
	echo "Passed Test"
else
	echo "Failed Test"
fi
