<div align="center">

# Smart Toll Plaza Automation System

### A Toll Collection Platform Built with Spring Boot Microservices

An automated toll management solution developed using **FASTag**, **REST APIs**, and **Spring Boot Microservices**.

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=springboot)
![Spring Cloud Gateway](https://img.shields.io/badge/API-Gateway-blue?style=for-the-badge)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)
![REST API](https://img.shields.io/badge/REST-API-success?style=for-the-badge)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-red?style=for-the-badge)

</div>

---

# Overview

The **Smart Toll Plaza Automation System** is a Spring Boot microservices application created as a capstone project to streamline toll collection through FASTag technology.

This platform replaces manual toll operations by verifying registered vehicles, checking FASTag wallet balances, processing toll deductions, recording travel history, and handling all client requests through a centralized API Gateway.

Every component is implemented as an individual Spring Boot microservice using a layered architecture, with communication established through synchronous REST APIs.

---

# Objectives

- Automate the toll payment process
- Maintain vehicle information
- Handle FASTag wallet operations
- Record journey details
- Support communication between microservices
- Process all client requests via the API Gateway

---

# System Architecture

```text
                    Client
                       │
                       ▼
               API Gateway (8080)
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
 Vehicle Service   Wallet Service   Journey Service
      │
      │
      ▼
   Toll Service
```

---

# Microservices

| Service | Description|
|----------|-------------|
| Vehicle Service | Handles vehicle registration and FASTag details 
| Wallet Service | Controls FASTag wallet balance and recharge operations 
| Journey Service | Maintains records of vehicle journeys 
| Toll Service | Manages the complete toll payment process 
| API Gateway | Serves as the unified access point for all APIs


# Toll Payment Flow

```text
Vehicle Number

      │
      ▼

Vehicle Service

      │
      ▼

Retrieve FASTag ID

      │
      ▼

Wallet Service

      │
      ▼

Deduct Balance

      │
      ▼

Journey Service

      │
      ▼

Store Journey

      │
      ▼

Return Success Response
```

---

# Repository Structure

```text
smart-toll-plaza-automation
│
├── api-gateway
│
├── vehicle-service
│
├── wallet-service
│
├── journey-service
│
├── toll-service
│
└── README.md
```

---

# Features

## Vehicle Service

- Register a vehicle
- Modify vehicle information
- Remove vehicle records
- View vehicle details
- Ensure unique vehicle numbers
- Generate unique FASTag IDs

---

## Wallet Service

- Create a wallet
- Recharge wallet balance
- Deduct toll amount
- View wallet balance
- Prevent negative balance transactions

---

## Journey Service

- Record journey information
- Access journey history
- Search journeys using vehicle number

---

## Toll Service

- Verify vehicle details
- Fetch FASTag information
- Process wallet deductions
- Record journey details
- Return payment confirmation

---

## API Gateway

- Forward incoming requests
- Provide a centralized access point
- Simplify communication between clients and services

---

# Tech Stack

## Backend

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Cloud Gateway

---

## Database

- H2 Database

---

## Communication

- REST APIs
- RestTemplate

---

## Tools

- IntelliJ IDEA
- Postman
- Git
- GitHub
- Maven

---

# Project Structure

Each microservice is organized using a layered architecture.

```text
service-name
│
├── controller
├── service
├── repository
├── entity
├── dto
├── advice
├── exception
└── config
```

---

# Running the Project

Launch the services in the following sequence.

```text
Vehicle Service
        ↓
Wallet Service
        ↓
Journey Service
        ↓
Toll Service
        ↓
API Gateway
```

---

# Default Ports

| Service | Port |
|----------|------|
| API Gateway | 8080 |
| Vehicle Service | 8081 |
| Wallet Service | 8082 |
| Journey Service | 8083 |
| Toll Service | 8084 |

---

# API Gateway Routes

| Route | Destination |
|---------|-------------|
| /vehicles/** | Vehicle Service |
| /wallet/** | Wallet Service |
| /journeys/** | Journey Service |
| /toll/** | Toll Service |

---

# End-to-End Workflow

1. Register the vehicle
2. Create a FASTag wallet
3. Recharge the wallet
4. Initiate toll payment
5. Deduct the wallet balance
6. Save journey information
7. Return the payment result

---

# Key Concepts Demonstrated

- Spring Boot
- REST APIs
- Microservices
- Layered Architecture
- DTO Pattern
- Bean Validation
- Global Exception Handling
- RestTemplate
- API Gateway
- H2 Database
- Logging
- CRUD Operations

---

# Future Enhancements

- Eureka Service Discovery
- OpenFeign Client
- MySQL / PostgreSQL Integration
- Docker Containerization
- JWT Authentication
- Spring Security
- Apache Kafka
- Redis Caching
- Swagger / OpenAPI Documentation
- Kubernetes Deployment

---

# Author

**Kanagadharini.A**

B.E Computer Science Engineering

Saveetha Engineering College

https://github.com/kanagadharini/SMART-TOLL-PLAZA-PROJECT

---
