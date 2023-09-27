package com.rosterflex.application.services;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.repositories.ScheduleTypeRepository;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
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
    public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {
        PageRequest pageRequest = PageRequest.of(50, 10);
        Page<ScheduleTypeDTO> result = scheduleTypeService.findAllPaged(pageRequest);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void findAllPagedShouldReturnPageWhenPage0Size10() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<ScheduleTypeDTO> result = scheduleTypeService.findAllPaged(pageRequest);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals(10, result.getSize());
        Assertions.assertEquals(countTotalScheduleTypes, result.getTotalElements());
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
