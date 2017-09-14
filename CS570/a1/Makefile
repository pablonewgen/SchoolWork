########################################################### 
# Makefile for CS570 Assignment #1
# Ryan Ragasa (cssc1144)
# Paul Nguyen (cssc1143)
###########################################################

FILE=main.c
CC=gcc
ENDFLAGS=-lpthread
FLAGS=-lrt
NAMEFILE=bots
TEXTFILE=QUOTE.txt

run:
	$(CC) $(FILE) -o $(NAMEFILE) $(FLAGS) $(ENDFLAGS)

clean:
	rm -f *.o
	rm -f $(NAMEFILE)
	rm $(TEXTFILE)

#######################[ EOF: Makefile ]###################
