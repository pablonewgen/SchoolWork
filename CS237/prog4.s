*----------------------------------------------------------------------
* Programmer: Paul Truong Nguyen
* Class Account: masc1489
* Assignment or Title: Program 4
* Filename: prog4.s
* Date completed: 12/11/2015
*----------------------------------------------------------------------
* Problem statement: Using a recursive subroutine, reverse a user inputted string. This
* 		     is to be done with two files
* Input: User string
* Output: User string reversed
* Error conditions tested: None
* Included files: reverse.s
* Method and/or pseudocode: 
*		- Prompt user to input string
*		- Store length of string
*		- Store inputted string
*		- Setup memory for output string
*		- Move information onto the stack
*		- JSR into subroutine
*		- Link to stack
*		- Move information off the stack
*		- Check string length, if zero --> exit, if not continue.
*		- Subtract one from the string length, it is now a counter
*		- Move one across the string
*		- Move information back onto the stack
*		- JSR and run subroutine again; exit when counter hits zero
*		- Subtract one from input length
*		- Move one byte of what is in current A0 position into 
*		current A1 position
*		- Unlink and return to main program
*		- Add terminator to output
*		- Line out end string
*		- Line out output string and exit
* References: 
*----------------------------------------------------------------------
*
        	ORG     	$0
        	DC.L    	$3000           * Stack pointer value after a reset
        	DC.L    	start           * Program counter value after a reset
        	ORG     	$3000           * Start at location 3000 Hex
*
*----------------------------------------------------------------------
*
#minclude /home/ma/cs237/bsvc/iomacs.s
#minclude /home/ma/cs237/bsvc/evtmacs.s
*
*----------------------------------------------------------------------
*
* Register use:
*		- D0: Initially holds the string length, moved to D1
*		- D1: Holds string length, used as a counter to 
*		exit recursive function
*		- A0: Address that stores input string
*		- A1: Address that stores output string
*
*----------------------------------------------------------------------
*
start:  	initIO                  * Initialize (required for I/O)
		setEVT			* Error handling routines
*		initF			* For floating point macros only	
		
		* Prompts user for input and sets data and address storage
		
		lineout		title
		lineout		prompt
		linein		input
		move.l		D0,D1
		lea		input,A0
		lea		output,A1
		
		* Moves data onto the stack for subroutine access
		
		move.l		D1,-(SP)
		move.l		A0,-(SP)
		move.l		A1,-(SP)
		
rec:		equ		$8000	
		jsr		rec
		
		* Output reversed string
		
		move.l		#0,(A1)
		lineout		line
		lineout		output

        	break                   * Terminate execution
*
*----------------------------------------------------------------------
*       Storage declarations
title:		dc.b		'Program #4, Paul Truong Nguyen, masc1489',0
prompt:		dc.b		'Enter a string:',0
line:		dc.b		'Here is the string backwards:',0
input		ds.b		80
output		ds.b		80



        	end




















