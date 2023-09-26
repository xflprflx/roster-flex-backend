package com.rosterflex.application.services;

import com.rosterflex.application.repositories.ScheduleTypeRepository;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ScheduleTypeIT {

    @Autowired
    private ScheduleTypeService scheduleTypeService;

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalScheduleTypes;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalScheduleTypes = 3L;
    }

    @Test
    public void deleteShouldDeleteResourceWhenIdExists() {
        scheduleTypeService.delete(existingId);
        Assertions.assertEquals(countTotalScheduleTypes - 1, scheduleTypeRepository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            scheduleTypeService.delete(nonExistingId);
        });
    }

}
