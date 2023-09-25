package com.rosterflex.application.services;

import com.rosterflex.application.repositories.ScheduleTypeRepository;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.ResourceAccessException;

@ExtendWith(SpringExtension.class)
public class ScheduleTypeServiceTests {

    @InjectMocks
    private ScheduleTypeService scheduleTypeService;

    @Mock
    private ScheduleTypeRepository scheduleTypeRepository;



    private long existingId;
    private long nonExistingId;
    private long countTotalScheduleTypes;

    @BeforeEach
    void setUp() throws Exception{
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalScheduleTypes = 3L;

        Mockito.doNothing().when(scheduleTypeRepository).deleteById(existingId);
        Mockito.when(scheduleTypeRepository.existsById(existingId)).thenReturn(true);
        Mockito.when(scheduleTypeRepository.existsById(nonExistingId)).thenReturn(false);
        //Mockito.when(scheduleTypeRepository.existsById(dependentId)).thenReturn(true);
    }

    @Test
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> {
            scheduleTypeService.delete(existingId);
        });
        Mockito.verify(scheduleTypeRepository, Mockito.times(1)).deleteById(existingId);
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            scheduleTypeService.delete(nonExistingId);
        });
    }
}
