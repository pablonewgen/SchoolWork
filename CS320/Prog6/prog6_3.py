##
# Assignment #6-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
#
# The following python script that will execute prog6_1.py and prog6_2.py. The 
# script will import the previously noted files and execute various functions 
# to tokenize a file of random strings and values and label them either
# LiteralInt Tokens or Name Tokens.  
##

from prog6_1 import Tokenize
from prog6_2 import * 
import sys

print ("Assignment #6-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com")

passedFile = sys.argv[1] if len(sys.argv) > 1 else "No input file found."
tokens = Tokenize(passedFile)

for string in tokens:
	
	listString = LiteralIntToken.GetStringValue(string)
	# Simple conditional statement to determine is current list of strings
	# Are literal-ints or names. 
	if listString.isdigit():
		print (LiteralIntToken.GetElementType(string))
	else:
		print (NameToken.GetElementType(string))




