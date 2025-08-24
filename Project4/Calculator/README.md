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
➕ Addition

➖ Subtraction

✖️ Multiplication

➗ Division

## ⚠️ Error Handling

Examples of error responses:

Empty List
{
  "message": "List must not be empty",
  "status": "BAD_REQUEST"
}



