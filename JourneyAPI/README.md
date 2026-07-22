# Journey Service

The **Journey Service** is responsible for maintaining records of all successful toll transactions by storing vehicle journey information.

After a vehicle completes a successful toll payment, the Toll Service forwards the journey details to this service, where they are saved for future reference, tracking, and reporting purposes.

This microservice functions as the journey history component of the Smart Toll Plaza Automation System.

---

# Responsibilities

- Save completed journey records
- Maintain toll payment history
- Retrieve stored journey details
- Search journeys using vehicle number
- Record journey start and end timestamps
- Store payment status

---

# Architecture

```text
                Client
                   │
                   ▼
          JourneyController
                   │
                   ▼
           JourneyService
                   │
                   ▼
         JourneyRepository
                   │
                   ▼
              H2 Database
```

---

# Project Structure

```text
journey-service
│
├── controller
│     JourneyController
│
├── service
│     JourneyService
│
├── repository
│     JourneyRepository
│
├── entity
│     Journey
│
├── dto
│     JourneyDTO
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     JourneyNotFoundException
│
└── JourneyApiApplication
```

---

# Entity

## Journey

| Field | Type | Description |
|--------|------|-------------|
| id | Integer | Primary Key |
| vehicleNumber | String | Registered Vehicle Number |
| startTime | LocalDateTime | Journey Start Time |
| endTime | LocalDateTime | Journey End Time |
| plaza | String | Name of the Toll Plaza |
| amount | Double | Toll Charge |
| paymentStatus | String | Payment Status |

---

# Journey DTO

This DTO is used to receive journey details from the Toll Service.

```text
vehicleNumber

startTime

endTime

plaza

amount

paymentStatus
```

---

# Business Flow

```text
Receive Journey Request

          │

          ▼

Validate Request

          │

          ▼

Create Journey Entity

          │

          ▼

Store in Database

          │

          ▼

Return Success Response
```

---

# REST APIs

## Save Journey

```text
POST /api/v1/journeys
```

### Request

```json
{
    "vehicleNumber":"TN38AB1234",
    "startTime":"2026-07-18T10:30:00",
    "endTime":"2026-07-18T10:35:00",
    "plaza":"Chennai Toll Plaza",
    "amount":150,
    "paymentStatus":"SUCCESS"
}
```

### Response

```text
201 CREATED
```

<img width="1917" height="1023" alt="image" src="https://github.com/user-attachments/assets/03d610db-d9da-49f6-8356-e7f6d9bf9392" />

---

## Get All Journeys

```text
GET /api/v1/journeys
```

<img width="1917" height="1078" alt="image" src="https://github.com/user-attachments/assets/131b3bd0-288c-440d-87b9-4b540bae2a87" />

---

## Get Journey By Vehicle Number

```text
GET /api/v1/journeys/{vehicleNumber}
```

<img width="1917" height="1078" alt="image" src="https://github.com/user-attachments/assets/6fdb3546-cacf-404d-a75f-dcb71e09fa70" />

---

# Journey Lifecycle

```text
Vehicle Crosses Toll Plaza

        │

        ▼

Payment Successful

        │

        ▼

Toll Service

        │

        ▼

Journey Service

        │

        ▼

Journey Recorded

        │

        ▼

Journey History Available
```

---

# Validation

Incoming requests are validated before storing journey records.

Validation checks include:

- Vehicle Number is required
- Toll Plaza is required
- Toll Amount is required
- Payment Status is required
- Journey Start Time is required
- Journey End Time is required

---

# Payment Status

Supported values:

```text
SUCCESS

FAILED

PENDING
```

---

# Exception Handling

Global exception handling is implemented using

```java
@RestControllerAdvice
```

Handled exceptions:

- JourneyNotFoundException
- ValidationException

---

# Error Response

```json
{
    "statusCode":404,
    "errorType":"Journey Not Found",
    "errorMessage":"Journey not found for vehicle TN38AB1234",
    "timestamp":"2026-07-18T18:20:00"
}
```

---

# Repository

The repository extends

```java
JpaRepository<Journey,Integer>
```

Custom methods:

```java
findByVehicleNumber()

findAll()
```

---

# Integration

This service is mainly used by

## Toll Service

Purpose:

- Save successful toll transactions
- Maintain journey history
- Generate vehicle travel records

---

# Logging

SLF4J logging is used to monitor service activities.

Logged operations include:

- Journey Creation
- Journey Retrieval
- Validation Errors
- Journey Search
- Database Operations

---

# Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- Lombok
- H2 Database
- Maven

---

# Running

```bash
mvn spring-boot:run
```

Default Port

```text
8083
```

---

# Testing

Recommended tools:

- Postman
- IntelliJ HTTP Client
- curl

---

# Future Enhancements

- Journey Pagination
- Search by Date Range
- Vehicle-wise Reports
- Monthly Reports
- Export to Excel
- Export to PDF
- Dashboard Analytics
- Swagger/OpenAPI Documentation
- Docker Support
- MySQL/PostgreSQL Integration

---

# Author

**Kanagadharini.A**

B.Tech Artificial Intelligence & Machine Learning

Saveetha Engineering College

GitHub

https://github.com/isravel-eng
