--[[
 FizzBuzz in Lua
 Assignment #5-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com
 The following is the solution of how to solve the FizzBuzz problem in Lua. 
 The program runs through a loop from 1 to 100. For every instance of 15, 3, or
 5, the appropriate print statement is initiated and the cases handled. 
--]]
print("Assignment #5-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com")

for i = 1, 100
do
	if i % 15 == 0
	then
		print("FizzBuzz")
	elseif i % 3 == 0
	then
		print("Fizz")
	elseif i % 5 == 0
	then
		print("Buzz")
	else
		print(i)
	end
end

