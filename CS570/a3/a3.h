/*
 CS570 Operating Systems
 Instructor: Dr. Leonard
 Assignment 3
 June 24th 2017
 
 Ryan Ragasa (cssc1144)
 Paul Nguyen (cssc1143)
 
 File: a3.h
 
*/

#ifndef a3_h
#define a3_h

#include <stdio.h>
#include <time.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <sys/wait.h>

#define DEFAULT 30
#define BUF_SIZE 12
#define PIPE_SIZE 2

//functions for each child process
void wall_clock();
void count_down(int start);
void my_function(int sig);

//global arrays and variables
int pfdOpen[PIPE_SIZE];
int pfdClose[PIPE_SIZE];
int alarmHours, alarmMinutes,alarmSeconds;
char buf[BUF_SIZE];
char bufClose[BUF_SIZE];
volatile sig_atomic_t flag = 0;

#endif /* a3_h */
