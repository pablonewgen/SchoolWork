def infermRNA(fileName):
    mRNA = ""
    stopRNACodon = 3
    mRNAValue = 1
    with open(fileName, "r") as infile:
        mRNA += infile.readline()
    listmRNA = list(mRNA)
    AMINO_ACID_TO_NUMBER_OF_CODONS_DICT = {
        'A':4,
        'C':2,
        'D':2,
        'E':2,
        'F':2,
        'G':4,
        'H':2,
        'I':3,
        'K':2,
        'L':6,
        'M':1,
        'N':2,
        'P':4,
        'Q':2,
        'R':6,
        'S':6,
        'T':4,
        'V':4,
        'W':1,
        'Y':2
    }
    for value in listmRNA:
        mRNAValue = AMINO_ACID_TO_NUMBER_OF_CODONS_DICT[value] * mRNAValue
    mRNAValue = mRNAValue * stopRNACodon

    #Assignment calls for mod 1000000
    mRNAValue = mRNAValue % 1000000

    print mRNAValue
    
