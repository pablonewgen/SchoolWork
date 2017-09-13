#include <stdio.h>
#include <math.h>

double tanCalculator(int degrees) {
	
	double PI = 3.14159265;
	double result = tan(degrees*(PI/180));
	return result;
}
int main() {

	int input;
	double output;
	
	printf ("%s \n", "Assignment #2-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com");
	printf ("%s \n", "Please input an integer: ");
	scanf ("%d", &input);
	output = tanCalculator(input);
	printf ("%.3f \n", output);

	return 0;
}
