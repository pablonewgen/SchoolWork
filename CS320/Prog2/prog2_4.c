#include <stdio.h>
#include <stdlib.h>
#include <math.h>

float *genSine(int length, float s) {

	int i;
	float PI = 3.14159265;
	float stepSize;
	float *generatedWave = (float*)malloc(sizeof(float)*length);

	for(i = 0, stepSize= 0; i < length; i++, stepSize += s) {	
		generatedWave[i] = sin(stepSize*(PI/180));
	}
	return generatedWave;
}

float *genDegree(int length, float s) {

	int i;
	float stepSize;
	float *degree = (float*)malloc(sizeof(float)*length);

	for(i = 0, stepSize= 0; i < length; i++, stepSize += s) {	
		degree[i] = stepSize;
	}
	return degree;
}

void printArray(float *data, int length) {

	int i;
	for(i = 0; i < length; i++) {
		printf("%0.2f ", data[i]);
	}
	printf("\n");
}

int main(int argc, char **argv) {

	printf ("%s \n", "Assignment #2-3, Paul Truong Nguyen, paul.truong.nguyen@gmail.com");

	int length = atoi(argv[1]);
	float stepSize = atof(argv[2]);

	float *degreeStep = genDegree(length, stepSize);
	float *sineWave = genSine(length, stepSize);
	printArray(degreeStep, length);
	printArray(sineWave, length);

	free(degreeStep);
	free(sineWave);
}
