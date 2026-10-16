# Task Manager API

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Gradle](https://img.shields.io/badge/Gradle-8+-02303A?style=for-the-badge&logo=gradle&logoColor=white)](https://gradle.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5-25A162?style=for-the-badge&logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![Status](https://img.shields.io/badge/Status-Ready-2EA44F?style=for-the-badge)](https://github.com/)

API REST para gerenciamento de tarefas, desenvolvida com Java, Spring Boot e Gradle.

## Visão Geral

Este projeto oferece um backend para cadastrar, listar, consultar, atualizar e excluir tarefas. A solução foi estruturada com foco em domínio, casos de uso, validação de entrada e testes automatizados.

## Tecnologias

- Java 17
- Spring Boot 3.3.5
- Gradle
- Spring Web
- Spring Validation
- JUnit 5
- MockMvc

## Funcionalidades

- Criação de tarefas
- Listagem de todas as tarefas
- Consulta de tarefa por identificador
- Atualização parcial de tarefa
- Exclusão de tarefa
- Validação de campos obrigatórios
- Tratamento de exceções HTTP
- Status suportados: `PENDING`, `IN_PROGRESS`, `COMPLETED`

## Estrutura do Projeto

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

## Requisitos

- JDK 17+
- Gradle 8+
- Git

## Como Executar

1. Clone o repositório:

```bash
git clone <url-do-repositorio>
cd taskmanager
```

2. Inicie a aplicação:

```bash
./gradlew bootRun
```

3. A API ficará disponível em:

```text
http://localhost:8080
```

## Endpoints

### Criar tarefa

```http
POST /tasks
Content-Type: application/json
```

```json
{
  "title": "Estudar Spring Boot",
  "description": "Revisar testes de integração"
}
```

### Listar tarefas

```http
GET /tasks
```

### Buscar tarefa por ID

```http
GET /tasks/{id}
```

### Atualizar tarefa

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

### Excluir tarefa

```http
DELETE /tasks/{id}
```

## Exemplos de Resposta

```json
{
  "id": "3ac35e5c-38cd-4bdd-b359-11bb43be9ccc",
  "title": "Estudar Spring Boot",
  "description": "Revisar testes de integração",
  "status": "PENDING"
}
```

## Arquitetura e Decisões de Design

A aplicação adota uma abordagem orientada a domínio e casos de uso, com separação clara entre responsabilidades:

- Domain: concentra as entidades e regras do negócio, como `Task`, `TaskStatus`, `TaskId` e exceções específicas.
- Application: reúne os casos de uso, como criação, atualização, consulta e remoção de tarefas.
- Infrastructure: implementa os adaptadores da aplicação, incluindo o controlador REST e o repositório em memória.

Principais decisões de design:

- Uso de `record`s para DTOs e entradas de aplicação, reduzindo boilerplate e deixando o código mais legível.
- Repositório em memória para desenvolvimento ágil, testes e demonstração sem dependência externa.
- Validação no domínio e no nível de entrada para rejeitar dados inconsistentes rapidamente.
- Separação entre regras de negócio e transporte HTTP, facilitando manutenção e evolução.
- Tratamento centralizado de erros com `@RestControllerAdvice` para respostas padronizadas da API.

## Testes

Para executar a suíte completa:

```bash
./gradlew test
```

## Contribuição

Contribuições são bem-vindas. Abra uma issue ou envie um pull request com melhorias, correções ou documentação.

## Licença

Este projeto é um exemplo de aplicação para estudo e desenvolvimento local. Ajustes de licença podem ser definidos conforme a política do repositório.
