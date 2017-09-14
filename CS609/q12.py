def openFile(fileName):
    listFromFile = []
    with open(fileName) as f:
        for line in f.readlines():
            listFromFile.append(line.replace('\n',''))
    return listFromFile

def genomePathProblem(fileName):
    genomePath = ''
    listGenome = openFile(fileName)
    genomePath += listGenome[0]
    for genome in listGenome[1:]:
        genomePath += genome[-1:]
    print(genomePath)
