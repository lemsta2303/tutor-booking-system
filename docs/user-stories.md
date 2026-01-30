# User Stories – MVP0

This document defines the user stories for the MVP0 version of the Tutor Booking System.
Each story focuses on delivering a minimal yet complete booking flow.

---

## US-01 Register account
**As a:** Guest  
**I want to:** register an account with email and password  
**So that:** I can use the tutoring booking system

**Acceptance Criteria:**
- user provides email, password, and role (STUDENT or TUTOR)
- email must be unique
- password is securely stored
- user can log in after successful registration

---

## US-02 Log in
**As a:** Registered user  
**I want to:** log in using my credentials  
**So that:** I can access features assigned to my role

**Acceptance Criteria:**
- valid credentials return a JWT token
- invalid credentials are rejected
- authenticated user can access protected endpoints

---

## US-03 Create tutor profile
**As a:** Tutor  
**I want to:** create and update my tutor profile  
**So that:** students can find and book sessions with me

**Acceptance Criteria:**
- only users with role TUTOR can create a profile
- profile contains description, subject, and hourly rate
- profile is visible in the tutor listing

---

## US-04 Add available time slot
**As a:** Tutor  
**I want to:** add available time slots  
**So that:** students can book tutoring sessions with me

**Acceptance Criteria:**
- start time must be before end time
- time slot is assigned to the authenticated tutor
- duplicate time slots are not allowed
- slot becomes visible to students

---

## US-05 Book tutoring session
**As a:** Student  
**I want to:** book an available time slot  
**So that:** I can schedule a tutoring session

**Acceptance Criteria:**
- only users with role STUDENT can book slots
- slot must exist and be available
- booking is created with status CONFIRMED
- booking operation is transactional
- double booking is prevented

---

## US-06 View bookings
**As a:** User  
**I want to:** view my bookings  
**So that:** I can manage my tutoring sessions

**Acceptance Criteria:**
- student sees bookings they created
- tutor sees bookings related to their time slots
- booking list includes slot time and status

---

## US-07 Cancel booking
**As a:** Student or Tutor  
**I want to:** cancel an existing booking  
**So that:** the time slot becomes available again

**Acceptance Criteria:**
- student can cancel their own bookings
- tutor can cancel bookings for their slots
- booking status is changed to CANCELLED
- cancelled booking does not block the time slot

---

## Notes
- All user stories are scoped strictly to MVP0.
- Payment, reviews, notifications, and approval workflows are excluded.
