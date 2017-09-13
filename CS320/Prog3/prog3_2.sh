#!/bin/bash

echo "Assignment #3-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com"

dir=$1

#parse and store paths of files with #include inside of them
paths=`grep -R "#include" $dir | cut -d ":" -f1`

#loop through files
for single in $paths;
do
#store names of files, copy and convert into .c files
	fn=`basename $single`
	cp $single $fn.c
done

#list and display .c file results on terminal
ls -1 *.c

