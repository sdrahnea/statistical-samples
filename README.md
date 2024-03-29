# statistical-samples application

This is an application which provide charts based on information from sources. The goal is to provide charts.
We use the following frameworks: java, sql, spring framework, spring boot, spring jpa, hibernate, primefaces and MySQL as database. 

## Summary
* 1. Sources
* 2. Getting Started (Prerequisites, Installing)
* 3. Running the tests
* 4. Deployment
* 5. Troubleshooting
* 6. Built With
* 7. Do you have any issue?
* 8. Contributing
* 9. Versioning
* 10. Authors
* 11. License
* 12. Donation


## 1. Sources, data and charts
All information is added or is created from open sources from Internet. The charts are created based on information.

### 1.1 Novel Coronavirus (2019-nCoV) dynamic charts
 * https://youtu.be/7tt85NplJ5A
 * https://youtu.be/By7QJZtnnOw 
 * https://youtu.be/JUt5QQiVEVk
 * https://youtu.be/STkwBkQbaqg
 * https://youtu.be/-PogMHu6xOw
 * https://youtu.be/zCJgr8h7bbM
 * https://youtu.be/Mni1TtJYjRE
 * https://youtu.be/KJYBLNqgufg
### 1.2 Corruption Perceptions Index (in progress)

## 2. Getting Started

Clone or download a copy of this project.

### 2.1 Prerequisites

This project requires Java 1.8, MySQL and Maven.

### 2.2 Installing

After MySQL instalation, it is required to create a dabase:

```
CREATE DATABSE sss;
```
Execute the content of `.sql` files, such as: 
```
chart_type.sql
data.sql
```
Note: in case that you run the application starting with MySQL 8.0.4, please execute the following query:
```
ALTER USER '${USER}'@'localhost' IDENTIFIED WITH mysql_native_password BY '${PASSWORD}';
-- where ${USER} and ${PASSWORD} should be provided. 
```
All this files contains initial data. Just copy and paste the file's content Go to downloaded folder and create the build (you should have something similar like the following):
```
SDR:statistical-samples sdrahnea$ mvn clean compile package
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------< com.udc:statistical-samples >----------
[INFO] Building statistical-samples 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:2.6.1:clean (default-clean) @ statistical-samples ---
[INFO] Deleting /Users/sdrahnea/Documents/my-projects/statistical-samples/target
[INFO] 
[INFO] --- maven-enforcer-plugin:1.4.1:enforce (enforce-versions) @ statistical-samples ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ statistical-samples ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 13 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ statistical-samples ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 31 source files to /Users/sdrahnea/Documents/my-projects/statistical-samples/target/classes
[INFO] /Users/sdrahnea/Documents/my-projects/statistical-samples/src/main/java/com/udc/controller/AbstractController.java: Some input files use unchecked or unsafe operations.
[INFO] /Users/sdrahnea/Documents/my-projects/statistical-samples/src/main/java/com/udc/controller/AbstractController.java: Recompile with -Xlint:unchecked for details.
[INFO] 
[INFO] --- maven-enforcer-plugin:1.4.1:enforce (enforce-versions) @ statistical-samples ---
[INFO] 
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ statistical-samples ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO] Copying 13 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:compile (default-compile) @ statistical-samples ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ statistical-samples ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] skip non existing resourceDirectory /Users/sdrahnea/Documents/my-projects/statistical-samples/src/test/resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.7.0:testCompile (default-testCompile) @ statistical-samples ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.18.1:test (default-test) @ statistical-samples ---
[INFO] No tests to run.
[INFO] 
[INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ statistical-samples ---
[INFO] Building jar: /Users/sdrahnea/Documents/my-projects/statistical-samples/target/statistical-samples-0.0.1-SNAPSHOT.jar
[INFO] 
[INFO] --- spring-boot-maven-plugin:1.5.7.RELEASE:repackage (default) @ statistical-samples ---
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.930 s
[INFO] Finished at: 2019-04-30T20:22:57+03:00
[INFO] ------------------------------------------------------------------------
SDR:statistical-samples sdrahnea$ 
```

## 3. Running the tests

This project does not have any kind of tests :).

## 4. Deployment

Once the build (the jar file) is ready the application can be run. Please, use the following command to run the application:
```
SDR:statistical-samples sdrahnea$ java -jar target/statistical-samples-0.0.1-SNAPSHOT.jar
```
If was used default configuration then the application should be available at this url: http://localhost:8081/mytemplate/login.xhtml 
Use the following credentials: username: admin, password: 123.

## 5. Troubleshooting
Possible solution when try to run the project.
### 5.1 JPS incremental annotation processing is disabled.
* Stacktrace sample:
```
....
java: JPS incremental annotation processing is disabled. Compilation results on partial recompilation may be inaccurate. Use build process "jps.track.ap.dependencies" VM flag to enable/disable incremental annotation processing environment.
java: java.lang.ExceptionInInitializerError
Unable to make field private com.sun.tools.javac.processing.JavacProcessingEnvironment$DiscoveredProcessors com.sun.tools.javac.processing.JavacProcessingEnvironment.discoveredProcs accessible: module jdk.compiler does not "opens com.sun.tools.javac.processing" to unnamed module @2fb4bfb9
java: java.lang.ExceptionInInitializerError
.....
```
* Possible solution: take a look into project configuration and make sure to user proper java version (in my case 1.8) 

## 6. Built With

* [Java](https://www.java.com/en/download/) - Java technology allows you to work and play in a secure computing environment. Java allows you to play online games, chat with people around the world, calculate your mortgage interest, and view images in 3D, just to name a few.
* [PrimeFaces](https://www.primefaces.org/) - PrimeFaces is a popular open source framework for JavaServer Faces featuring over 100 components, touch optimized mobilekit, client side validation, theme engine and more.
* [Spring Security](https://spring.io/projects/spring-security) - Spring Security is a powerful and highly customizable authentication and access-control framework. It is the de-facto standard for securing Spring-based applications.
* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".
* [Spring Data](https://spring.io/projects/spring-data) - Spring Data’s mission is to provide a familiar and consistent, Spring-based programming model for data access while still retaining the special traits of the underlying data store.
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa) - Spring Data JPA, part of the larger Spring Data family, makes it easy to easily implement JPA based repositories. This module deals with enhanced support for JPA based data access layers. It makes it easier to build Spring-powered applications that use data access technologies.
* [MySQL](https://www.mysql.com/) - MySQL is the world's most popular open source database. Whether you are a fast growing web property, technology ISV or large enterprise, MySQL can cost-effectively help you deliver high performance, scalable database applications.
* [Maven](https://maven.apache.org/) - Apache Maven is a software project management and comprehension tool. Based on the concept of a project object model (POM), Maven can manage a project's build, reporting and documentation from a central piece of information. 

## 7. Do you have any issue?

Please contact via LinkedIn account or drop an email (read [LICENSE.md](LICENSE.md) file).

## 8. Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## 9. Versioning

We use [SemVer](http://semver.org/) for versioning.

## 10. Authors

* **Sergiu Drahnea** - *Initial work* - [LinkedIn](https://www.linkedin.com/in/sergiu-drahnea)

## 11. License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## 12. Donation
* [PayPal](https://www.paypal.me/sdrahnea) - any donation is welcomed in case that you was pleased with this work :p

