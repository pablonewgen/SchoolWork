import re
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
    values = fasta.values()
    return values

def dnaToRNA (str):
    replaceChar = list(str)
    for i, c in enumerate(replaceChar):
        if c == 'T':
            replaceChar[i] = 'U'
    newString = "".join(replaceChar)
    return newString

def rnaToProtein(str):
    proteinSize = 3
    groupedRNA = []
    proteinString= ''
    for i in range (0, len(str), proteinSize):
       groupedRNA.append(str[i : i + proteinSize])
    rnaCodon = {'UUU': 'F', 'CUU': 'L', 'AUU': 'I', 'GUU': 'V', 'UUC': 'F',
                'CUC': 'L', 'AUC': 'I', 'GUC': 'V', 'UUA': 'L', 'CUA': 'L',
                'AUA': 'I', 'GUA': 'V', 'UUG': 'L', 'CUG': 'L', 'AUG': 'M',
                'GUG': 'V', 'UCU': 'S', 'CCU': 'P', 'ACU': 'T', 'GCU': 'A',
                'UCC': 'S', 'CCC': 'P', 'ACC': 'T', 'GCC': 'A', 'UCA': 'S',
                'CCA': 'P', 'ACA': 'T', 'GCA': 'A', 'UCG': 'S', 'CCG': 'P',
                'ACG': 'T', 'GCG': 'A', 'UAU': 'Y', 'CAU': 'H', 'AAU': 'N',
                'GAU': 'D', 'UAC': 'Y', 'CAC': 'H', 'AAC': 'N', 'GAC': 'D',
                'UAA': '*', 'CAA': 'Q', 'AAA': 'K', 'GAA': 'E',
                'UAG': '*', 'CAG': 'Q', 'AAG': 'K', 'GAG': 'E',
                'UGU': 'C', 'CGU': 'R', 'AGU': 'S', 'GGU': 'G', 'UGC': 'C',
                'CGC': 'R', 'AGC': 'S', 'GGC': 'G', 'UGA': '*',
                'CGA': 'R', 'AGA': 'R', 'GGA': 'G', 'UGG': 'W', 'CGG': 'R',
                'AGG': 'R', 'GGG': 'G' }
    for key in groupedRNA:
        if key in rnaCodon:
                    proteinString+=rnaCodon.get(key)
    return proteinString

def dnaReverseComplement(str):
    convertedPassedString = str[::-1]
    returnedComplement = ""
    convertedStr = list(convertedPassedString)
    complementDict = {"A":"T", "T":"A", "C":"G", "G":"C"}

    for base in convertedStr:
        returnedComplement += complementDict[base]
    return returnedComplement

def readingFrames(fileName):
    dnaList = read_fasta(fileName)
    dna = dnaList[0]
    reverseComp = dnaReverseComplement(dna)
    return[dnaToRNA(dna) ,dnaToRNA(dna[1:]), dnaToRNA(dna[2:]), dnaToRNA(reverseComp), dnaToRNA(reverseComp[1:]), dnaToRNA(reverseComp[2:])]

def orf(fileName):
    frames = readingFrames(fileName)
    returnSet = []
    uPList = []
    for frame in frames:
        uncleanedProtein = rnaToProtein(frame)
        uPList.append(uncleanedProtein)
    for uP in uPList:
        start = []
        stop = []
        for starter in re.finditer('M', uP):
            start.append(starter.start())
        for starter in re.finditer('\*', uP):
            stop.append(starter.start())
        for starter in start:
            for end in stop:
                if end > starter:
                    if uP[starter:end] not in returnSet:
                        returnSet.append(uP[starter:end])
                    break
    for protein in returnSet:
        print protein
