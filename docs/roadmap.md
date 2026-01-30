# Roadmap

This roadmap describes the planned, incremental development of the Tutor Booking System.
Each milestone introduces new functionality while building on the previous stage.

---

## MVP0 – Core Booking Flow
Goal: Deliver a complete, minimal end-to-end booking experience.

Scope:
- user registration and authentication (JWT),
- roles: STUDENT and TUTOR,
- tutor profile creation and management,
- manual definition of availability using time slots,
- booking individual time slots,
- cancelling bookings,
- prevention of booking conflicts,
- basic API documentation (Swagger),
- local development setup using Docker Compose.

---

## MVP1 – Usability and Control
Goal: Improve usability and introduce additional business logic.

Scope:
- booking approval workflow (PENDING → CONFIRMED),
- tutor ability to accept or reject bookings,
- basic filtering and sorting of tutors,
- in-app notifications for booking status changes.

---

## MVP2 – Quality and Scheduling
Goal: Improve user trust, scheduling flexibility, and technical quality.

Scope:
- reviews and ratings after completed sessions,
- availability rules (recurring schedules with exceptions),
- automated tests (unit + integration),
- CI pipeline for build and test validation.

---

## MVP3 – Monetization and Integration
Goal: Extend the system with real-world integrations.

Scope:
- payment processing (e.g. Stripe),
- tutoring session packages or prepaid hours,
- calendar integration (ICS export / Google Calendar sync).
