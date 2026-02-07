package com.slemanski.backend.features.subjects.model;

import lombok.Getter;

public enum Subject {
    MATH("Mathematics"),
    PHYSICS("Physics"),
    ENGLISH("English");

    @Getter
    private final String label;

    Subject(String label){
        this.label = label;
    }

    public String getCode() {
        return name();
    }

}
