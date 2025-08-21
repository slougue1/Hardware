#!/bin/bash

#This code reads usersnames from a file and create users with 
#their corresponding groups, name, password, home dirr, 
#and add those groups in users groups
file=users.csv

#Check if file empty or not exist
if ! [ -s $file ]; then
    echo "Invalid File!!"
    exit 0
fi 

count=$(wc -l < $file) #Counts num of new line so add new line on last line in file to have right count
i=2 #set to 2 to not read first line (identification line)

#Loop to get usersname and password 1 by 1 from 2nd line to last line
#awk to read file by column where each column is seperated by ,
#Set NR to i to cross read each row from the column, thus isolating each username & passwd
while [ $i -le $count ]; do
    #Extract
    user_name=$(awk -v n=$i -F "," 'NR==n {print$1}' users.csv) #$1 for first column
    passwd=$(awk -v n=$i -F "," 'NR==n{print$3}' users.csv) #$3 for third
    fullname=$(awk -v n=$i -F "," 'NR==n{print$2}' users.csv) #2 for fullname

    sudo useradd -c "$fullname" -m $user_name #create usernames, groups, home directory, username's fullname
    sudo usermod -aG users $user_name #add usernames to users group
    echo "$user_name:$passwd" | sudo chpasswd #change each username's passwords to passwd

    echo "---Username $user_name:"
    cat /etc/passwd | grep $user_name #check/display username, its group, & home created
    echo "---Groups where $user_name are in:"
    #cat /etc/group | grep $user_name #Also shows other 10 usernames in users group so too messy
    groups $user_name #check username added in users group
    echo
    ((i++)) #increment to read next row in the column
done

