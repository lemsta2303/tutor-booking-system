package com.slemanski.backend.features.tutors.service;

import com.slemanski.backend.features.tutors.exception.TutorNotFoundException;
import com.slemanski.backend.features.tutors.model.TutorProfile;
import com.slemanski.backend.features.tutors.repository.TutorProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorProfileService {

    private final TutorProfileRepository repository;

    public TutorProfileService(TutorProfileRepository repo) {
        this.repository = repo;
    }

    public List<TutorProfile> getAllTutorProfiles() {
        return repository.findAll();
    }

    public TutorProfile getTutorProfileById(long tutorId) {

        Optional<TutorProfile> foundProfile = this.repository.findById(tutorId);
        if(foundProfile.isEmpty()) {
            throw new TutorNotFoundException(tutorId);
        }

        return foundProfile.get();
    }
}
