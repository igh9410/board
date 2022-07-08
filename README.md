# board
Service Link: http://springbootboardservice-env.eba-xppdbmk5.ap-northeast-2.elasticbeanstalk.com/posts/list
id: test
password: dummy

Spring Boot Web Board Service
View Templates: Thymeleaf
Language: Java
Framework: Spring Boot, Spring Security
Database: MySQL
APIs: JPA (Java Persistence API), Lombok, etc..


# Description
A simple CRUD board service made from scratch using Spring Boot Framework, Spring Security and JPA.
Not-logged-in users can only view posts.
Logged-in users can write, update, and delete their OWN posts.
Non-admins will be redirected to the access-denied page if they try to update or delete other users posts.
Admin is accessible to all CRUD operations.

Users can search posts by titles and contents.

#Update Logs
7/7/2022 - Added GlobalExceptionHandler class using @ControllerAdvice. 
