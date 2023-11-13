# Blog Application - Backend

This is the backend for a simple blog application developed using Spring Boot. It provides RESTful APIs for managing blog posts, comments, and user authentication.

## Features

- Create, read, update, and delete blog posts.
- Add and view comments on blog posts.
- User authentication for creating and managing blog posts.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java installed
- Maven installed
- Database (e.g., MySQL) set up with the appropriate configuration in `application.properties`.

## Configuration

Update the `application.properties` file with the necessary database configuration:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/DBName
spring.datasource.username=db_username
spring.datasource.password=db_password


