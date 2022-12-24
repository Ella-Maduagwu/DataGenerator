******************
## Table of contents
1. General info
2. Technology
3. Installation/Run

********************

            ##General info
This project is designed and built as a tool to help software developers and engineers
 generate and populate their database with harmless realistic - looking dataset for Testing

 Simplicity was a top priority in this project and I think I achieved that

 I have made a few changes since the submission of the 010 assignment, I have replaced -
 - the option to continue an existing project with the function that will allow the user choose a Template for the dataset they want generated.

*******************

           ##Technology
A list of the technologies I used for this project
1. Intellij Idea ( IDE)
2. MySql Server
3. Mysql workbench
4. Mysql Connector/J: version 8.0.31
5. JavaFaker ( Library): version 1.0.2
 **************

             ##Run
How to successfully run this program

#step 1
please have the connection details for your Database at hand
If you don't have one, it's very easy to create a database, I recommend MySQL database as that was the Database used for this project

please follow the steps below to create a database :

1. input Mysql Work Bench into your browser or follow - https://dev.mysql.com/downloads/mysql/
2. Download and follow all prompt for installation and set-up

ALTERNATIVELY - please refer to https://www.guru99.com/introduction-to-mysql-workbench.html -
and follow the steps of installation and set-up on there

#step 2
Open Project with Intellij Idea and Run

#step 3
create Account - please use a Gmail account
This program is limited to one user, so creating another account will overwrite the previously created account
User email and password will be stored in a file in your directory to enable future Log In

#step 4
Log In

#step 5
provide the connection details for the Database
i. jdbc url in this format - jdbc:mysql://127.0.0.1:3306/testdata or 
jdbc:mysql://localhost:3306/testdata
( you can find the JDBC url by right-clicking on the connection in the welcome page![](whereToFindJdbcUrl.png))
'testdata' being the name of the Database

ii. username e.g. root

iii. password e.g. password

iv. Row limit - being the quantity of data you want to generate i.e. how many rows should your table have

#step 6
select a Template

#step 7
Exit program when prompted and check your database for the generated dataset

It should look somewhat like this - ![](Screenshot (2).png)

 ***************

 please find screenshots in the project directory on the left-hand side


                                   Thank you!





