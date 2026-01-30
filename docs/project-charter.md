# Project Charter – Tutor Booking System

## 1. Project Goal
The goal of this project is to design and implement a web-based system for booking tutoring sessions.
The application is developed as a pet project intended to demonstrate practical skills in:

- backend development using Java Spring Boot,
- frontend development using React,
- clean architecture and separation of concerns,
- domain-driven design fundamentals,
- REST API design and documentation.

The project focuses on correctness, clarity, and maintainability rather than feature richness.

---

## 2. MVP0 Scope
The MVP0 version delivers a complete end-to-end booking flow with minimal complexity.

The scope includes:
- user registration and authentication,
- two user roles: `STUDENT` and `TUTOR`,
- creation and management of tutor profiles,
- manual definition of tutor availability using time slots,
- booking individual tutoring sessions,
- viewing and cancelling existing bookings.

Features such as payments, reviews, or recurring availability are explicitly excluded from MVP0.

---

## 3. User Roles
The system supports the following user roles:

- **Guest** – can browse the list of tutors and view tutor profiles,
- **Student** – can book available time slots and cancel their own bookings,
- **Tutor** – can manage their public profile, define availability, and view bookings related to their time slots.

---

## 4. Key Assumptions
The following assumptions apply to MVP0:

- bookings are automatically confirmed upon creation,
- each time slot can be booked by at most one student,
- all business rules are enforced by the backend,
- the backend exposes a REST API consumed by a separate frontend application.

---

## 5. MVP0 Completion Criteria
MVP0 is considered complete when:

- a student can successfully book a tutoring session,
- both tutor and student can view their respective bookings,
- booking conflicts are correctly prevented,
- the application can be started locally using Docker Compose,
- the REST API is documented using Swagger / OpenAPI.
