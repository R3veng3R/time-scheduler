# time-scheduler
A test project.

### Installing
1. git clone https://github.com/R3veng3R/time-scheduler
2. Setup MySQL database with the included script in database folder.
3. Setup DB connection in application.properties
4. use mvn package to build the project
5. Start DB (!This is important, the DB must be accessible )
6. Go to the project directory /target
7. use command line to start the project:
```
    java -jar time-scheduler-0.0.1-SNAPSHOT.jar -p  - Prints out the current database records.
    java -jar time-scheduler-0.0.1-SNAPSHOT.jar     - Starts as normal and adds a record every second
```

## Built With

* [SpringBoot](https://projects.spring.io/spring-boot/) - The Framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MyBatis](http://blog.mybatis.org/) - DB connection
* [Project Lombok](https://projectlombok.org/) - for getters/setters and log