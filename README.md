# Tutor Booking System

## Status

The project is currently under development.

## Overview

A full-stack web application that allows students to reserve time slots managed by tutors.

The backend is built with Spring Boot and uses PostgreSQL for data persistence.  
The frontend is developed in React and communicates with the backend via a REST API.

Core features include secure JWT-based authentication, role-based access control (Student / Tutor), tutor profile management, and structured time slot creation and booking.


## Tech Stack

### Backend

-   Java
-   Maven
-   Spring Boot
-   Spring Security
-   JWT
-   PostgreSQL

### Frontend

-   React
-   TypeScript
-   Vite
-   TanStack Query
-   Redux Toolkit
-   Material UI
-   Axios

## Run Locally

### 1. Database (PostgreSQL)

Make sure PostgreSQL is installed and running locally.

Create a database: `tutor_reservation_system`

Then create a file: `backend/src/main/resources/application-local.properties`

Example configuration:
```
spring.application.name=backend

# PostgreSQL connection

spring.datasource.url=jdbc:postgresql://localhost:5432/tutor_reservation_system
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# BCrypt strength

security.password.bcrypt.strength=12
```
------------------------------------------------------------------------

### 2. Start Backend

`cd backend`
`./mvnw spring-boot:run`

Backend runs on: http://localhost:8080

------------------------------------------------------------------------

### 3. Start Frontend

`cd web-app`
`npm install`
`npm run dev`

Frontend runs on: http://localhost:5173

