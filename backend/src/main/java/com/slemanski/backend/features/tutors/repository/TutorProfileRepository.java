package com.slemanski.backend.features.tutors.repository;

import com.slemanski.backend.features.tutors.model.TutorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorProfileRepository extends JpaRepository<TutorProfile, Long> {
}
