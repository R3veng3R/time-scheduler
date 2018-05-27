# time-scheduler
a test project

Installation:
1. git clone https://github.com/R3veng3R/time-scheduler
2. Setup MySQL database with the included script.
3. mvn package
4. Start DB
5. Go to project directory /target 
6. use command line to start the project:
    java -jar time-scheduler-0.0.1-SNAPSHOT.jar -p  - Prints out the current database records.
    java -jar time-scheduler-0.0.1-SNAPSHOT.jar     - Starts as normal and adds record every second
