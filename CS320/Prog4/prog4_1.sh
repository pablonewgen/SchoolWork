#!/bin/bash

echo "Assignment #4-1, Paul Truong Nguyen, paul.truong.nguyen@gmail.com"

passFile=$1
targetServer=$2
username=$3

#search in logins for student name matching one found in grades
account=$(grep -i "$username" $passFile)

#parse login credentials for student
login_cut=`echo "${account,,}" | cut -d " " -f1`
password_cut=`echo "$account" | cut -d, -f3`

#display results on terminal

expect -c "
	set timeout 1
	log_user 0
	spawn scp -q $login_cut@$targetServer:\{encryptor,*.enc\} .
	expect yes/no { send yes\r ; exp_continue }
	expect password: { send $password_cut\r }
	sleep 3
	exit
" 
ls -1a *enc*



