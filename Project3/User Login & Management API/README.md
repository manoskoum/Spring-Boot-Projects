# 🔐 User Login & Management API

![Java](https://img.shields.io/badge/Java-17-orange) 
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen) 
![License: MIT](https://img.shields.io/badge/License-MIT-blue)

---

## 📑 Table of Contents
- [Overview](#-overview)
- [Technologies](#-technologies)
- [Features](#-features)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
  - [Authentication](#authentication)
  - [Users](#users)
- [Testing](#-testing)
- [License](#-license)
- [Author](#-author)

---

## 📌 Overview
The **User Login & Management API** is a RESTful web service built with **Spring Boot**.  
It provides endpoints for **user registration**, **login (authentication)**, and **user management (CRUD operations)**.  
This project uses **Spring Data JPA**, **Hibernate**, and an **H2 in-memory database** for development and testing.  

---

## ⚙️ Technologies
- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database

---

## ✨ Features
- 🔐 **Authentication**
  - User Registration
  - User Login
- 👥 **User Management**
  - Create, Read, Update, Delete Users
- 🛠 **Extras**
  - DTOs for request/response
  - In-memory H2 database for testing

---

## 🚀 Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.x

### Installation & Run
1. Clone the repository:
   ```bash
   git clone https://github.com/manoskoum/Spring-Boot.git

2. Run the application:
   ```bash
   cd Spring-Boot/Project2/userloginform

3. Run the application:
   ```bash
   mvn spring-boot:run

The API will start on: 👉 http://localhost:8585 

## 🧪 Testing

Access the H2 database console at: 👉 http://localhost:8585/h2-console

Default JDBC URL: jdbc:h2:mem:testdb

## 📡 API Endpoints

🔐 Authentication

### 🔐 Authentication
| Method | Endpoint        | Description         |
|--------|-----------------|---------------------|
| POST   | `/api/register` | Register new user   |
| POST   | `/api/login`    | Login existing user |

### 👥 Users
| Method | Endpoint           | Description           |
|--------|-------------------|-----------------------|
| GET    | `/api/users`      | Get all users         |
| GET    | `/api/users/{id}` | Get user by ID        |
| POST   | `/api/users`      | Create new user       |
| PUT    | `/api/users/{id}` | Update existing user  |
| DELETE | `/api/users/{id}` | Delete user           |



## 📄 License

This project is licensed under the MIT License.
Feel free to use, modify, and distribute.

## 👨‍💻 Author

Developed by Emmanouil Koumentakis
