package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.models.ScheduleType;
import com.rosterflex.application.services.ScheduleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/scheduleTypes")
public class ScheduleTypeController {

    @Autowired
    private ScheduleTypeService scheduleTypeService;

    @GetMapping
    public ResponseEntity<List<ScheduleTypeDTO>> findAll(){
        List<ScheduleTypeDTO> list = scheduleTypeService.findAll();
        return ResponseEntity.ok().body(list);
    }












}