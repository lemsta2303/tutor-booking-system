package com.slemanski.backend.features.timeslots.controller;

import com.slemanski.backend.features.timeslots.dto.TimeSlotCreateDto;
import com.slemanski.backend.features.timeslots.dto.TimeSlotDeleteDto;
import com.slemanski.backend.features.timeslots.service.TimeSlotService;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutors/slots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    TimeSlotController(TimeSlotService service) {
        this.timeSlotService = service;
    }

    @PostMapping
    public ResponseEntity<Void> createTimeSlot(
            @RequestBody @Valid TimeSlotCreateDto dto,
            @AuthenticationPrincipal MyUserDetails me
    ) {
        this.timeSlotService.createTimeSlot(dto, me.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @RequestMapping("/{timeSlotId}")
    public ResponseEntity<Void> deleteTimeSlot(
            @PathVariable long timeSlotId,
            @AuthenticationPrincipal MyUserDetails me
    ) {
        this.timeSlotService.deleteTimeSlot(timeSlotId, me.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
