def openFile(fileName):
    dnaString = []
    with open(fileName) as f:
      for line in f.readlines():
        dnaString.append(line.replace('\n',''))
    return dnaString[0], dnaString[1]

def occurrences():
    returnValue = ''
    start, pattern = openFile("rosalind_subs.txt")
    startLength = len(start)
    patternLength = len(pattern)
    count = []
    
    lengthDifferent = startLength - patternLength + 1
    for i in range(0,lengthDifferent):
        if start[i : patternLength] == pattern:
            count.append((i + 1))
        patternLength += 1

    for index in count:
        returnValue += str(index)
        returnValue += ' '
        
    print returnValue
