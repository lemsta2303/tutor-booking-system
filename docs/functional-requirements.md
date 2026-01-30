# Functional Requirements – MVP0

This document defines the functional requirements for the Tutor Booking System (MVP0).
Each requirement is intentionally scoped to support an end-to-end booking flow with minimal complexity.

---

## FR-01 User registration
**Actor:** Guest

**Description:**  
A guest user can create an account by providing an email address, password, and selecting a role.

**Inputs:**
- email
- password
- role: `STUDENT` or `TUTOR`

**Preconditions:**
- The email is not already registered.

**Postconditions:**
- A new `User` entity is created.
- The password is stored as a secure hash.
- The user can authenticate via login.

**Validation & Errors:**
- `400 BAD REQUEST` – invalid email format, weak/empty password, invalid role
- `409 CONFLICT` – email already exists

---

## FR-02 User login
**Actor:** User

**Description:**  
A registered user can authenticate using email and password.

**Inputs:**
- email
- password

**Postconditions:**
- The system issues an access token (JWT) and (optionally) a refresh token.

**Validation & Errors:**
- `400 BAD REQUEST` – missing fields
- `401 UNAUTHORIZED` – incorrect credentials

---

## FR-03 Tutor profile management
**Actor:** Tutor (authenticated)

**Description:**  
A tutor can create and update a public profile that will be visible in the tutor catalog.

**Profile fields (MVP0 minimal):**
- displayName (or name)
- description (bio)
- subject (string for MVP0)
- hourlyRate (number)

**Preconditions:**
- The authenticated user has role `TUTOR`.

**Postconditions:**
- Tutor profile is created/updated and becomes visible on tutor listing and tutor details.

**Validation & Errors:**
- `401 UNAUTHORIZED` – not authenticated
- `403 FORBIDDEN` – user is not a tutor
- `400 BAD REQUEST` – invalid hourlyRate, empty required fields

---

## FR-04 Tutor listing
**Actor:** Guest

**Description:**  
A guest can view a list of tutors to choose from.

**Behavior (MVP0):**
- Returns all tutors that have a profile.
- Shows basic information: name, subject, hourlyRate.

**Postconditions:**
- None (read-only operation).

**Errors:**
- `200 OK` – always returns a list (may be empty)

---

## FR-05 Add availability time slot
**Actor:** Tutor (authenticated)

**Description:**  
A tutor can create an available time slot that students can book.

**Inputs:**
- startTime (datetime)
- endTime (datetime)

**Preconditions:**
- User is authenticated and has role `TUTOR`.
- startTime < endTime

**Postconditions:**
- A `TimeSlot` is created and is available for booking.

**Validation & Errors:**
- `401 UNAUTHORIZED` – not authenticated
- `403 FORBIDDEN` – user is not a tutor
- `400 BAD REQUEST` – invalid time range (end <= start), invalid datetime
- `409 CONFLICT` – duplicate slot for the same tutor (same start/end)

---

## FR-06 Remove availability time slot
**Actor:** Tutor (authenticated)

**Description:**  
A tutor can delete an existing time slot, but only if it is not currently booked.

**Preconditions:**
- The slot belongs to the authenticated tutor.
- The slot has no active booking (`CONFIRMED`).

**Postconditions:**
- The `TimeSlot` is removed from the system.

**Validation & Errors:**
- `401 UNAUTHORIZED` – not authenticated
- `403 FORBIDDEN` – slot does not belong to the tutor
- `404 NOT FOUND` – slot does not exist
- `409 CONFLICT` – slot is booked and cannot be deleted

---

## FR-07 Book time slot
**Actor:** Student (authenticated)

**Description:**  
A student can book a selected available time slot.

**Inputs:**
- timeSlotId

**Preconditions:**
- User is authenticated and has role `STUDENT`.
- The referenced time slot exists.
- The time slot is not already booked.

**Postconditions:**
- A `Booking` entity is created with status `CONFIRMED`.
- The time slot becomes unavailable for other students.
- The operation is executed transactionally.

**Validation & Errors:**
- `401 UNAUTHORIZED` – not authenticated
- `403 FORBIDDEN` – user is not a student
- `404 NOT FOUND` – time slot does not exist
- `409 CONFLICT` – time slot already booked

---

## FR-08 Prevent booking conflicts
**Actor:** System

**Description:**  
The system must prevent double booking of the same time slot.

**Rules (MVP0):**
- A `TimeSlot` can have at most one active booking with status `CONFIRMED`.
- If two students attempt to book the same slot concurrently, only one can succeed.

**Expected behavior:**
- The losing request returns `409 CONFLICT`.

---

## FR-09 View student bookings
**Actor:** Student (authenticated)

**Description:**  
A student can view a list of their own bookings.

**Behavior (MVP0):**
- Returns bookings where `studentId = currentUserId`.
- Includes slot start/end time and booking status.

**Validation & Errors:**
- `401 UNAUTHORIZED` – not authenticated
- `403 FORBIDDEN` – user is not a student

---

## FR-10 View tutor bookings
**Actor:** Tutor (authenticated)

**Description:**  
A tutor can view bookings related to their time slots.

**Behavior (MVP0):**
- Returns bookings for time slots where `timeSlot.tutorId = currentUserId`.
- Includes student identifier (basic) + slot times + booking status.

**Validation & Errors:**
- `401 UNAUTHORIZED` – not authenticated
- `403 FORBIDDEN` – user is not a tutor

---

## FR-11 Cancel booking
**Actor:** Student or Tutor (authenticated)

**Description:**  
A booking can be cancelled either by the student who created it or by the tutor who owns the related time slot.

**Preconditions:**
- Booking exists.
- Actor is related to the booking:
  - student: booking.studentId = currentUserId
  - tutor: booking.timeSlot.tutorId = currentUserId

**Postconditions:**
- Booking status is set to `CANCELLED` (soft-cancel; booking is not deleted).
- The time slot becomes available again for future bookings.

**Validation & Errors:**
- `401 UNAUTHORIZED` – not authenticated
- `403 FORBIDDEN` – actor not related to booking
- `404 NOT FOUND` – booking does not exist
- `409 CONFLICT` – booking already cancelled (optional handling)

---

## Notes (MVP0 scope boundaries)
- No payment processing in MVP0.
- No reviews/ratings in MVP0.
- No recurring availability rules in MVP0 (only manual slots).
- No approval workflow in MVP0 (bookings are auto-confirmed).
