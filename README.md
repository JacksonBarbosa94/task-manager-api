# Task Manager API

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8+-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Status](https://img.shields.io/badge/Status-Ready-2EA44F?style=for-the-badge)](https://github.com/)

Language: [Português](README.pt-BR.md) | [English](README.en.md)

Uma API REST para gerenciamento de tarefas, desenvolvida com Java, Spring Boot e Gradle.

English: A REST API for managing tasks, built with Java, Spring Boot, and Gradle.

## Índice / Table of Contents

- [Português](#português)
- [English](#english)

---

## Português

### Visão Geral

Este projeto é uma API REST para cadastrar, listar, consultar, atualizar e excluir tarefas. Ele foi desenvolvido para demonstrar boas práticas em arquitetura de domínio, casos de uso, validação de entrada e testes automatizados.

### Tecnologias

- Java 17
- Spring Boot 3.3.5
- Gradle
- Spring Web
- Spring Validation
- JUnit 5
- MockMvc para testes de integração

### Funcionalidades

- Criação de tarefas
- Listagem de todas as tarefas
- Consulta por identificador
- Atualização parcial de tarefas
- Exclusão de tarefas
- Validação de campos obrigatórios
- Tratamento de erros e exceções HTTP
- Status de tarefa: `PENDING`, `IN_PROGRESS`, `COMPLETED`

### Estrutura do Projeto

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

### Requisitos

- JDK 17+
- Gradle 8+
- Git

### Como executar

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
cd taskmanager
```

2. Execute a aplicação:

```bash
./gradlew bootRun
```

3. A API estará disponível em:

```text
http://localhost:8080
```

### Endpoints

#### Criar tarefa

```http
POST /tasks
Content-Type: application/json
```

```json
{
  "title": "Estudar Spring Boot",
  "description": "Revisar testes end-to-end"
}
```

#### Listar tarefas

```http
GET /tasks
```

#### Buscar tarefa por ID

```http
GET /tasks/{id}
```

#### Atualizar tarefa

```http
PATCH /tasks/{id}
Content-Type: application/json
```

```json
{
  "title": "Estudar Spring Boot 3",
  "description": "Atualizar documentação",
  "status": "in-progress"
}
```

#### Excluir tarefa

```http
DELETE /tasks/{id}
```

### Exemplos de respostas

```json
{
  "id": "3ac35e5c-38cd-4bdd-b359-11bb43be9ccc",
  "title": "Estudar Spring Boot",
  "description": "Revisar testes end-to-end",
  "status": "PENDING"
}
```

### Arquitetura e Decisões de Design

A aplicação segue uma estrutura orientada a domínio e casos de uso, separando responsabilidades em camadas bem definidas:

- Domain: contém as entidades e regras centrais do negócio, como `Task`, `TaskStatus`, `TaskId` e exceções específicas.
- Application: concentra os casos de uso (`CreateTaskUseCase`, `UpdateTaskUseCase`, `GetTaskUseCase`, etc.), isolando a lógica de orquestração das interfaces HTTP e persistência.
- Infrastructure: implementa as integrações externas e adaptadores, como o repositório em memória e o controlador REST.

Principais decisões de design:

- Uso de `record`s para DTOs e entradas de aplicação, reduzindo boilerplate e deixando o código mais expressivo.
- Repositório em memória para facilitar desenvolvimento, testes e demonstração sem dependência de banco de dados.
- Validação no domínio e na camada de entrada para evitar inconsistências e rejeitar dados inválidos cedo.
- Separação de responsabilidades para manter o código testável, extensível e fácil de evoluir.
- Tratamento centralizado de erros com `@RestControllerAdvice`, garantindo respostas consistentes em API.

### Testes

Para executar a suíte de testes:

```bash
./gradlew test
```

### Contribuição

Contribuições são bem-vindas. Abra uma issue ou envie um pull request com melhorias, correções ou documentação.

---

## English

### Overview

This project is a REST API for registering, listing, querying, updating, and deleting tasks. It was developed to demonstrate best practices in domain architecture, use cases, input validation, and automated testing.

### Technologies

- Java 17
- Spring Boot 3.3.5
- Gradle
- Spring Web
- Spring Validation
- JUnit 5
- MockMvc for integration testing

### Features

- Task creation
- Listing all tasks
- Query by identifier
- Partial task update
- Task deletion
- Required field validation
- HTTP error handling
- Task status: `PENDING`, `IN_PROGRESS`, `COMPLETED`

### Project Structure

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

### Requirements

- JDK 17+
- Gradle 8+
- Git

### How to run

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

### Endpoints

#### Create task

```http
POST /tasks
Content-Type: application/json
```

```json
{
  "title": "Study Spring Boot",
  "description": "Review end-to-end tests"
}
```

#### List tasks

```http
GET /tasks
```

#### Get task by ID

```http
GET /tasks/{id}
```

#### Update task

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

#### Delete task

```http
DELETE /tasks/{id}
```

### Example response

```json
{
  "id": "3ac35e5c-38cd-4bdd-b359-11bb43be9ccc",
  "title": "Study Spring Boot",
  "description": "Review end-to-end tests",
  "status": "PENDING"
}
```

### Testing

To run the test suite:

```bash
./gradlew test
```

### Contribution

Contributions are welcome. Open an issue or submit a pull request with improvements, fixes, or documentation updates.

---

## License

This project is provided as a learning and demonstration application. License terms may be defined later according to your repository policy.

## Status

Application ready for local development and testing.
