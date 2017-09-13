*----------------------------------------------------------------------
* Programmer: Paul Truong Nguyen
* Class Account:  masc1489
* Assignment or Title: Assignment #3
* Filename: prog3.s
* Date completed:  11/19/2015
*----------------------------------------------------------------------
* Problem statement: Program will determine the number of bills and coins based on the 
*		     amount inputted by the user on the command line.
* Input: Valid input by the user is 0.01 to 9999999.99
* Output: Number of Hundreds, Fifties, Twenties, Tens, Fives, Ones, Quarters, Dimes, Nickels, and Pennies
* Error conditions tested: Check for valid input
*				- Valid format
*				- Valid characters
*				- Valid length to total dollars and cents
* Included files: prog3.s
* Method and/or pseudocode: Pseudocode listed as follows:
*				- Prompt title
*				- Prompt user for input
*				- Take in input
*				- Check entire length of user input to see if valid characters, length,
*				  and of the correct format
*				- Default to reprompt user if input is invalid
*				- Convert values for dollars and cents
*				- Check dollars amount against hundreds, fifties, twenties, tens, fives,
*				  and then ones
*				- Check cents amount against quarters, dimes, nickels, and pennies
*				- Print quantity after every denomination check.
*				
* References: I/O Reference Manual
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
*		D1: Intially stores length of input, then used for denomination count=
*		D2: Initually used for decimal count, then used for dollars amount
*		D3: Initially used for input cents length, then used for cents amount
*		D4: Storing one instance of input length
*		D5: Storing second instance of input length
*		D6: Initally stores calculated value for cents
*		A0: Address of the input
*		A1: Address of the count for denomination calculation
*		A2: Address of specified denomination text string
*
*----------------------------------------------------------------------
*
start:  	initIO                  * Initialize (required for I/O)
		setEVT			* Error handling routines
*		initF			* For floating point macros only
		
		* Prompts user for input and clears all used data registers for new run
		
		lineout		title		
begin:		lineout		prompt
		clr.l		D1
		clr.l		D2
		clr.l		D3
		clr.l		D4
		clr.l		D5
		clr.l		D6
		linein		input
		move.l		D0,D1
		move.l		D0,D4
		move.l		D0,D5
		lea		input,A0
		
	* Validates user input

loop:		cmp.l		#0,D1
		ble		convert		* D1 <= 0
		
		cmp.b		#'.',(A0)
		beq		decimal		* input == '.'			
		cmp.b		#'0',(A0)
		blt		bad		* input < '0'
		cmp.b		#'9',(A0)	
		bgt		bad		* input > '9'
		
cont:		add.l		#1,A0
		sub.l		#1,D1
		bra		loop		
		
decimal:	add.l		#1,D2
		cmp.l		#1,D2
		bgt		bad		* D2 > 1	

		add.l		#1,A0
		cvta2		(A0),#2
		move.l		D0,D6
		sub.l		#3,D5
		sub.l		#1,D1
		
afterD:		cmp.l		#0,D1
		ble		convert		* D1 <= 0
		
		cmp.b		#'.',(A0)
		beq		decimal		* input == '.'
			
		cmp.b		#'0',(A0)
		blt		bad		* input < '0'
		cmp.b		#'9',(A0)	
		bgt		bad		* input > '9'
		
		add.l		#1,A0
		sub.l		#1,D1
		add.l		#1,D3
		cmp.l		#2,D3
		bgt		bad		* D3 > 2
		bra		afterD	
		
bad:		lineout		invalid
		bra		begin	
		
	* Calculations to convert user input to desired values of specific denominations
		
convert:	sub.l		#3,D4
		cvta2		input,D4	* Dollars
		move.l		D0,D2
		move.l		D6,D3
		
	* Manipulates user input to find # of hundreds 
		
hundred:	cmp.l		#100,D2
		blt		fifty		* input < 100
		sub.l		#100,D2
		add.l		#1,D1
		cmp.l		#100,D2
		bge		hundred		* input >= 100			
		lea		hund,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#8
		stripp		output,#8
		add.l		D0,A2
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of fifties			
		
fifty:		cmp.l		#50,D2
		blt		twenty		* input < 50
		sub.l		#50,D2
		add.l		#1,D1
		lea		fif,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#8
		stripp		output,#8
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of twenties	
		
twenty:		cmp.l		#20,D2
		blt		tenC		* input < 20
		sub.l		#20,D2
		add.l		#1,D1	
		cmp.l		#20,D2
		bge		twenty		* input >= 20		
		lea		twen,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#8
		stripp		output,#8
		add.l		D0,A2
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of tens
		
tenC:		cmp.l		#10,D2
		blt		fiveC		* input < 10
		sub.l		#10,D2
		add.l		#1,D1
		lea		ten,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#8
		stripp		output,#8
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of fives	

fiveC:		cmp.l		#5,D2
		blt		oneC		* input < 5
		sub.l		#5,D2
		add.l		#1,D1
		lea		five,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#8
		stripp		output,#8
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of ones	
		
oneC:		cmp.l		#1,D2
		blt		quarter		* input < 1
		sub.l		#1,D2
		add.l		#1,D1
		cmp.l		#1,D2
		bge		oneC		* input >= 1
		lea		one,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#8
		stripp		output,#8
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of quarters	
		
quarter:	cmp.l		#25,D3
		blt		dimeC		* input < 25
		sub.l		#25,D3
		add.l		#1,D1
		cmp.l		#25,D3
		bge		quarter		* input >= 25
		lea		quart,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#2
		stripp		output,#2
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of dimes	

dimeC:		cmp.l		#10,D3
		blt		nickelC		* input < 10
		sub.l		#10,D3
		add.l		#1,D1
		cmp.l		#10,D3
		bge		dimeC		* input >= 10
		lea		dime,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#2
		stripp		output,#2
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of nickels	

nickelC:	cmp.l		#5,D3
		blt		pennyC		* input < 5
		sub.l		#5,D3
		add.l		#1,D1
		lea		nickel,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#2
		stripp		output,#2
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
	* Manipulates user input to find # of pennies	

pennyC:		cmp.l		#1,D3
		blt		exit		* input < 1
		sub.l		#1,D3
		add.l		#1,D1
		cmp.l		#1,D3
		bge		pennyC		* input >= 1
		lea		penny,A1
		lea		output,A2
		move.l		D1,D0
		cvt2a		output,#2
		stripp		output,#2
		add.l		D0,A2	
		
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+
		move.b		(A1)+,(A2)+

		lineout		output		
		clr.l		D1
		
exit:
        	break                   * Terminate execution
*
*----------------------------------------------------------------------
*       Storage declarations
title:		dc.b		'Program #3, Paul Truong Nguyen, masc1489',0
prompt:		dc.b		'Enter a dollar amount in U.S. Dollars (no $ sign):',0
invalid:	dc.b		'Sorry, invalid entry.',0
input		ds.b		80
output		ds.b		80
hund:		dc.b		' x Hundred ',0
fif:		dc.b		' x Fifty   ',0
twen:		dc.b		' x Twenty  ',0
ten:		dc.b		' x Ten     ',0
five:		dc.b		' x Five    ',0
one:		dc.b		' x One     ',0
quart:		dc.b		' x Quarter ',0
dime:		dc.b		' x Dime    ',0
nickel:		dc.b		' x Nickel  ',0
penny:		dc.b		' x Penny   ',0


        	end




















