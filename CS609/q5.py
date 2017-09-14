def wordCounter (str):
    wordSplit = str.split()
    wordCount = {}
    for key in wordSplit:
        if key in wordCount:
            wordCount[key] += 1
        else:
            wordCount[key] = 1
    for i in wordCount:
        print i, wordCount[i]
