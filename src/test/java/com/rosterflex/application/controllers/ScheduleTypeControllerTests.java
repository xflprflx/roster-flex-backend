package com.rosterflex.application.controllers;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.services.ScheduleTypeService;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ScheduleTypeController.class)
public class ScheduleTypeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ScheduleTypeService scheduleTypeService;

    private ScheduleTypeDTO scheduleTypeDTO;
    private PageImpl<ScheduleTypeDTO> page;

    @BeforeEach
    void setUp() throws Exception {
        scheduleTypeDTO = Factory.createScheduleTypeDTO();
        page = new PageImpl<>(List.of(scheduleTypeDTO));
        when(scheduleTypeService.findAllPaged(any())).thenReturn(page);
    }

    @Test
    public void findAllShouldReturnPage() throws Exception {
        ResultActions result =
                mockMvc.perform(get("/scheduleTypes")
                                .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }
}
