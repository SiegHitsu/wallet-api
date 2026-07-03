# Wallet API

A RESTful API built with **Java** and **Spring Boot** to simulate a digital wallet system while reinforcing backend development concepts and best practices.

This project was created as a learning exercise focused on understanding **why** Spring Boot and Spring Security work the way they do, instead of simply following tutorials.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT (JSON Web Token)
- BCrypt
- Maven
- Lombok

---

## 📂 Project Structure

```
src/main/java/com/francisco/wallet
│
├── auth
│   ├── controller
│   ├── dto
│   ├── security
│   └── service
│
├── config
│
├── user
│   ├── entity
│   └── repository
│
└── WalletApiApplication
```

The project follows a layered architecture separating responsibilities into Controllers, Services, Repositories and Entities.

---

## 🔐 Authentication Flow

The authentication process is based on **JWT**.

```
Client
   │
POST /auth/login
   │
   ▼
AuthController
   │
   ▼
AuthService
   │
Validate username/password
   │
Generate JWT
   │
   ▼
Client receives token
```

Every protected request must include:

```http
Authorization: Bearer <jwt_token>
```

Incoming requests are intercepted by a custom JWT filter before reaching the controller.

```
HTTP Request
      │
      ▼
JwtAuthenticationFilter
      │
Validate JWT
      │
Create Authentication
      │
Store Authentication in SecurityContext
      │
      ▼
Controller
```

---

## ✅ Features Implemented

- User registration
- User authentication
- Password encryption using BCrypt
- JWT generation
- JWT validation
- Custom Spring Security Filter
- Protected endpoints
- PostgreSQL integration
- Layered architecture
- DTO pattern

---

## 📌 Endpoints

### Register

```http
POST /auth/register
```

Example request

```json
{
  "username": "francisco",
  "email": "francisco@test.com",
  "password": "123456",
  "fullName": "Francisco Jaime"
}
```

---

### Login

```http
POST /auth/login
```

Example request

```json
{
  "username": "francisco",
  "password": "123456"
}
```

Example response

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### Authenticated User

```http
GET /user/me
```

Requires:

```http
Authorization: Bearer <jwt_token>
```

---

## ⚙️ Database Configuration

Example:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/walletdb
    username: postgres
    password: postgres

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 🧠 Concepts Practiced

This project focuses on understanding backend fundamentals rather than only using frameworks.

### Spring

- Dependency Injection
- Inversion of Control (IoC)
- Bean Lifecycle
- Singleton Beans
- Layered Architecture

### Persistence

- Spring Data JPA
- Hibernate
- Entity Mapping
- Dirty Checking
- Repository Pattern

### Security

- JWT Authentication
- Spring Security
- SecurityContextHolder
- Authentication
- UserDetails
- UserDetailsService
- Filter Chain
- BCrypt Password Hashing

### Java

- Interfaces
- Records
- DTOs
- Enums
- Immutability
- Exception Handling
- Transactions
- ThreadLocal
- Stack vs Heap
- Polymorphism
- Method Overloading
- Method Overriding

---

## 🛣️ Roadmap

- [x] User Registration
- [x] User Login
- [x] JWT Authentication
- [x] Spring Security Integration
- [ ] Wallet Entity
- [ ] Wallet Creation During Registration
- [ ] Balance Endpoint
- [ ] Deposit Money
- [ ] Withdraw Money
- [ ] Transfer Between Wallets
- [ ] Transaction History
- [ ] Global Exception Handler
- [ ] Refresh Tokens
- [ ] Docker Support
- [ ] Unit Tests
- [ ] Integration Tests

---

## 🎯 Project Goal

The objective of this project is not only to build a functional Wallet API, but also to gain a deeper understanding of backend architecture, Spring Boot internals, authentication, transactions and software design principles by implementing every component from scratch.

Rather than copying existing tutorials, each feature is developed by first understanding the problem it solves and then implementing the solution using Spring Boot best practices.
