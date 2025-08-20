# School Management API

![Java](https://img.shields.io/badge/Java-17-orange) 
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen) 
![License: MIT](https://img.shields.io/badge/License-MIT-blue)

## 📑 Table of Contents
- [Overview](#-overview)
- [Technologies](#-technologies)
- [Features](#-features)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
  - [Students](#students)
  - [Professors](#professors)
  - [Classes](#classes)
- [Testing](#-testing)
- [License](#-license)
- [Author](#-author)

## 📌 Overview
The **School Management API** is a RESTful web service built with **Spring Boot**.  
It allows managing students, professors, and classes, with additional functionality for **enrolling students into classes** and **assigning professors to classes**.  
The project uses **DTO mapping**, **Jakarta Validation**, and includes **data initialization** on startup with an in-memory **H2 database**.

---

## ⚙️ Technologies
- Java 17+
- Spring Boot 3
- Spring Data JPA & Hibernate
- H2 Database (in-memory for development)
- Lombok
- Jakarta Validation

---

## ✨ Features
- 👩‍🎓 **Students**
  - Create, Read, Update, Delete
  - Enroll/Unenroll in classes
- 👨‍🏫 **Professors**
  - Create, Read, Update, Delete
  - Assign/Unassign to classes
- 📚 **Classes**
  - Create, Read, Delete
  - Linked with students & professors
- 🛠 **Extras**
  - DTO mapping for cleaner responses
  - Validation with Jakarta
  - Seed data via `DataInitializer`

  ## 🚀 Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.x

  ### Installation & Run
1. Clone the repository:
   ```bash
   git clone https://github.com/manoskoum/Spring-Boot.git

2.Navigate to the project:
   cd Spring-Boot/Project2/school-managment-api

3.Run the application:   
   mvn spring-boot:run

 The API will start on:
👉 http://localhost:9591  

## 📡 API Endpoints

### 👩‍🎓 Students
| Method | Endpoint                                | Description           |
|-------:|-----------------------------------------|-----------------------|
| GET    | `/api/students`                         | Get all students      |
| GET    | `/api/students/{id}`                    | Get student by ID     |
| POST   | `/api/students`                         | Create student        |
| PUT    | `/api/students/{id}`                    | Update student        |
| DELETE | `/api/students/{id}`                    | Delete student        |
| POST   | `/enroll/{studentId}/classes/{classId}` | Enroll  to class      |
| DELETE | `/enroll/{studentId}/classes/{classId}` | Unenroll from class   |   

### 👨‍🏫 Professors
| Method | Endpoint                                             | Description               |
|-------:|------------------------------------------------------|---------------------------|
| GET    | `/api/professors`                                    | Get all professors        |
| GET    | `/api/professors/{id}`                               | Get professor by ID       |
| POST   | `/api/professors`                                    | Create professor          |
| PUT    | `/api/professors/{id}`                               | Update professor          |
| DELETE | `/api/professors/{id}`                               | Delete professor          |
| POST   | `/enroll/professors/{professorId}/classes/{classId}` | Assign to class           |
| DELETE | `/enroll/professors/{professorId}/classes/{classId}` | Unassign from class       |

### 📚 Classes
| Method | Endpoint                         | Description     |
|-------:|----------------------------------|-----------------|
| GET    | `/api/school/classes`            | Get all classes |
| POST   | `/api/school/classes`            | Create class    |
| DELETE | `/api/admin/school/classes/{id}` | Delete class    |

## 🧪 Testing

Access the H2 database console at:
👉 http://localhost:9591/h2-console

Default JDBC URL: jdbc:h2:mem:testdb

testdb

## 📄 License

This project is licensed under the MIT License.
Feel free to use, modify, and distribute.

## 👨‍💻 Author

Developed by Emmanouil Koumentakis
