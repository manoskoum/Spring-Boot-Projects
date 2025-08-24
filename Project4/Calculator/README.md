## 🧮 Calculator REST API
A simple Spring Boot REST API that performs basic arithmetic operations:

- ➕ Addition

- ➖ Subtraction

- ✖️ Multiplication

- ➗ Division

It also includes global exception handling with meaningful error messages for invalid inputs, division by zero, and missing resources.

## 🚀 Features

-REST endpoints for arithmetic operations

-Input validation using @Validated and javax.validation annotations

-Custom exceptions (ResourceNotFoundException)

-Centralized error handling via GlobalExceptionHandler

-JSON error responses with HTTP status codes

## 🛠️ Technologies Used

Java 17+

Spring Boot 3+

Spring Web / Spring MVC

Spring Validation (Jakarta Validation API)

Maven (build tool)

## 📡 API Endpoints
### ➕ Addition
`GET /add?nums=10&nums=5&nums=3`

**Response:**
`18.0`

### ➖ Subtraction
`GET /sub?nums=10&nums=4`

**Response:**
`6.0`

### ✖️ Multiplication
`GET /mult?nums=2&nums=3&nums=4`

**Response:**
`24.0`

### ➗ Division
`GET /dia?nums=20&nums=4`

**Response:**
`5.0`

## ⚠️ Error Handling

Examples of error responses:

**Empty List**

'{ "message": "List must not be empty",
  "status": "BAD_REQUEST" }'

**Division by Zero**
```json
{
  "message": "Division by zero is not allowed",
  "status": "BAD_REQUEST"
}

**Unexpected Error**
{
  "message": "Unexpected error",
  "status": "INTERNAL_SERVER_ERROR"
}



