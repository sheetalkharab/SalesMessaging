pre-requisite tools & tech used :
jdk-11
maven
intellij

assumption:
it is assumed file are sales messages are of 2 types and are stored in file in resource folder.
Message can be only in below 2 formats, else program will throw IO exception:
 > MessageType1,apple,10p.   
 > MessageType2,20,banana,10p

task:
Processing requirements:
1. All sales must be recorded.
2. All messages must be processed.
3. After 10messages have been received your application should log that it is pausing, stop accepting
   new messages and log a report detailing the number of sales of each product and their total value. 

solution:
1. give option to choose one of file to user
2. based on option selected, 1 file is selected and data from file is read and stored in List<Message>
3. List<Message> is processed to log the report after 10 messages and then process next 10 and so for, 
    untill all messages are logged.
4. records are stored in file txt file.

command and execute:
1. from intellij, running main method from SalesController.java
 or
2. from root directory run with below command:
   mvn clean install
   java -jar target/SalesMessaging-1.0-SNAPSHOT.jar

future enhancement:
providing interface to user to provide input from outside of project.
storing record files in more standard way
exception handling enhancement.
