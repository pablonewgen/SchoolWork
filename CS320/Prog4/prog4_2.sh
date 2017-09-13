#!/bin/bash

echo "Assignment #4-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com"

# The following inserts plain text, c, into the encryptor executable
# and then captures the result.
aout="c"
eout=$(echo $aout | ./encryptor)

# the following compiles the companion prog4_2.c file to determine the 
# Caeser cipher offset, captures the output, and displays the results.
gcc prog4_2.c -o prog4_2 -lm
output=$(echo $eout | ./prog4_2 $aout)
echo $output



