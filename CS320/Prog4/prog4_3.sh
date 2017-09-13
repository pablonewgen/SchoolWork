#!/bin/bash

offset=$1

# Loop to handle all aviable .enc files
for file in *.enc
do

	# Compile store basename, and create from base .enc file
	gcc prog4_3.c -o prog4_3 -lm
	fn=`basename $file| cut -d "." -f1`
	output=$(echo $file | ./prog4_3 $offset)
	# The following cleans and parses result into decoded file
	parsed=$(echo ${output#* })
	cleaned=$(echo $parsed | rev | cut -c 2- | rev) 
	echo $cleaned > $fn.dec
done
# Lists each file on its own line
ls -1a *.dec
