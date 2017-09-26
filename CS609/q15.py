def openFile(fileName):
    dnaString = []
    with open(fileName) as f:
      for line in f.readlines():
        dnaString.append(line.replace('\n',''))
    return dnaString[0], dnaString[1]

def compare():
    count = 0
    a, b = openFile("rosalind_hamm.txt")
    for x, y in zip(a, b):
        if x == y:
          count += 1
    return (len(a) - count)
