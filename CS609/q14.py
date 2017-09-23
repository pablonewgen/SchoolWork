def openFile(fileName):
    dnaString = ''
    length = 0
    with open(fileName) as f:
        for line in f.readlines():
            length = len(line)
            dnaString += line.replace('\n','')
    return dnaString, length

def dnaReverseComplement(str):
    convertedPassedString = str[::-1]
    returnedComplement = ""
    convertedStr = list(convertedPassedString)
    complementDict = {"A":"T", "T":"A", "C":"G", "G":"C"}

    for base in convertedStr:
        returnedComplement += complementDict[base]
    print returnedComplement

def kmersComp(str, int):
    printedKmers = ''
    compiledList = []
    compLength = int - 1
    compString = str
    reversedComp = dnaReverseComplement(str)
    
    for i in range(0, len(compString)):
        if ((i + compLength) <= (len(compString)) + 1):
            printedKmers = compString[i:compLength + i]
            if len(printedKmers) == compLength:
                compiledList.append(printedKmers)
    for kmers in sorted(compiledList):
        compiledList += kmers
    return compiledList

def
    
