def read_fasta(fileName):
    fasta = {}
    title = ''
    with open(fileName) as f:
        for line in f:
            if line.startswith(">"):
                title = line.lstrip(">").rstrip("\n")
            else:
                fasta.setdefault(title, []).append(line.rstrip("\n"))
    for key in fasta:
        fasta[key] = ''.join(fasta[key])
    return fasta

def kmersComp(fileName):
    printedKmers = ''
    dictComp = read_fasta(fileName)
    compList = dictComp.values()
    compiledList = []
    compLength = int(filter(str.isdigit, compList[0]))
    compString = ''.join([i for i in compList[0] if not i.isdigit()])
    
    for i in range(0, len(compString)):
        if ((i + compLength) <= (len(compString)) + 1):
            printedKmers = compString[i:compLength + i]
            if len(printedKmers) == compLength:
                compiledList.append(printedKmers)
    for kmers in sorted(compiledList):
        print kmers
    
