package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.services.ScheduleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleTypeDTO> findById(@PathVariable Long id){
        ScheduleTypeDTO dto = scheduleTypeService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping()
    public ResponseEntity<ScheduleTypeDTO> insert(@RequestBody ScheduleTypeDTO dto){
        dto = scheduleTypeService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ScheduleTypeDTO> update(@PathVariable Long id, @RequestBody ScheduleTypeDTO dto){
        dto = scheduleTypeService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        scheduleTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}