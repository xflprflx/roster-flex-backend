package com.rosterflex.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.services.ScheduleTypeService;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import com.rosterflex.application.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleTypeController.class)
public class ScheduleTypeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleTypeService scheduleTypeService;

    @Autowired
    private ObjectMapper objectMapper;


    private ScheduleTypeDTO scheduleTypeDTO;
    private PageImpl<ScheduleTypeDTO> page;
    private long existingId;
    private long nonExistingId;
    private long dependentId;

    @BeforeEach
    void setUp() throws Exception {
        scheduleTypeDTO = Factory.createScheduleTypeDTO();
        page = new PageImpl<>(List.of(scheduleTypeDTO));
        existingId = 1L;
        nonExistingId = 1000L;
        dependentId = 5L;

        when(scheduleTypeService.findAllPaged(any())).thenReturn(page);

        when(scheduleTypeService.findById(existingId)).thenReturn(scheduleTypeDTO);
        when(scheduleTypeService.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);

        when(scheduleTypeService.update(eq(existingId), any())).thenReturn(scheduleTypeDTO);
        when(scheduleTypeService.update(eq(nonExistingId), any())).thenThrow(ResourceNotFoundException.class);
    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(scheduleTypeDTO);

        ResultActions result =
                mockMvc.perform(put("/scheduleTypes/{id}", existingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.workedTime").exists());
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

        String jsonBody = objectMapper.writeValueAsString(scheduleTypeDTO);

        ResultActions result =
                mockMvc.perform(put("/scheduleTypes/{id}", nonExistingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }

    @Test
    public void findAllShouldReturnPage() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/scheduleTypes")
                                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

    @Test
    public void findByIdShouldReturnScheduleTypeWhenIdExists() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/scheduleTypes/{id}", existingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").exists());
        result.andExpect(jsonPath("$.name").exists());
        result.andExpect(jsonPath("$.workedTime").exists());
    }

    @Test
    public void findByIdShouldReturnNotFoundWhenIdExists() throws Exception {

        ResultActions result =
                mockMvc.perform(get("/scheduleTypes/{id}", nonExistingId)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isNotFound());
    }


}
