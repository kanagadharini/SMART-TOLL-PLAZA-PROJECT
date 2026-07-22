# Vehicle Service

The **Vehicle Service** manages vehicle registration and stores FASTag details within the Smart Toll Plaza Automation System.

It acts as the central repository for vehicle-related information that is accessed by other microservices. Before initiating a toll transaction, the Toll Service uses this service to verify the vehicle and obtain its corresponding FASTag ID.

---

# Responsibilities

- Register vehicles
- Store FASTag details
- Fetch vehicle information
- Modify vehicle records
- Remove vehicle records
- Ensure Vehicle Numbers are unique
- Ensure FASTag IDs are unique

---

# Architecture

```
                Client
                   │
                   ▼
          VehicleController
                   │
                   ▼
            VehicleService
                   │
                   ▼
         VehicleRepository
                   │
                   ▼
              H2 Database
```

---

# Project Structure

```
vehicle-service
│
├── controller
│     VehicleController
│
├── service
│     VehicleService
│
├── repository
│     VehicleRepository
│
├── entity
│     Vehicle
│
├── dto
│     VehicleDTO
│
├── enums
│     VehicleType
│
├── advice
│     ErrorResponse
│     GlobalExceptionHandler
│
├── exception
│     DuplicateVehicleException
│     FastTagNotFoundException
│     VehicleNotFoundException
│
└── VehicleApiApplication
```

---

# Entity

## Vehicle

| Field | Type | Description |
|---------|------|-------------|
| id | Integer | Primary Key |
| vehicleNumber | String | Unique Vehicle Registration Number |
| ownerName | String | Name of the Vehicle Owner |
| vehicleType | Enum | CAR / BUS / TRUCK / BIKE |
| fastagId | String | Unique FASTag Identifier |

---

# DTO

## VehicleDTO

This DTO is used to accept incoming client requests.

```java
vehicleNumber

ownerName

vehicleType

fastagId
```

Validation covers:

- Vehicle Number Format
- Required Fields
- Owner Name Length
- Valid Vehicle Type
- Mandatory FASTag ID

---

# Vehicle Type

Supported vehicle categories:

```
CAR

BUS

TRUCK

BIKE
```

---

# Business Flow

```
Register Vehicle

       │

       ▼

Validate Request

       │

       ▼

Vehicle Number Exists?

       │

 ┌─────┴──────┐

 │            │

Yes          No

 │            │

 ▼            ▼

Throw      FASTag Exists?

Exception       │

         ┌─────┴─────┐

         │           │

       Yes          No

         │           │

         ▼           ▼

      Throw      Save Vehicle

      Exception      │

                     ▼

             Return Created
```

---

# REST APIs

## Register Vehicle

```
POST /api/v1/vehicles
```

### Request

```json
{
  "vehicleNumber":"TN38AB1234",
  "ownerName":"Kanagadharini.A",
  "vehicleType":"CAR",
  "fastagId":"FT1001"
}
```

### Success

```
201 CREATED
```

#### Example Output

<img width="1917" height="915" alt="image" src="https://github.com/user-attachments/assets/cdcd331a-b6fa-4b0c-9a5e-8969feefe29d" />

---

## Get Vehicle

```
GET /api/v1/vehicles/{vehicleNumber}
```

<img width="1917" height="873" alt="image" src="https://github.com/user-attachments/assets/2823893b-155f-4038-813e-efedc24fba0c" />

---

## Get All Vehicles

```
GET /api/v1/vehicles
```

<img width="1912" height="1017" alt="image" src="https://github.com/user-attachments/assets/69ace1c4-af9e-4d5d-95dc-def13c92fac5" />

---

## Update Vehicle

```
PUT /api/v1/vehicles/{id}
```

<img width="1917" height="925" alt="image" src="https://github.com/user-attachments/assets/3b18404b-942e-49ef-9501-367826131283" />

---

## Delete Vehicle

```
DELETE /api/v1/vehicles/{id}
```

<img width="1917" height="602" alt="image" src="https://github.com/user-attachments/assets/afef7c41-4539-48a3-9381-57a135e3cf14" />

---

# Validation

| Field | Validation |
|---------|------------|
| Vehicle Number | Required + Regular Expression |
| Owner Name | Minimum 3 Characters |
| Vehicle Type | Enum Validation |
| FASTag ID | Required |

Example

```
TN42IK2007
```

---

# Exception Handling

Global exception handling is implemented using

```
@RestControllerAdvice
```

Handled exceptions include:

- DuplicateVehicleException
- VehicleNotFoundException
- FastTagNotFoundException
- MethodArgumentNotValidException

---

# Error Response

Every exception returns a standardized response format.

```json
{
  "statusCode":400,
  "errorType":"Validation Failed",
  "errorMessage":"Vehicle already exists",
  "timestamp":"2026-07-18T12:00:00"
}
```

---

# Repository

The repository extends

```
JpaRepository<Vehicle,Integer>
```

Custom methods:

```java
existsByVehicleNumber()

existsByFastagId()

findByVehicleNumber()
```

---

# Logging

Application logging is implemented using SLF4J to monitor request flow and operations.

Examples:

- Vehicle Registration
- Fetch Vehicle Details
- Update Vehicle Information
- Delete Vehicle Record
- Duplicate Vehicle Detection
- Vehicle Not Found

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
8081
```

---

# Testing

The APIs can be tested using:

- Postman
- IntelliJ HTTP Client
- curl

---

# Used By

This service is utilized by:

- Toll Service

For:

- Vehicle Validation
- FASTag ID Retrieval

---

# Future Improvements

- Pagination Support
- Advanced Vehicle Search
- Swagger Documentation
- MySQL Integration
- Unit Testing
- Docker Support
- OpenFeign Client
- Eureka Service Discovery

---

# Author

**Kanagadharini.A**

B.E.Computer Science Enginering

Saveetha Engineering College

https://github.com/kanagadharini/SMART-TOLL-PLAZA-PROJECT
