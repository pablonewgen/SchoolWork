/******************************************************
  * A1.h header file for CS570 Assignment #1
  * Ryan Ragasa (cssc1144)
  * Paul Nguyen (cssc1143)
  */


#ifndef CS570AS1_Header_h
#define CS570AS1_Header_h
#define NUMBER_OF_THREADS 7

#include <pthread.h>
#include <semaphore.h>
#include <math.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

sem_t FLAG;
pthread_t thread[NUMBER_OF_THREADS];
pthread_attr_t attr;
FILE *processFile;
pid_t pid;
int createdProcess;
long currentThread;
void *status;

void fileHandler();
void threadHandler();
void *assignment1(const void *id);

#endif

/********************[ EOF: A1.h ]**********************/