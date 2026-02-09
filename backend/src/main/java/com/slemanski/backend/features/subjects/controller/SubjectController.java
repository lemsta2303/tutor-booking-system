package com.slemanski.backend.features.subjects.controller;

import com.slemanski.backend.features.subjects.dto.SubjectDto;
import com.slemanski.backend.features.subjects.model.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @GetMapping
    public List<SubjectDto> getSubjects() {
        return Arrays.stream(Subject.values())
                .map(s-> new SubjectDto(s.getCode(), s.getLabel()))
                .toList();
    }
}
