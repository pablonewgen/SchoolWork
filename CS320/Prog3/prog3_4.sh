#!/bin/bash

echo "Assignment #3-4, Paul Truong Nguyen, paul.truong.nguyen@gmail.com"

#store command line arguments
filename=$1
oldString=$2
newString=$3

#stream editor editing passed file
sed -i -e 's/'"$oldString"'/'"$newString"'/g' $filename


