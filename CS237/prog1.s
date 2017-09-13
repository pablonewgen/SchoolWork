*----------------------------------------------------------------------
* Programmer: Paul Truong Nguyen
* Class Account: masc1489 
* Assignment or Title: Assignment #1
* Filename: prog1.s
* Date completed: 10/12/2015
*----------------------------------------------------------------------
* Problem statement: Program will prompt user to input a date in format MM/DD/YYYY.
*		     Program will then reformat date and output "The date entered is
*		     MONTH DD, YYYY."
* Input: User inputs date in format MM/DD/YYYY
* Output: Program will output string 'The date entered is MONTH DD, YYYY.'
* Error conditions tested: None
* Included files: None
* Method and/or pseudocode: 
*		-print title
*		-prompt for user input
*		-convert user input (cvta2) for month values
*		-store month values in 1
*		-offset array index
*		-set string length inside each index to a default length
*		-store month values in 2
*		-offset array index
*		-correct month string length to actual month string length 
*		for later output formating
*		-convert user input (cvta2) for days values
*		-store day values in 3
*		-convert user input (cvta2) for year values
*		-store year values in 4
*		-set month array into address
*		-format output to contain all instances of possible month values
*		-alter output string to format into expected output
*			Alter set month string length to remove spaces
*		
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
* Register use: A1 is used for month array, A2 is used for month array per month word length,
*		A3 is used for formatting output length
*
*----------------------------------------------------------------------
*
start:  	initIO                  * Initialize (required for I/O)
		setEVT			* Error handling routines
*		initF			* For floating point macros only
	
		lineout		title
		lineout		prompt
		linein		input
		
	*The following converts user input for month
		cvta2		input,#2
		move.l		D0,D1
		add.l		#-1,D1
		mulu		#10,D1
		move.l		D0,D2
		add.l		#-1,D2
		mulu		#4,D2
		
	*The following converts user input for day and year (answer2)
		cvta2		input+3,#2
		move.l		D0,D3
		cvta2		input+6,#4
		move.l		D0,D4
		
		move.l		D3,D0
		cvt2a		answer2,#2
		move.l		D4,D0
		cvt2a		answer2+4,#4
		
	*The following alters and formats stored user input into expected output
		lea		month,A1
		add.l		D1,A1
		
		move.b		0(A1),output
		move.b		1(A1),output+1
		move.b		2(A1),output+2
		move.b		3(A1),output+3
		move.b		4(A1),output+4
		move.b		5(A1),output+5
		move.b		6(A1),output+6
		move.b		7(A1),output+7
		move.b		8(A1),output+8
		move.b		9(A1),output+9
		
		*alter set month string length to remove spaces						
		lea		length,A2
		add.l		D2,A2
		move.l		(A2),D5
		lea		output,A3
		add.l		D5,A3
										
		move.b		answer2+0,0(A3)
		move.b		answer2+1,1(A3)
		move.b		answer2+2,2(A3)
		move.b		answer2+3,3(A3)
		move.b		answer2+4,4(A3)
		move.b		answer2+5,5(A3)
		move.b		answer2+6,6(A3)
		move.b		answer2+7,7(A3)
		move.b		answer2+8,8(A3)
		move.b		answer2+9,9(A3)
		
		lineout		answer1

        	break                   * Terminate execution
*
*----------------------------------------------------------------------
*       Storage declarations
title:		dc.b		'Program #1, Paul Truong Nguyen, masc1489',0
prompt:		dc.b		'Enter a date in MM/DD/YYYY format:',0
month:		dc.b		'January   '
		dc.b		'Feburary  '
		dc.b		'March     '
		dc.b		'April     '
		dc.b		'May       '
		dc.b		'June      '
		dc.b		'July      '
		dc.b		'August    '
		dc.b		'September '
		dc.b		'October   '
		dc.b		'November  '
		dc.b		'December  '
input:		ds.b		80
answer1:	dc.b		'The date entered is '
output:		ds.b		80
answer2:	dc.b		'~~, ~~~~.',0
length:		dc.l		8,9,6,6,4,5,5,7,10,8,9,9

        	end




















