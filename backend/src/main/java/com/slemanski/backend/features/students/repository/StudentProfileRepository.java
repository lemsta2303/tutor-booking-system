package com.slemanski.backend.features.students.repository;

import com.slemanski.backend.features.students.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}
