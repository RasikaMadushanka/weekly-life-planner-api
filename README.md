# AI-Driven Health & Productivity API

A modern **Java backend system** that analyzes user physical metrics and automatically generates a **7-day personalized plan for nutrition, fitness, and daily productivity tasks**.

## 🚀 Overview

The system calculates a user's **BMI** and generates a weekly roadmap including **meals, exercises, and productivity tasks** based on the user's health category.

## 🛠 Tech Stack

* **Language:** Java (JDK 25)
* **Framework:** Spring Boot 3.x
* **ORM:** Hibernate 6+ (JPA)
* **Database:** MySQL
* **Architecture:** Layered Architecture (Controller → Service → Repository)
* **Data Mapping:** ModelMapper
* **Build Tool:** Maven

## 🏗 System Architecture

The project follows a **clean layered architecture**:

### API Layer (Controllers)

Handles REST endpoints for:

* User registration
* Manual data logging
* Report generation

### Business Logic Layer (Services)

Contains the core system logic:

* BMI calculation engine
* Auto-assignment loop that generates a full **7-day schedule**

### Data Access Layer (Repositories)

Hibernate/JPA repositories that manage communication with the **MySQL database**.

### Domain Layer (Entities)

Entity classes representing the database tables and relationships.

## 🗄 Database Design

The system uses a **One-to-Many (1:N) relationship model**.

**User (Parent Entity)**

* Stores user profile and health metrics

**Schedules (Child Entities)**

* Meals
* Exercises
* Daily tasks

### Cascade Management

Using **Hibernate CascadeType.ALL**, when a user profile is deleted, all related weekly schedules are automatically removed.

## ⚙ Core Workflow

1. **User Registration**
   User submits physical metrics such as weight and height.

2. **BMI Calculation**
   The system calculates BMI and determines the health category.

3. **Automatic Schedule Generation**
   The system loops through **7 days** and generates:

   * Breakfast, lunch, and dinner plans
   * Exercise routines
   * Daily productivity tasks

4. **Data Persistence**
   Hibernate maps the generated objects into **MySQL tables**.

## 📌 Features

* Automatic **BMI-based health analysis**
* Personalized **7-day health & productivity plan**
* Clean **layered architecture**
* Secure and scalable **Spring Boot backend**
* Automatic **database cascade management**

---

⭐ Built with **Java, Spring Boot, and MySQL** to explore intelligent backend systems for health and productivity automation.
