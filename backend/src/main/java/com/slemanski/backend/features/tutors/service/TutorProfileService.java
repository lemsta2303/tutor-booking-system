package com.slemanski.backend.features.tutors.service;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.subjects.model.Subject;
import com.slemanski.backend.features.tutors.dto.TutorProfileUpdateDto;
import com.slemanski.backend.features.tutors.exception.TutorNotFoundException;
import com.slemanski.backend.features.tutors.model.TutorProfile;
import com.slemanski.backend.features.tutors.repository.TutorProfileRepository;
import com.slemanski.backend.shared.exception.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorProfileService {

    private final TutorProfileRepository tutorProfileRepository;

    public TutorProfileService(TutorProfileRepository repo) {
        this.tutorProfileRepository = repo;
    }

    public List<TutorProfile> getAllTutorProfiles() {
        return tutorProfileRepository.findAll();
    }

    public TutorProfile getTutorProfileById(long tutorId) {

        Optional<TutorProfile> foundProfile = this.tutorProfileRepository.findById(tutorId);
        if(foundProfile.isEmpty()) {
            throw new TutorNotFoundException(tutorId);
        }

        return foundProfile.get();
    }

    @Transactional
    public TutorProfile updateMyTutorProfile(MyUser user, TutorProfileUpdateDto dto) {
        TutorProfile tutorProfile = user.getTutorProfile();
        if(tutorProfile == null) {
            throw new UserNotFoundException();
        }

        Subject newSubject = dto.subject();
        Integer newHourlyRate = dto.hourlyRate();
        String newDescription = dto.description();

        if(newSubject != null) {
            tutorProfile.setSubject(newSubject);
        }
        if(newHourlyRate != null) {
            tutorProfile.setHourlyRate(newHourlyRate);
        }
        if(newDescription != null) {
            tutorProfile.setDescription(newDescription);
        }

        return tutorProfileRepository.save(tutorProfile);
    }
}
