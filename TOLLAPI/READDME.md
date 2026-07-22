# Toll Service

The **Toll Service** serves as the central orchestration component of the Smart Toll Plaza Automation System.

Unlike the remaining microservices, the Toll Service does **not** use a dedicated database. Its primary responsibility is to coordinate interactions between the Vehicle Service, Wallet Service, and Journey Service to process a toll payment successfully.

Whenever a vehicle reaches a toll plaza, this service verifies the vehicle details, fetches the linked FASTag ID, deducts the toll amount from the wallet, records the journey, and sends back the payment result.

---

# Responsibilities

- Accept toll payment requests
- Verify registered vehicles
- Obtain FASTag details
- Process wallet deductions
- Store completed journey information
- Return transaction results
- Coordinate communication across multiple microservices

---

# Architecture

```text
                    Client
                       │
                       ▼
                TollController
                       │
                       ▼
                 TollService
                       │
      ┌────────────────┼────────────────┐
      │                │                │
      ▼                ▼                ▼
VehicleClient     WalletClient    JourneyClient
      │                │                │
      ▼                ▼                ▼
 Vehicle API      Wallet API      Journey API
```

---

# Project Structure

```text
toll-service
│
├── controller
│     TollController
│
├── service
│     TollService
│
├── client
│     VehicleClient
│     WalletClient
│     JourneyClient
│
├── dto
│     TollRequest
│     TollResponse
│     JourneyDTO
│     WalletDTO
│     VehicleDTO
│
├── config
│     RestTemplateConfig
│
└── TollApiApplication
```

---

# Why No Database?

The Toll Service focuses exclusively on **business orchestration**.

It does not store any persistent data locally.

Instead, it exchanges information with other microservices using REST APIs.

| Service | Responsibility |
|----------|---------------|
| Vehicle Service | Validate Vehicle |
| Wallet Service | Deduct Wallet Balance |
| Journey Service | Record Journey Details |

This design promotes loose coupling and follows the **Database per Service** architectural pattern.

---

# Toll Payment Workflow

```text
Receive Toll Request

        │

        ▼

Vehicle Service

Validate Vehicle

        │

        ▼

Retrieve FASTag ID

        │

        ▼

Wallet Service

Deduct Balance

        │

        ▼

Enough Balance?

        │

 ┌──────┴─────────┐

 │                │

No               Yes

 │                │

 ▼                ▼

Return Error   Journey Service

                    │

                    ▼

             Save Journey

                    │

                    ▼

          Return Success Response
```

---

# Service Communication

```text
                Toll Service

                      │

      ┌───────────────┼───────────────┐

      ▼               ▼               ▼

Vehicle API      Wallet API      Journey API

      │               │               │

Validate        Deduct Money     Save Journey

Vehicle         Update Balance   Return Success
```

---

# REST API

## Pay Toll

```text
POST /api/v1/tolls
```

### Request

```json
{
    "vehicleNumber":"TN38AB1234",
    "plaza":"Chennai Toll Plaza",
    "amount":150
}
```

---

### Success Response

```json
{
    "message":"Toll Paid Successfully",
    "vehicleNumber":"TN38AB1234",
    "fastagId":"FT1001",
    "amount":150,
    "status":"SUCCESS"
}
```

<img width="1917" height="958" alt="image" src="https://github.com/user-attachments/assets/eab39961-9f29-4e53-a741-39dc36baa39a" />

---

# Complete Processing Flow

### Step 1

Receive Toll Request

↓

### Step 2

Invoke Vehicle Service

```text
GET /vehicles/{vehicleNumber}
```

↓

Vehicle Available?

↓

No → Return Error

↓

Yes

↓

Fetch FASTag ID

↓

### Step 3

Invoke Wallet Service

```text
PUT /wallet/deduct
```

↓

Sufficient Balance?

↓

No

↓

Return Insufficient Balance

↓

Yes

↓

Wallet Updated

↓

### Step 4

Invoke Journey Service

```text
POST /journeys
```

↓

Journey Recorded

↓

### Step 5

Return Successful Response

---

# Request DTO

## TollRequest

```text
vehicleNumber

plaza

amount
```

---

# Response DTO

## TollResponse

```text
message

vehicleNumber

fastagId

amount

status
```

---

# External Clients

## VehicleClient

Responsibilities

- Verify Vehicle
- Retrieve FASTag Information

---

## WalletClient

Responsibilities

- Deduct Wallet Balance
- Validate Wallet

---

## JourneyClient

Responsibilities

- Save Journey Details
- Return Journey Status

---

# RestTemplate

The Toll Service interacts with other microservices through

```java
RestTemplate
```

A dedicated configuration class provides a reusable `RestTemplate` bean for all outbound REST communications.

---

# Error Handling

Possible failure scenarios include:

- Vehicle Not Found
- FASTag Not Found
- Insufficient Balance
- Wallet Service Unavailable
- Journey Service Unavailable
- Invalid Request
- Network Timeout

---

# Transaction Lifecycle

```text
Vehicle Arrives

      │

      ▼

Vehicle Validation

      │

      ▼

Wallet Deduction

      │

      ▼

Journey Recording

      │

      ▼

Payment Completed
```

---

# Dependencies

The Toll Service relies on the following services:

- Vehicle Service
- Wallet Service
- Journey Service

Ensure these services are running before starting the Toll Service.

---

# Technologies

- Java 21
- Spring Boot
- Spring Web
- RestTemplate
- Bean Validation
- Lombok
- Maven

---

# Running

```bash
mvn spring-boot:run
```

Default Port

```text
8084
```

---

# Running Order

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

# Future Improvements

- OpenFeign Client
- Circuit Breaker (Resilience4j)
- Retry Mechanism
- Distributed Tracing
- Kafka Integration
- Eureka Service Discovery
- Docker Support
- Kubernetes Deployment
- JWT Authentication
- Swagger/OpenAPI Documentation

---

# Author

**KANAGADHARINI.A**

B.E.Computer science Engineering

Saveetha Engineering College

GitHub

https://github.com/isravel-eng
