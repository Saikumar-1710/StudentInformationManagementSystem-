# 📘 Student Information Management System

## 💡 Project Overview
The **Student Information Management System** is a full-stack Java web application designed using the **MVC (Model-View-Controller)** architectural pattern. This system enables secure student data management, including user registration, login, profile updates, and account management.

---

## 🧩 Architecture: MVC Pattern

### 1️⃣ Model Layer
Responsible for the data structure and database interaction.

- **Database:** SQL*Plus
- **Tables Created:**
  - `User_account`
  - `Student`
- **Purpose:** Define schema and manage data storage

---

### 2️⃣ View Layer
Handles the user interface.

- **Technologies:** HTML, JSP
- **Pages:**
  - `login.jsp` – User login
  - `register.html` – Registration page
  - `home.jsp` – Main dashboard with buttons:
    - `Get User Details`
    - `Get All Users`
    - `Update Values`
    - `Delete Record`
    - `Student Details`
  - `success.jsp` – Operation success page
  - `error.jsp` – Operation failure page
  - `update.jsp` – Update form
  - `account_details.jsp` – Shows account info

---

### 3️⃣ Controller Layer
Manages the logic and user request handling.

- **Servlet Class:**
  - Routes requests using a `switch-case` mechanism
  - Connects frontend to backend
- **POJO Classes:**
  - Mirrors database tables with:
    - Constructors
    - Getters/Setters
- **DAO Class:**
  - Contains:
    - Database connection
    - PreparedStatements
    - CRUD methods for user data
- **HttpSessionListener:**
  - Manages session lifecycle
  - Provides optimized DB connection
  - Avoids execution delay during multiple user access

---

## 🛠️ Technologies Used

| Technology | Description                         |
|------------|-------------------------------------|
| Java       | Backend logic                       |
| JSP/HTML   | Frontend development                |
| Servlet    | Request handling and routing        |
| JDBC       | Database connectivity and queries   |
| SQL*Plus   | Database schema creation            |
| MVC Pattern| Application architecture separation |

---
