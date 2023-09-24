package com.rosterflex.application.repositories;

import com.rosterflex.application.models.ScheduleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ScheduleTypeRepositoryTests {

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        long existingId = 1L;
        scheduleTypeRepository.deleteById(existingId);
        Optional<ScheduleType> result = scheduleTypeRepository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }
}