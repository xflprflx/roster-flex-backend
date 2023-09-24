package com.rosterflex.application.repositories;

import com.rosterflex.application.models.ScheduleType;
import com.rosterflex.application.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ScheduleTypeRepositoryTests {

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    private long existingId;
    private long countTotalScheduleTypes;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        countTotalScheduleTypes = 3L;
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {
        scheduleTypeRepository.deleteById(existingId);
        Optional<ScheduleType> result = scheduleTypeRepository.findById(existingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void saveShouldPersistWithAutoincrementWhenIdIsNull() {
        ScheduleType scheduleType = Factory.createScheduleType();
        scheduleType.setId(null);
        scheduleType = scheduleTypeRepository.save(scheduleType);
        Assertions.assertNotNull(scheduleType.getId());
        Assertions.assertEquals(countTotalScheduleTypes + 1, scheduleType.getId());
    }
}