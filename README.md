# time-scheduler
A test project.

### Installing
1. git clone https://github.com/R3veng3R/time-scheduler
2. Setup MySQL database with the included script in database folder.
3. use mvn package to build the project
4. Start DB (!This is important, the DB must be accessible on runtime)
5. Go to the project directory /target
6. use command line to start the project:
```
    java -jar time-scheduler-0.0.1-SNAPSHOT.jar -p  - Prints out the current database records.
    java -jar time-scheduler-0.0.1-SNAPSHOT.jar     - Starts as normal and adds a record every second
```

## Built With

* [SpringBoot](https://projects.spring.io/spring-boot/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MyBatis](http://blog.mybatis.org/)
* [Project Lombok] (https://projectlombok.org/)