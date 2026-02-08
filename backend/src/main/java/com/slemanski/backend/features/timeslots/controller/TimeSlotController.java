package com.slemanski.backend.features.timeslots.controller;

import com.slemanski.backend.features.timeslots.dto.TimeSlotCreateDto;
import com.slemanski.backend.features.timeslots.dto.TimeSlotSummaryDto;
import com.slemanski.backend.features.timeslots.service.TimeSlotService;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    TimeSlotController(TimeSlotService service) {
        this.timeSlotService = service;
    }

    @PostMapping("/slots")
    public ResponseEntity<Void> createTimeSlot(
            @RequestBody @Valid TimeSlotCreateDto dto,
            @AuthenticationPrincipal MyUserDetails me
    ) {
        this.timeSlotService.createTimeSlot(dto, me.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/slots/{timeSlotId}")
    public ResponseEntity<Void> deleteTimeSlot(
            @PathVariable long timeSlotId,
            @AuthenticationPrincipal MyUserDetails me
    ) {
        this.timeSlotService.deleteTimeSlot(timeSlotId, me.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{tutorId}/slots")
    public ResponseEntity<List<TimeSlotSummaryDto>> getTimeSlotsOfTutor(
            @PathVariable long tutorId
    ) {
        return ResponseEntity.ok(
                this.timeSlotService.getTimeSlotsOfTutor(tutorId)
                    .stream()
                    .map(TimeSlotSummaryDto::from)
                    .toList()
        );
    }

}
