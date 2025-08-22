# Student Management API

RESTful API for managing students (CRUD, search, pagination & sorting).

## 🔧 Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL 8+
- Jakarta Bean Validation
- Lombok
- Maven

## 🏗 Architecture
- **Controller layer**: HTTP routing.
- **Service layer**: business logic & transactions.
- **Repository layer**: DB access with Spring Data JPA.
- **DTOs**: `StudentRequestDTO` / `StudentResponseDTO`.
- **Global Exception Handling**: consistent error responses.
- **Mapper**: `StudentMapper` for DTO ↔ Entity.

## 🗃️ MySQL Setup (run once)
 ```sql
CREATE DATABASE IF NOT EXISTS tododb
   ```

  
## 🚀 Run

git clone https://github.com/manoskoum/Spring-Boot.git
cd Spring-Boot/Project1/Student\ Management\ API
mvn clean install
mvn spring-boot:run


📚 API Endpoints
GET /api/students – list students (paginated)

GET /api/students/{id} – get student by ID

GET /api/students/search/{lastName} – search by last name (contains, case-insensitive)

POST /api/students – create student

PUT /api/students/{id} – update student

DELETE /api/students/{id} – delete student

📨 Request Examples

Create (POST)

{
 "firstname": "Manolis",
 "lastname": "Koumentakis",
 "age": 27
}

Update (PUT)

{
  "firstName": "Maria",
  "lastName": "Kallergi",
  "age": 22
}

❗ Error Example

{
  "message": "Student not found with id: 99",
  "status": "NOT_FOUND"
}












