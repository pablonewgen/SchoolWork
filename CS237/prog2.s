*----------------------------------------------------------------------
* Programmer: Paul Truong Nguyen
* Class Account: masc1489
* Assignment or Title: Assignment #2
* Filename: prog2.s
* Date completed: 10/21/2015 
*----------------------------------------------------------------------
* Problem statement: Determine the day of the week for user inputted date in format
*		MM/DD/YYYY.
* Input: MM/DD/YYYY or q/Q
* Output: 'MM/DD/YYYY is a _____day' or program exits out if user inputs q/Q
* Error conditions tested: None
* Included files: None
* Method and/or pseudocode: 
*		Prompt for date value -> lineout
*		Store user input -> linein
*		Calculate day of the week
*		Print user input string and output calculation string in proper format
*		Repeat until quit
* References: I/O Macros
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
*	D1: Initially stores MM value, is manipulated throughout calculation for
*		storing other values inside equations
*	D2: Stores value of finished calculations to be mainpulated for end of 
*		formatted output string
*	D3: Used to handle DD for output string
*	D4: Initially stores DD value, is manipulated throughout calculation for
*		storing other values inside equations
*	D5: Initially stores YYYY value, is manipulated throughout calculation for
*		storing other values inside equations
*	D6: Initially stores MM value duplicate, is manipulated throughout 
*		calculation for storing other values inside equations
*	D7: Used for storing other values inside to do multiple calculation
*		equations. Later used to help format the output string for the 
*		day of the week
*	A1: Day of the week array
*	A2: Day of the week word length
*	A3: USed to format output string length
*
*----------------------------------------------------------------------
*
start:  	initIO                  * Initialize (required for I/O)
		setEVT			* Error handling routines
*		initF			* For floating point macros only	
		
		lineout		title
loop:		lineout		prompt
		linein		input
		move.l		D0,D1
		cmp.b		#'q',input
		beq		exit
		cmp.b		#'Q',input
		beq		exit	
		
	* Converts user input into separated desired values
	
		cvta2		input,#2
		move.l		D0,D1
		move.l		D0,D6
		cvta2		input+3,#2
		move.l		D0,D4
		cvta2		input+6,#4
		move.l		D0,D5

	** CALCULATIONS to convert desired values into day of the week string
			
		move.l		#14,D7			* a
		sub.l		D1,D7
		move.l		D7,D1			
		divu		#12,D1
		ext.l		D1
		
		sub.l		D1,D5			* b
		
		mulu		#12,D1			* c
		add.l		D6,D1
		sub.l		#2,D1
		
		move.l		D5,D6			* d part 1
		divu		#4,D5
		ext.l		D5
		add.l		D6,D5
		add.l		D4,D5
		
		move.l		D6,D4			* d part 2
		divu		#100,D4
		ext.l		D4
		
		divu		#400,D6			* d part 3
		ext.l		D6
		
		mulu		#31,D1			* d part 4
		divu		#12,D1
		ext.l		D1
		
		sub.l		D4,D5			* d complete and % 7
		add.l		D5,D6
		add.l		D6,D1
		divu		#7,D1
		swap		D1
		ext.l		D1
		
		cmp.l		#0,D1
		beq		special
findday:	move.l		D1,D2	
		add.l		#-1,D1
		mulu		#9,D1
		add.l		#-1,D2
		mulu		#4,D2
		
	* Converts calculated day value into properly fromatted day of the week
	* and placement into string
	
		lea		day,A1
		add.l		D1,A1
		
		move.b  	0(A1),output+0
 	 	move.b  	1(A1),output+1 
 	 	move.b  	2(A1),output+2 
 	 	move.b  	3(A1),output+3 
 	 	move.b  	4(A1),output+4 
 	 	move.b  	5(A1),output+5 
 	 	move.b  	6(A1),output+6 
 	 	move.b  	7(A1),output+7 
 	 	move.b  	8(A1),output+8 
		
		lea 	 	length,A2 
 	 	add.l 	 	D2,A2 
		clr.l		D7
 	 	move.l  	(A2),D7
 	 	lea 	 	output,A3 
 	 	add.l 	 	D7,A3 
 	 	 	 	 	 	 	 	 	 	 
 	 	move.b  	period+0,0(A3) 
 	 	move.b  	period+1,1(A3) 
	
	**END CALCULATIONS
		
		clr.l		D1
		clr.l		D3
		clr.l		D5
		
	* Reconverts input date for output string	
	
		cvta2		input,#2	
		move.l		D0,D1
		cvta2		input+3,#2
		move.l		D0,D3
		cvta2		input+6,#4
		move.l		D0,D5
				
		move.l		D1,D0
		cvt2a		answer,#2
		move.l		D3,D0
		divu		#10,D3
		ext.l		D3
		sub.l		#1,D3
		cvt2a		answer+3,#2
		move.l		D5,D0
		cvt2a		answer+6,#4

		lineout		answer
		
	*Clears all values for next looped run
	
		clr.l		D0	
		clr.l		D1
		clr.l		D2
		clr.l		D3
		clr.l		D4
		clr.l		D5
		clr.l		D6
		clr.l		D7
		
		bra		loop
	
	* Handles special case where calculations zero out
	
special:	move.l		#7,D1
		bra		findday		

	* Terminate execution
	
exit:        	break                   	

*
*----------------------------------------------------------------------
*       Storage declarations
title:		dc.b		'Program #2, Paul Truong Nguyen, masc1489',0
prompt:		dc.b		'Enter a date (MM/DD/YYYY) or Q to Quit:',0
day:		dc.b		'Monday   '
		dc.b		'Tuesday  '
		dc.b		'Wednesday'
		dc.b		'Thursday '
		dc.b		'Friday   '
		dc.b		'Saturday '
		dc.b		'Sunday   '
input:		ds.b		80
answer:		dc.b		'~~/~~/~~~~ is a '
output:		ds.b		80
period:		dc.b		'.',0
length:		dc.l		6,7,9,8,6,8,6

        	end




















