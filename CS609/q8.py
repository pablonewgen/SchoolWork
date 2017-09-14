def dnaReverseComplement(str):
    convertedPassedString = str[::-1]
    returnedComplement = ""
    convertedStr = list(convertedPassedString)
    complementDict = {"A":"T", "T":"A", "C":"G", "G":"C"}

    for base in convertedStr:
        returnedComplement += complementDict[base]
    print returnedComplement

    
