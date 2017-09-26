class Node:
  """This creates a node, that is the main object used for the debrujin graph"""
  def __init__(self,kmer):
     self.kmer = kmer
     self.edges = set()
  def __str__(self):
     return "kmer = {}\n edges = {}".format(self.kmer,self.edges)

def openFile(fileName):
    """This function opens a text file and formats the contents into a usable list"""
    dnaString = []
    with open(fileName) as f:
        for line in f.readlines():
            dnaString.append(line.replace('\n',''))
    return dnaString

def dnaReverseComplement(str):
    """This function produces the reverse complement of a DNA string"""
    convertedPassedString = str[::-1]
    returnedComplement = ""
    convertedStr = list(convertedPassedString)
    complementDict = {"A":"T", "T":"A", "C":"G", "G":"C"}

    for base in convertedStr:
        returnedComplement += complementDict[base]
    return returnedComplement

def kmersComp(str, int):
    """This function produces the kmers setlist"""
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

if __name__ == "__main__":
    dna_list = openFile("rosalind_dbru.txt")
    pairs = []
    dna_list+=[dnaReverseComplement(x) for x in dna_list]
    kmerLength = len(dna_list[0])

    node_dict = {}
    for dna in dna_list:
        kmerList = kmersComp(dna, kmerLength)
        last_node = None
        for kmer in kmerList:
            if (len(kmer) != 1):
                if kmer not in node_dict:
                   node_dict[kmer] = Node(kmer)
                if last_node:
                    last_node.edges.add(node_dict[kmer])
                last_node = node_dict[kmer]
            
    for kmer, node in sorted(node_dict.items()):
        for edge in node.edges:
            pairs.append( "({0}, {1})".format(kmer,edge.kmer))
    pairs.sort()        
    for pair in pairs:
        print pair
