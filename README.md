# Yollo - Project Management System

Yollo is a robust, Spring Boot-based project management application designed to handle agile workflows, including product backlogs, epics, user stories, and sprints. It provides a RESTful API with JWT authentication and comprehensive Swagger documentation.

## 🚀 Features

- **Agile Management**: Manage Products, Epics, User Stories, Sprints, and Tasks.
- **Authentication**: Secure JWT-based authentication for user login and access control.
- **API Documentation**: Interactive API explorer using Swagger/OpenAPI.
- **Data Persistence**: Uses PostgreSQL for reliable data storage with Spring Data JPA.
- **DTO Mapping**: Efficient object mapping using MapStruct.

## 🛠️ Tech Stack

- **Backend**: Java 21, Spring Boot 3.5.x
- **Security**: Spring Security, JWT (JSON Web Token)
- **Database**: PostgreSQL
- **ORM**: Hibernate, Spring Data JPA
- **Documentation**: SpringDoc OpenAPI (Swagger)
- **Utilities**: Lombok, MapStruct
- **Build Tool**: Maven

## 📋 Prerequisites

- **Java 21** or higher
- **Maven 3.x**
- **PostgreSQL** installed and running

## ⚙️ Configuration

Before running the application, update the database configuration in `yollo-project/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/yollo_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Ensure the database `yollo_db` exists in your PostgreSQL instance.

## 🏃 Getting Started

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd Trello-Fork/yollo-project
   ```

2. **Build the project**:
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## 📖 API Documentation

Once the application is running, you can access the Swagger UI at:
[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## 📂 Project Structure

- `com.yollo.controllers`: REST API endpoints.
- `com.yollo.services`: Business logic interfaces and implementations.
- `com.yollo.models`: JPA entities.
- `com.yollo.dtos`: Data Transfer Objects.
- `com.yollo.repositories`: Spring Data JPA repositories.
- `com.yollo.config`: Security and Swagger configurations.
- `com.yollo.mappers`: MapStruct interfaces for DTO conversion.

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
