def evenNumFile (file):
i = 1
readFile = open(file)
for line in readFile.readlines():
    if i % 2 == 0 :
        print line
    i += 1
