# Wallet Service

The **Wallet Service** handles FASTag wallet management within the Smart Toll Plaza Automation System.

Its primary functions include creating FASTag wallets, managing wallet balances, processing recharges, deducting toll charges, and ensuring that sufficient funds are available before every toll transaction.

Whenever a toll payment is requested, the Toll Service interacts with this microservice to complete the payment process.

---

# Responsibilities

- Create FASTag wallets
- Recharge wallet balance
- Deduct toll charges
- Retrieve wallet information
- Delete wallets
- Maintain wallet balances
- Ensure FASTag IDs remain unique
- Verify sufficient wallet balance

---

# Architecture

```
                Client
                   │
                   ▼
           WalletController
                   │
                   ▼
            WalletService
                   │
                   ▼
          WalletRepository
                   │
                   ▼
              H2 Database
```

---

# Project Structure

```
wallet-service
│
├── controller
│     WalletController
│
├── service
│     WalletService
│
├── repository
│     WalletRepository
│
├── entity
│     Wallet
│
├── dto
│     WalletRequest
│     RechargeRequest
│     DeductRequest
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     DuplicateFastTagException
│     FastTagNotFoundException
│     InsufficientBalanceException
│     InvalidAmountException
│
└── WalletApiApplication
```

---

# Entity

## Wallet

| Field | Type | Description |
|--------|------|-------------|
| id | Integer | Primary Key |
| fastagId | String | Unique FASTag Identifier |
| balance | Double | Available Wallet Balance |

---

# DTOs

## WalletRequest

Used for wallet creation requests.

```text
fastagId
```

---

## RechargeRequest

```text
fastagId

amount
```

---

## DeductRequest

```text
fastagId

amount
```

---

# Business Flow

## Create Wallet

```
Client

   │

   ▼

FASTag Exists?

   │

Yes──────────────► Throw Exception

   │

No

   │

Create Wallet

   │

Balance = 0

   │

Return Created
```

---

## Recharge Wallet

```
Receive Request

      │

      ▼

FASTag Exists?

      │

No────────► Exception

      │

Yes

      ▼

Amount > 100 ?

      │

No────────► Invalid Amount

      │

Yes

      ▼

Increase Balance

      │

Save Wallet

      │

Return Success
```

---

## Deduct Wallet Balance

```
Receive Request

      │

      ▼

FASTag Exists?

      │

No────────► Exception

      │

Yes

      ▼

Enough Balance?

      │

No────────► Insufficient Balance

      │

Yes

      ▼

Deduct Amount

      │

Save Wallet

      ▼

Return Updated Wallet
```

---

# REST APIs

## Create Wallet

```
POST /api/v1/wallets
```

### Request

```json
{
    "fastagId":"FT1001"
}
```

### Response

```
201 CREATED
```

<img width="1917" height="841" alt="image" src="https://github.com/user-attachments/assets/afd93c08-54ac-470c-aa88-65fc67988f0e" />

---

## Get Wallet

```
GET /api/v1/wallets/{fastagId}
```

<img width="1917" height="572" alt="image" src="https://github.com/user-attachments/assets/3817bdcd-1969-46a8-a4cf-8dfce9fe1583" />

---

## Get All Wallets

```
GET /api/v1/wallets
```

<img width="1882" height="775" alt="image" src="https://github.com/user-attachments/assets/7f5ecd26-28d1-46a8-bb77-e1d83f44fef1" />

---

## Recharge Wallet

```
PUT /api/v1/wallets/recharge
```

### Request

```json
{
    "fastagId":"FT1001",
    "amount":500
}
```

<img width="1917" height="852" alt="image" src="https://github.com/user-attachments/assets/275cc85c-93eb-44ec-a55c-d9adaa408279" />

---

## Deduct Wallet

```
PUT /api/v1/wallets/deduct
```

### Request

```json
{
    "fastagId":"FT1001",
    "amount":150
}
```

<img width="1917" height="841" alt="image" src="https://github.com/user-attachments/assets/5ed1a892-7ce0-4646-a909-5102fa8a7a2f" />

---

## Delete Wallet

```
DELETE /api/v1/wallets/{id}
```

<img width="1917" height="487" alt="image" src="https://github.com/user-attachments/assets/bd42d6c9-e03d-4525-a420-1d5c52b147d5" />

---

# Wallet Rules

## Wallet Creation

- Every FASTag ID must be unique.
- Newly created wallets are initialized with a balance of **₹0**.

---

## Recharge Rules

- The FASTag ID must already exist.
- The recharge amount should be greater than **₹100**.

---

## Deduction Rules

- A valid FASTag ID is required.
- The wallet should have enough balance to complete the transaction.
- The balance is updated immediately after a successful deduction.

---

# Exception Handling

Global exception handling is implemented using:

```
@RestControllerAdvice
```

Handled exceptions:

- DuplicateFastTagException
- FastTagNotFoundException
- InvalidAmountException
- InsufficientBalanceException
- ValidationException

---

# Error Response

```json
{
    "statusCode":400,
    "errorType":"Validation Failed",
    "errorMessage":"Amount must be greater than 100",
    "timestamp":"2026-07-18T15:20:00"
}
```

---

# Repository

The repository extends

```
JpaRepository<Wallet,Integer>
```

Custom methods:

```java
findByFastagId()

existsByFastagId()
```

---

# Logging

SLF4J is used for logging application events and monitoring service execution.

Logged activities include:

- Wallet Creation
- Wallet Recharge
- Wallet Deduction
- Duplicate FASTag Detection
- Invalid Recharge Amount
- Insufficient Wallet Balance
- Wallet Deletion

---

# Integration

This service is utilized by

## Toll Service

Purpose:

- Deduct Toll Charges
- Verify Wallet Balance

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

```
8082
```

---

# Testing

Recommended tools:

- Postman
- IntelliJ HTTP Client
- curl

---

# Future Improvements

- Transaction History
- Recharge Records
- Wallet Statements
- Daily Transaction Limits
- Automatic Recharge
- JWT Authentication
- Swagger/OpenAPI Documentation
- Docker Integration
- MySQL/PostgreSQL Support
- OpenFeign Client

---

# Author

**Kanagadharini.A**

B.E.Computer Science Engineering

Saveetha Engineering College

https://github.com/kanagadharini/SMART-TOLL-PLAZA-PROJECT
