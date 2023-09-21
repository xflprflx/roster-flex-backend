package com.rosterflex.application.controllers;


import com.rosterflex.application.models.ScheduleType;
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

    @GetMapping
    public ResponseEntity<List<ScheduleType>> findAll(){
        List<ScheduleType> list = new ArrayList<>();
        list.add(new ScheduleType(1L, 12.0, 36.0, ChronoUnit.HOURS, 440, 1));
        list.add(new ScheduleType(2L, 6.0, 1.0, ChronoUnit.DAYS, 440, 1));
        return ResponseEntity.ok().body(list);
    }












}