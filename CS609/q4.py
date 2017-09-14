import sys
def readFile(fileName):
    file = open(fileName, 'r')
    printLines(file)
        
def printLines(filePassed):
    i = 1
    for line in filePassed.readlines():
        if i % 2 == 0 :
            sys.stdout.write(line)
        i += 1
