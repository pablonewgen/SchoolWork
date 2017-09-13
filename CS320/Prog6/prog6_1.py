##
# Assignment #6-1, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
#
# The following is a tokenizing function written in python. The function will
# take in a text file to which it will split on the lines and then split on the
# available white space, thus resulting in a list of lists. 
##


def Tokenize(filename): 
	with open(filename) as fp:
		# splits by the line
		lines = fp.read().splitlines()
		# splits lines into tokens
		tokens = [word.split() for word in lines]
		return tokens
