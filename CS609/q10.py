def read_fasta(fileName):
    fasta = {}
    with open(fileName) as f:
        for line in f:
            if line.startswith(">"):
                title = line.lstrip(">").rstrip("\n")
            else:
                fasta.setdefault(title, []).append(line.rstrip("\n"))
    for key in fasta:
        fasta[key] = ''.join(fasta[key])
    return fasta

def trans(fileName):
    transition = 0
    transversion = 0
    ratio = 0
    pairs = ""
    pairedList = []
    transitionList = ["AG", "GA", "CT", "TC"]
    transversionsList = ["AC", "CA", "AT", "TA", "CG", "GC", "GT", "TG"]
    
    dictDNA = read_fasta(fileName)
    values = dictDNA.values()
    strA = list(values[0])
    strB = list(values[1])
    
    for tuplePair in zip(strA, strB):
        pairs += tuplePair[0]
        pairs += tuplePair[1]
        pairedList.append(pairs)
        pairs = ""

    for dna in pairedList:
        transition += transitionList.count(dna)
        transversion += transversionsList.count(dna)

    ratio = float(transition)/float(transversion)
    print(ratio)
    
