# Domain Model – MVP0

This document describes the core domain model for the Tutor Booking System (MVP0).
The model is intentionally minimal and focuses on supporting the basic booking flow.

---

## User
Represents an authenticated system user.

Fields:
- id
- email (unique)
- passwordHash
- role (STUDENT, TUTOR)
- createdAt

Notes:
- Email must be unique across all users.
- Role determines access to system features.

---

## TutorProfile
Represents additional data for users with the TUTOR role.

Fields:
- id
- userId (reference to User)
- description
- subject
- hourlyRate

Notes:
- Each tutor has exactly one TutorProfile.
- Profile data is publicly visible.

---

## TimeSlot
Represents a single available time window for a tutoring session.

Fields:
- id
- tutorId (reference to User with role TUTOR)
- startTime
- endTime

Notes:
- A TimeSlot belongs to exactly one tutor.
- A tutor cannot have overlapping or duplicate time slots.
- Booking state is derived from related Booking entities.

---

## Booking
Represents a reservation of a TimeSlot by a student.

Fields:
- id
- studentId (reference to User with role STUDENT)
- timeSlotId (reference to TimeSlot)
- status (CONFIRMED, CANCELLED)
- createdAt

Notes:
- A TimeSlot can have at most one active (CONFIRMED) booking.
- Tutor information is derived through the related TimeSlot.
- Booking operations are transactional.

---

## Domain Constraints (MVP0)
- User.email must be unique.
- TutorProfile.userId must be unique.
- TimeSlot must not overlap with another TimeSlot of the same tutor.
- Only one CONFIRMED booking is allowed per TimeSlot.
