#include <stdio.h>

int main() {

	char str[BUFSIZ];
	printf ("%s \n", "Assignment #2-2, Paul Truong Nguyen, paul.truong.nguyen@gmail.com");
	printf ("%s \n", "Hello! What is your name?");
	scanf ("%s", str);
	printf ("Hello %s. Nice to meet you! \n", str);
	
	return 0;
}
