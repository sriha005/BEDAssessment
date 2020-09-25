This application consists of three main functions:

-setup() which creates the properties file
-Image.writeImage("old_image_path", "new_image_path") which stores the image in a different directory under a new name if desired
-Json.ReadJson("old_image_path"); takes the old image in order to output name and size to Json but its main purpose is to read Json file and output to Excel.

For getting this application running, there are 2 ways to do it

-You can open the project with Eclipse IDE, go to the file labeled "Reader.java" in package com.main, and run it.

-The second way to run this project is to go inside the ms3Interview folder using the Command Prompt, and run the
BED-0.0.1-SNAPSHOT.jar file located in the target folder. This can be done from the interview folder using
the command:
 
java -jar target/BED-0.0.1-SNAPSHOT.jar

The files involved in this application:

yurop 661.jpg - an image file
EmpCol - a pipe delimited file with three columns representing employees
Empsheet - An excel worksheet with three columns representing employees
imageEmployees.json - a json file outputted which has name, size of image plus list of employees
person.json - contains list of employees that are read in
pics - folder where images are moved to by application
archive - folder where images are moved if already existing in pics.
