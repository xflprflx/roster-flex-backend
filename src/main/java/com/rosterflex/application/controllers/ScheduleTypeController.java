package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.services.ScheduleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
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
    public ResponseEntity<Page<ScheduleTypeDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ScheduleTypeDTO> resultPage = scheduleTypeService.findAllPaged((pageRequest));
        return ResponseEntity.ok().body(resultPage);
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