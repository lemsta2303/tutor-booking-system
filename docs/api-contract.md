# API Contract – MVP0

This document describes the REST API for the Tutor Booking System (MVP0).
The API is designed according to a backend-first approach and is intended
to be consumed by a React frontend application.

---

## Authentication

### POST /api/auth/register
Actor: Guest

Description:
Registers a new user account with a selected role.

Allowed roles: STUDENT, TUTOR

Request body (JSON):
{
  "email": "user@example.com",
  "password": "secret123",
  "role": "TUTOR"
}

Behavior:
- creates a new user account
- stores the password as a secure hash
- assigns the selected role to the user

Responses:
- 201 CREATED – account created successfully
- 400 BAD REQUEST – validation error
- 409 CONFLICT – email already exists

---

### POST /api/auth/login
Actor: User

Description:
Authenticates a user and returns JWT tokens.

Request body (JSON):
{
  "email": "user@example.com",
  "password": "secret123"
}

Response body (JSON):
{
  "accessToken": "jwt-access-token",
  "refreshToken": "jwt-refresh-token"
}

Behavior:
- verifies credentials
- issues access and refresh tokens

Responses:
- 200 OK – login successful
- 401 UNAUTHORIZED – invalid credentials

---

## Tutors

### GET /api/tutors
Actor: Guest

Description:
Returns a list of all tutors available in the system.

Behavior:
- returns basic tutor profile data
- used to display the tutor catalog

---

### GET /api/tutors/{id}
Actor: Guest

Description:
Returns detailed information about a specific tutor,
including available (not booked) time slots.

Responses:
- 200 OK – tutor data returned
- 404 NOT FOUND – tutor does not exist

---

## Availability (Time Slots)

### POST /api/tutors/slots
Actor: Tutor (authenticated)

Description:
Creates a new available time slot for the authenticated tutor.

Request body (JSON):
{
  "startTime": "2026-02-01T16:00",
  "endTime": "2026-02-01T17:00"
}

Behavior:
- assigns the slot to the authenticated tutor
- marks the slot as available

Responses:
- 201 CREATED – slot created
- 400 BAD REQUEST – invalid time range
- 401 UNAUTHORIZED – user is not a tutor

---

### DELETE /api/tutors/slots/{id}
Actor: Tutor (authenticated)

Description:
Deletes an existing available time slot.

Behavior:
- only the slot owner can delete it
- slot must not be booked

Responses:
- 204 NO CONTENT – slot deleted
- 403 FORBIDDEN – user is not the owner
- 409 CONFLICT – slot already booked
- 404 NOT FOUND – slot does not exist

---

## Bookings

### POST /api/bookings
Actor: Student (authenticated)

Description:
Books an available time slot with a tutor.

Request body (JSON):
{
  "timeSlotId": 42
}

Behavior:
- creates a booking with status CONFIRMED
- marks the slot as booked
- operation is transactional

Responses:
- 201 CREATED – booking created
- 401 UNAUTHORIZED – user is not a student
- 409 CONFLICT – slot already booked
- 404 NOT FOUND – slot does not exist

---

### GET /api/bookings/me
Actor: User (authenticated)

Description:
Returns bookings related to the authenticated user.

Behavior:
- student receives bookings they created
- tutor receives bookings for their slots

Responses:
- 200 OK – list of bookings

---

### DELETE /api/bookings/{id}
Actor: Student or Tutor (authenticated)

Description:
Cancels an existing booking.

Behavior:
- student can cancel their own booking
- tutor can cancel bookings related to their slots
- booking status is set to CANCELLED
- the slot becomes available again

Responses:
- 204 NO CONTENT – booking cancelled
- 403 FORBIDDEN – user not related to the booking
- 404 NOT FOUND – booking does not exist

---

## Security Notes
- All endpoints (except authentication and public tutor endpoints) require JWT authentication
- Role-based access control is enforced
- Booking creation is transactional and prevents race conditions
