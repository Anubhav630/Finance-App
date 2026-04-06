# Finance Data Processing and Access Control Backend

## 📌 Overview

This project is a backend system for a finance dashboard that manages financial records and enforces role-based access control.

It provides APIs for:
- User management
- Financial record management
- Dashboard analytics

The implementation focuses on clean architecture, maintainability, and correct handling of business logic, validation, and access control.

---

## 🧱 Architecture

The application follows a layered architecture:

Controller → Service → Repository → Database

- **Controller Layer**: Handles HTTP requests and responses  
- **Service Layer**: Contains business logic and transformations  
- **Repository Layer**: Handles database operations using JPA  
- **DTO Layer**: Separates API contracts from internal entities  

---

## ⚙️ Tech Stack

- Java  
- Spring Boot  
- Spring Data JPA  
- Spring Security (Basic Authentication)  
- MySQL  
- Lombok  
- Jakarta Validation  

---

## 👥 User Roles and Access Control

The system supports three roles:

### 👁️ Viewer
- Can only access dashboard APIs  
- Cannot access records or user management APIs  

### 📊 Analyst
- Can view financial records  
- Can access dashboard analytics  
- Cannot modify records  

### ⚙️ Admin
- Full access to system  
- Can create, update, delete records  
- Can manage users  

Access control is enforced using Spring Security with method-level annotations (`@PreAuthorize`).

---

## 🔐 Authentication

Basic authentication is used for simplicity.

### Default Credentials

| Role    | Username | Password     |
|--------|----------|-------------|
| Admin  | admin    | admin123     |
| Analyst| analyst  | analyst123   |
| Viewer | viewer   | viewer123    |

---

## 🎯 Role-Based API Behavior

### Viewer Access

GET /dashboard/summary ✅ Allowed
GET /records ❌ 403 Forbidden
POST /records ❌ 403 Forbidden


---

### Analyst Access

GET /records ✅ Allowed
GET /dashboard/summary ✅ Allowed
POST /records ❌ 403 Forbidden
DELETE /records/{id} ❌ 403 Forbidden


---

### Admin Access

POST /users ✅ Allowed
POST /records ✅ Allowed
PUT /records/{id} ✅ Allowed
DELETE /records/{id} ✅ Allowed
GET /dashboard/summary ✅ Allowed


---

## 📊 Features Implemented

- User and Role Management  
- Financial Records CRUD  
- Record Filtering (by type, category, date range)  
- Dashboard Summary APIs:
  - Total Income  
  - Total Expense  
  - Net Balance  
  - Category-wise totals  
  - Recent activity  
- Role-Based Access Control  
- Input Validation and Error Handling  
- Data Persistence using MySQL  

---

## 📬 API Endpoints

### User APIs
- `POST /users` → Create user  
- `GET /users` → Get all users  

### Record APIs
- `POST /records` → Create record  
- `GET /records` → Get all records  
- `PUT /records/{id}` → Update record  
- `DELETE /records/{id}` → Delete record  

### Filtering APIs
- `GET /records/type/{type}`  
- `GET /records/category/{category}`  
- `GET /records/date?start=YYYY-MM-DD&end=YYYY-MM-DD`  

### Dashboard APIs
- `GET /dashboard/summary`  
- `GET /dashboard/categories`  
- `GET /dashboard/recent`  

---

## 🧪 How to Test APIs

Use Postman with Basic Auth.

### Example Requests

#### Viewer

GET http://localhost:8080/dashboard/summary

Authorization: Basic viewer/viewer123


#### Analyst

GET http://localhost:8080/records

Authorization: Basic analyst/analyst123


#### Admin

POST http://localhost:8080/records

Authorization: Basic admin/admin123


---

## ⚙️ Setup Instructions

1. Clone the repository:
```bash
git clone https://github.com/your-username/your-repo.git
cd your-repo
Create MySQL database:

2. CREATE DATABASE finance_db;
  Update application.properties:
  spring.datasource.url=jdbc:mysql://localhost:3306/finance_db
  spring.datasource.username=your_username
  spring.datasource.password=your_password

3. Run the application:
  mvn spring-boot:run

4. Access APIs at:
  http://localhost:8080