##
# Assignment #6-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
#
# The following python file contains three classes, Token, LiteralIntToken,
# and NameToken. LiteralIntToken and NameToken are children to the Token
# class and inherit the GetStringValue function. GetElementType is modified
# in each child to suit their specific needs. 
##

class Token(object): 
	def __init__(self,token):
	# Takes in token from list
		self.token = token
	def GetStringValue(self):
	#Output string representation from current list
		outputString = " ".join(self)
		return outputString
	def GetElementType(self):
		return "Unknown"
class LiteralIntToken(Token):
	def GetElementType(self):
		return "Literal-Int"
class NameToken(Token):

	def GetElementType(self):
		return "Name"

