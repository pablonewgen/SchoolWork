*----------------------------------------------------------------------
* Programmer: Paul Truong Nguyen
* Class Account: masc1489
* Assignment or Title: Program 4
* Filename: reverse.s
* Date completed: 12/11/2015
*----------------------------------------------------------------------
* Problem statement: Using a recursive subroutine, reverse a user inputted string. This
* 		     is to be done with two files
* Input: User string
* Output: User string reversed
* Error conditions tested: None
* Included files: prog4.s
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


		org		$8000
		
		* Recursive subroutine reverses inputted string
		
rec:		link		A6,#0 
		movem.l		D1/A0-A1,-(SP) 
		
		move.l		8(A6),A1
		move.l		12(A6),A0
		move.l		16(A6),D1
		
		
		cmp.l		#0,D1
		beq		return
		
		sub.l		#1,D1
		add.l		#1,A0
		
		move.l		D1,-(SP)
		move.l		A0,-(SP)
		move.l		A1,-(SP)
		jsr		rec
		
return:		sub.l		#1,A0
		move.b		(A0),(A1)+
		unlk		A6
		
		rts
		
        	end



















