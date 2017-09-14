def dnaToRNA (str):
    replaceChar = list(str)
    for i, c in enumerate(replaceChar):
        if c == 'T':
            replaceChar[i] = 'U'
    new = "".join(replaceChar)
    print new
