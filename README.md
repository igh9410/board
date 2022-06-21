# board
Spring Boot Web Board Service
View Templates: Thymeleaf
Database: MySQL

Description:
A simple CRUD board service made from scratch using Spring Boot Framework and JPA.
Not-logged-in users can only view posts.
Logged-in users can write, update, and delete their OWN posts.
Non-admins will be redirected to the access-denied page if they try to update or delete other users posts.
Admin is accessible to all CRUD operations.
