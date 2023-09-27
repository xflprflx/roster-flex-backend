package com.rosterflex.application.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ScheduleTypeControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalScheduleTypes;

    private String username;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        countTotalScheduleTypes = 3L;
    }

    @Test
    public void findAllShouldReturnSortedPageWhenSortByName() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/scheduleTypes?page=0&size=12&sort=name,asc")
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.totalElements").value(countTotalScheduleTypes));
        result.andExpect(jsonPath("$.content").exists());
        result.andExpect(jsonPath("$.content[0].name").value("12x36"));
        result.andExpect(jsonPath("$.content[1].name").value("5x2"));
        result.andExpect(jsonPath("$.content[2].name").value("6x1"));
    }

    @Test
    public void updateShouldReturnProductDTOWhenIdExists() throws Exception {


        ScheduleTypeDTO scheduleTypeDTO = Factory.createScheduleTypeDTO();
        String jsonBody = objectMapper.writeValueAsString(scheduleTypeDTO);

        String expectedName = scheduleTypeDTO.getName();
        Double expectedWorkedTime = scheduleTypeDTO.getWorkedTime();

        ResultActions result =
                mockMvc.perform(put("/scheduleTypes/{id}", existingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(existingId));
        result.andExpect(jsonPath("$.name").value(expectedName));
        result.andExpect(jsonPath("$.workedTime").value(expectedWorkedTime));
    }

    @Test
    public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {
        ScheduleTypeDTO scheduleTypeDTO = Factory.createScheduleTypeDTO();
        String jsonBody = objectMapper.writeValueAsString(scheduleTypeDTO);
        ResultActions result =
                mockMvc.perform(put("/scheduleTypes/{id}", nonExistingId)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isNotFound());
    }
}