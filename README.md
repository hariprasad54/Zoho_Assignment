**1) COMMMENT APP RUN INSTRUCTIONS:** 

CommentApp is a web based Application.
Language stack used is HTML5, CSS, BOOTSTRAP,PHP,MSQL
before going to execute this application few things need to be done

(step by step video uploaded to root folder)

======================================================================================

1) XAMPP software need to installed on system

2) After installing the xampp extract the zip file into "C:/xamp/htdocs" drive

3) Search "Xampp control panel" in search bar and open it

4) click on start buttons in both Apache and mysql server

5)  open web browser (assume chrome) and type localhost/

6) click on phpmyadmin available in right side corner

7) create a database named exactly as "commentapp"

8) phpmyadmin panel open in front of you then there is an option called "import" in menu over there

9) now select .sql file available in extracted zip folder and then import it

10) all tables and data are imported.

============================================================================================

now we are ready to exectute application

1) open localhost/ {app path} in chrome

2) login page is displayed

3) you can create an account and login and post comment and if you forgot password and can recover by your secretkey

==================================================================================

These are the following testcases tested on this app

At Signup page and login page:
 
-------------------------------------------------------------------------------
testcase 1: one of the field is blank 

expected output : all fields are mandatory

original output : same as expected

testcase passed.....

-------------------------------------------------------------------------------

tescase 2: email passed as invalid format (assume abc.com)

exptected output: invalid email

original output : same is expected

testcase passed...

--------------------------------------------------------------------------------

At Forgot Password page:

same as previous : ALL TEST CASES PASSED

-------------------------------------------------------------------------------

At Comment page: 

testcase 1: filtering comments 

expected output : only current user comments displayed

original output : same as expected

TESTCASE PASSED...

-------------------------------------------------------------------------------

once you completed click on logout.



====================================================================================================================================

**2.ROLE HEIRARCHY RUN INSTRUCTIONS**

The folder consists of driver.java file

It is written in Java Language, so To execute this JDK need to be installed on computer

It can be executed in both ways manually or through any IDE like netbeans, eclipse, jetbrains IDE or etc.,

By manually, Follow this steps:

1) open command prompt
2) change directory to location where the file is available (Example, file is available at D:/abc/egf/  change directory to this by simply type "cd D:/abc/efg/ ")
3) After changing the directory now we are ready to run the file
4) to run the program by using the command " javac Driver.java"
5) it creates multiple class files and out of those we need to execute " Driver.class" by simply typing "java Driver"
6) now enter input

