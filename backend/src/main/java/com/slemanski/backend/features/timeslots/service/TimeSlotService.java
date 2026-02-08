package com.slemanski.backend.features.timeslots.service;

import com.slemanski.backend.features.auth.model.MyUser;
import com.slemanski.backend.features.timeslots.dto.TimeSlotCreateDto;
import com.slemanski.backend.shared.exception.exception.InvalidParamsException;
import com.slemanski.backend.shared.exception.exception.UserNotFoundException;
import com.slemanski.backend.features.timeslots.model.TimeSlot;
import com.slemanski.backend.features.timeslots.repository.TimeSlotRepository;
import com.slemanski.backend.features.tutors.model.TutorProfile;
import com.slemanski.backend.features.tutors.repository.TutorProfileRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TimeSlotService {

    private final int MIN_TIME_SLOT_LENGTH_IN_MINUTES = 15;
    private final int MAX_TIME_SLOT_LENGTH_IN_MINUTES = 240;

    private final TimeSlotRepository timeSlotRepository;
    private final TutorProfileRepository tutorProfileRepository;

    public TimeSlotService(TimeSlotRepository timeSlotRepository, TutorProfileRepository tutorProfileRepository) {
        this.timeSlotRepository = timeSlotRepository;
        this.tutorProfileRepository = tutorProfileRepository;
    }

    public void createTimeSlot(TimeSlotCreateDto dto, MyUser user) throws UserNotFoundException {
//        Optional<MyUser> user = myUserRepository.findById(userId);
//        if(user.isEmpty()) {
//            throw new UserNotFoundException();
//        }
//
//        TutorProfile tutor = user.get().getTutorProfile();
//        if(tutor == null) {
//            throw new UserNotFoundException();
//        }

        TutorProfile tutor = tutorProfileRepository.findByUser(user);
        validateNewSlotParams(dto, tutor);

        TimeSlot newTimeSlot = new TimeSlot();
        newTimeSlot.setStartTime(dto.startTime());
        newTimeSlot.setEndTime(dto.endTime());
        newTimeSlot.setTutorProfile(tutor);

        timeSlotRepository.save(newTimeSlot);

    }

    public void validateNewSlotParams(TimeSlotCreateDto dto, TutorProfile tutor) {

        if(Duration.between(dto.startTime(), dto.endTime() ).toMinutes() < MIN_TIME_SLOT_LENGTH_IN_MINUTES) {
            throw new InvalidParamsException("Slot must be at least 15 minutes.");
        }

        if(Duration.between(dto.startTime(), dto.endTime() ).toMinutes() > MAX_TIME_SLOT_LENGTH_IN_MINUTES) {
            throw new InvalidParamsException("Slot can be maximum 4 hours.");
        }

        boolean ifNewSlotOverlapWithSomeOther = timeSlotRepository.existsOverlapping(
                tutor.getTutorId(),
                dto.startTime(),
                dto.endTime()
        );

        if(ifNewSlotOverlapWithSomeOther) {
            throw new InvalidParamsException("Time slot overlaps existing slots.");
        }
    }
}
