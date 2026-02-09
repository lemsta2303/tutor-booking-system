package com.slemanski.backend.features.booking.service;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.auth.model.Role;
import com.slemanski.backend.features.booking.model.Booking;
import com.slemanski.backend.features.booking.repository.BookingRepository;
import com.slemanski.backend.features.students.model.StudentProfile;
import com.slemanski.backend.features.timeslots.model.TimeSlot;
import com.slemanski.backend.features.timeslots.repository.TimeSlotRepository;
import com.slemanski.backend.shared.exception.exception.AccessDeniedException;
import com.slemanski.backend.shared.exception.exception.ConflictException;
import com.slemanski.backend.shared.exception.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    TimeSlotRepository timeSlotRepository;
    BookingRepository bookingRepository;

    public BookingService(TimeSlotRepository timeSlotRepository, BookingRepository bookingRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void bookTimeSlot(MyUser clientWhoBooks, long timeSlotId) {
        StudentProfile studentProfile = clientWhoBooks.getStudentProfile();
        if(studentProfile == null) {
            throw new AccessDeniedException("User is not a student.");
        }

        Optional<TimeSlot> timeSlot = timeSlotRepository.findById(timeSlotId);
        if(timeSlot.isEmpty()) {
            throw new ResourceNotFoundException("Slot doest not exist.");
        }

        Optional<Booking> bookingExistedBefore = Optional.ofNullable(bookingRepository.getByTimeSlot(timeSlot.get()));
        if(bookingExistedBefore.isPresent()) {
            throw new ConflictException("Slot already booked.");
        }

        Booking newBooking = new Booking();
        newBooking.setStudentProfile(studentProfile);
        newBooking.setTimeSlot(timeSlot.get());
        this.bookingRepository.save(newBooking);

    }

    public List<Booking> getUserBooking(MyUser user) {
        StudentProfile studentProfile = user.getStudentProfile();
        if(studentProfile == null) {
            throw new AccessDeniedException("User is not a student.");
        }

        return studentProfile.getBookings();
    }

    public void deleteBooking(MyUser user, long bookingId) {
        Optional<Booking> bookingOptional = bookingRepository.findById(bookingId);
        if(bookingOptional.isEmpty()) {
            throw new ResourceNotFoundException("Booking with given id not found.");
        }
        Booking booking = bookingOptional.get();

        if(user.getRole() == Role.STUDENT && booking.getStudentProfile().getUser().getId() != user.getId()) {
            throw new AccessDeniedException("Student is not authorize to delete other student's resource.");
        }
        if(user.getRole() == Role.TUTOR && booking.getTimeSlot().getTutorProfile().getUser().getId() != user.getId()) {
            throw new AccessDeniedException("Tutor is not authorize to delete other tutor's resource.");
        }
        bookingRepository.deleteById(bookingId);

    }
}
