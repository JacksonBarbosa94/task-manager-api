# Task Manager API

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8+-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Status](https://img.shields.io/badge/Status-Ready-2EA44F?style=for-the-badge)](https://github.com/)

A REST API for task management, built with Java, Spring Boot, and Gradle.

## Overview

This project provides a backend for creating, listing, retrieving, updating, and deleting tasks. The solution is organized around domain logic, use cases, input validation, and automated tests.

## Technologies

- Java 17
- Spring Boot 3.3.5
- Gradle
- Spring Web
- Spring Validation
- JUnit 5
- MockMvc

## Features

- Create tasks
- List all tasks
- Retrieve a task by ID
- Update a task partially
- Delete a task
- Validate required fields
- Handle HTTP exceptions
- Supported statuses: `PENDING`, `IN_PROGRESS`, `COMPLETED`

## Project Structure

```text
src/
├── main/
│   ├── java/dio/taskmanager/
│   │   ├── application/
│   │   ├── domain/
│   │   ├── infrastructure/
│   │   └── TaskmanagerApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/dio/taskmanager/
```

## Requirements

- JDK 17+
- Gradle 8+
- Git

## How to Run

1. Clone the repository:

```bash
git clone <repository-url>
cd taskmanager
```

2. Start the application:

```bash
./gradlew bootRun
```

3. The API will be available at:

```text
http://localhost:8080
```

## Endpoints

### Create task

```http
POST /tasks
Content-Type: application/json
```

```json
{
  "title": "Study Spring Boot",
  "description": "Review integration tests"
}
```

### List tasks

```http
GET /tasks
```

### Get task by ID

```http
GET /tasks/{id}
```

### Update task

```http
PATCH /tasks/{id}
Content-Type: application/json
```

```json
{
  "title": "Study Spring Boot 3",
  "description": "Update documentation",
  "status": "in-progress"
}
```

### Delete task

```http
DELETE /tasks/{id}
```

## Example Response

```json
{
  "id": "3ac35e5c-38cd-4bdd-b359-11bb43be9ccc",
  "title": "Study Spring Boot",
  "description": "Review integration tests",
  "status": "PENDING"
}
```

## Architecture and Design Decisions

The application follows a domain-driven and use-case-oriented structure, with clear separation of responsibilities:

- Domain: contains core business entities and rules such as `Task`, `TaskStatus`, `TaskId`, and specific exceptions.
- Application: centralizes use cases like creation, update, retrieval, and deletion of tasks.
- Infrastructure: implements adapters such as the REST controller and the in-memory repository.

Key design decisions:

- Use of `record`s for DTOs and application inputs to reduce boilerplate and improve readability.
- In-memory repository to enable fast development, testing, and demonstrations without external database dependencies.
- Validation in both the domain and request layer to reject inconsistent data early.
- Clear separation between business rules and HTTP transport concerns, improving maintainability and extensibility.
- Centralized error handling through `@RestControllerAdvice` to provide consistent API responses.

## Testing

To run the full test suite:

```bash
./gradlew test
```

## Contribution

Contributions are welcome. Open an issue or submit a pull request with improvements, fixes, or documentation updates.

## License

This project is an example application for learning and local development. License terms can be defined according to the repository policy.
