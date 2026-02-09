package com.slemanski.backend.features.tutors.controller;

import com.slemanski.backend.features.tutors.dto.TutorProfileDetailsDto;
import com.slemanski.backend.features.tutors.dto.TutorProfileSummaryDto;
import com.slemanski.backend.features.tutors.dto.TutorProfileUpdateDto;
import com.slemanski.backend.features.tutors.model.TutorProfile;
import com.slemanski.backend.features.tutors.service.TutorProfileService;
import com.slemanski.backend.infrastructure.security.user.MyUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tutors")
public class TutorProfileController {

    private final TutorProfileService tutorProfileService;

    TutorProfileController(TutorProfileService tutorProfileService) {
        this.tutorProfileService = tutorProfileService;
    }

    @GetMapping
    public ResponseEntity<List<TutorProfileSummaryDto>> getAllTutorProfiles() {
        return ResponseEntity.ok().body(
                tutorProfileService.getAllTutorProfiles()
                        .stream()
                        .map(TutorProfileSummaryDto::from)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TutorProfileDetailsDto> getTutorProfileById(@PathVariable long id) {
        return ResponseEntity.ok(TutorProfileDetailsDto.from(tutorProfileService.getTutorProfileById(id)));
    }

    @PatchMapping("/me")
    public ResponseEntity<TutorProfileDetailsDto> updateMyTutorProfile(
            @RequestBody TutorProfileUpdateDto dto,
            @AuthenticationPrincipal MyUserDetails me
    ) {
        TutorProfile newProfile = tutorProfileService.updateMyTutorProfile(me.getUser(), dto);
        return ResponseEntity.ok(TutorProfileDetailsDto.from(newProfile));
    }


}
