#!/bin/bash

echo "Assignment #3-1, Paul Truong Nguyen, paul.truong.nguyen@gmail.com"

grades=$1
logins=$2

#unzip and open provided files. Lines written prior blackboard announcement
#gunzip -k $grades.gz
#unzip -q $logins.zip

#search in grades for line where student scores 100 multiple times
student=$(grep '100\|100\|100' $grades)

#parse student's name and store
firstname_cut=`echo "$student" | cut -d, -f1`
lastname_cut=`echo "$student" | cut -d, -f2`
fullname=$firstname_cut" "$lastname_cut

#search in logins for student name matching one found in grades
account=$(grep -F "$fullname" $logins)

#parse login credentials for student
login_cut=`echo "$account" | cut -d, -f2`
password_cut=`echo "$account" | cut -d, -f3`

#display results on terminal
echo $fullname
echo $login_cut
echo $password_cut


