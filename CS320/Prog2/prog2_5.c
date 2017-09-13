#include <stdio.h>
#include <math.h>

float genCos(int degree) {

	float output;
	float PI = 3.14159265;
	float cosDegree = (float) degree;
	
	output = cos(cosDegree*(PI/180));

	return output;
}

float genSine(int degree) {

	float output;
	float PI = 3.14159265;
	float sineDegree = (float) degree;
	
	output = sin(sineDegree*(PI/180));

	return output;
}

int addition(int inputA, int inputB) {

	int output;
	output = inputA + inputB;
	return output;
}

int subtraction(int inputA, int inputB) {

	int output;
	output = inputA - inputB;
	return output;
}

int multiplication(int inputA, int inputB) {

	int output;
	output = inputA * inputB;
	return output;
}

int division(int inputA, int inputB) {

	int output;
	output = inputA / inputB;
	return output;
}

void printFloat(float answer) {

	printf("0.3%f\n", answer);
}

void printRegular(int answer) {

	printf("%d\n", answer);
}


int main() {

	char operator;
	char buffer[3];
	int valueOne, valueTwo;
	int answerInt;
	float answerFloat;
	
	printf ("%s \n", "Assignment #2-5, Paul Truong Nguyen, paul.truong.nguyen@gmail.com");
	printf ("%s ", "> ");
	scanf ("%c %d %d", &operator, &valueOne, &valueTwo);

	if (operator == 'c') {
		//in progress
		answerFloat = genCos(valueOne);
		printFloat(answerFloat);
	}
	else if (operator == 's') {
		//in progress
		answerFloat = genSine(valueOne);
		printFloat(answerFloat);
	}
	else if (operator == '+') {
		answerInt = addition(valueOne, valueTwo);
		printRegular(answerInt);
	}
	else if (operator == '-') {
		answerInt = subtraction(valueOne, valueTwo);
		printRegular(answerInt);
	}
	else if (operator == '*') {
		answerInt = multiplication(valueOne, valueTwo);
		printRegular(answerInt);
	}
	else if (operator == '/') {
		answerInt = division(valueOne, valueTwo);
		printRegular(answerInt);
	}
	else {
		printf("Please use a proper operator: + - / * c s");
	}
	
	return 0;
}
