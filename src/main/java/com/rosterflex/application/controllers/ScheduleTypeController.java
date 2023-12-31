package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.services.ScheduleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/scheduleTypes")
public class ScheduleTypeController {

    @Autowired
    private ScheduleTypeService scheduleTypeService;

    @GetMapping
    public ResponseEntity<Page<ScheduleTypeDTO>> findAll(Pageable pageable){
        Page<ScheduleTypeDTO> page = scheduleTypeService.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleTypeDTO> findById(@PathVariable Long id){
        ScheduleTypeDTO dto = scheduleTypeService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
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