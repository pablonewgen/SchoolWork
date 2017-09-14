def parseDNA( str ):
   "This function prints a count of 'A', 'C', 'G', 'T' from the DNA string being passed in"
   countA = str.count('A')
   countC = str.count('C')
   countG = str.count('G')
   countT = str.count('T')
   
   print ("%s %s %s %s" % (countA, countC, countG, countT))

