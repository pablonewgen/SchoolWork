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
                'UAA': 'Stop', 'CAA': 'Q', 'AAA': 'K', 'GAA': 'E',
                'UAG': 'Stop', 'CAG': 'Q', 'AAG': 'K', 'GAG': 'E',
                'UGU': 'C', 'CGU': 'R', 'AGU': 'S', 'GGU': 'G', 'UGC': 'C',
                'CGC': 'R', 'AGC': 'S', 'GGC': 'G', 'UGA': 'Stop',
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
    return[dnaToRNA(reverseComp[2:]), dnaToRNA(reverseComp[1:]), dnaToRNA(reverseComp),  dnaToRNA(dna[2:]), dnaToRNA(dna[1:]), dnaToRNA(dna)]

def findBetween(s, first, last):
    try:
        start = s.index(first) + len(first) - 1
        end = s.index(last, start)
        return s[start:end]
    except ValueError:
        return None

def orf(fileName):
    frames = readingFrames(fileName)
    sliced = ""
    for frame in frames:
        protein = findBetween(rnaToProtein(frame), 'M', 'Stop')
        if rnaToProtein(frame) != None: print rnaToProtein(frame)
        if (protein != None):
            sliced = protein[1:] + 'Stop'
            print protein
        inProtein = findBetween(sliced, 'M', 'Stop')
        if (inProtein != None):
            if (len(inProtein) != 1):
                 print inProtein
        
    # cannot splice protein on second try because there is no way to find a 'Stop' as it has already found it in the first subtring splice.
    # Go through the length of the protein instead
        

