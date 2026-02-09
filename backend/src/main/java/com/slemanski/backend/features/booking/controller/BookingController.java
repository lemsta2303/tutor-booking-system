package com.slemanski.backend.features.booking.controller;

import com.slemanski.backend.features.booking.dto.BookTimeSlotDto;
import com.slemanski.backend.features.booking.dto.BookingSummaryDto;
import com.slemanski.backend.features.booking.model.Booking;
import com.slemanski.backend.features.booking.service.BookingService;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Void> bookTimeSlot(
            @RequestBody BookTimeSlotDto dto,
            @AuthenticationPrincipal MyUserDetails me
            ) {
        this.bookingService.bookTimeSlot(me.getUser(), dto.timeSlotId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<BookingSummaryDto>> getMyBookings(
            @AuthenticationPrincipal MyUserDetails me
    ) {
        List<Booking> myBookings = bookingService.getUserBooking(me.getUser());

        return ResponseEntity.ok().body(
                myBookings.stream()
                        .map(BookingSummaryDto::from)
                        .toList()
        );
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteBooking(
            @PathVariable long bookingId,
            @AuthenticationPrincipal MyUserDetails me
    ) {
        bookingService.deleteBooking(me.getUser(), bookingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
