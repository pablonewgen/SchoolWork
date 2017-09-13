#!/bin/bash

echo "Assignment #3-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com"

#loop through each passed command line argument
for var in "$@"
do

#compile the currently .c file
	gcc $var -o temp -lm

#run compiled .c file with 64 as the passed integer. parse result
	output=`./temp 64 | cut -d " " -f2`
#parse result a second time
	cleaned=`echo $output | cut -d "," -f1`
#remove compiled .c file for next .c file compilation 
	rm temp
#if else check flags to determine which file is which based on title string output
	if [ "$cleaned" == "#3-1" ];
	then
		echo $var" Assignment #1"
	elif [ "$cleaned" == "#3-2" ];
	then
		echo $var" Assignment #2"
	elif [ "$cleaned" == "#3-3" ];
	then
		echo $var" Assignment #3"
	else
		echo $var" Assignment #4"	
	fi
done	
