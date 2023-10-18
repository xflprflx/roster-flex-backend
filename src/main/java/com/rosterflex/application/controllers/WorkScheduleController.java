package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.UserDTO;
import com.rosterflex.application.dtos.WorkScheduleDTO;
import com.rosterflex.application.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/workSchedules")
public class WorkScheduleController {

    @Autowired
    private WorkScheduleService workScheduleService;

    @GetMapping
    public ResponseEntity<Page<WorkScheduleDTO>> findAll(Pageable pageable){
        Page<WorkScheduleDTO> page = workScheduleService.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkScheduleDTO> findById(@PathVariable Long id){
        WorkScheduleDTO dto = workScheduleService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<WorkScheduleDTO> insert(@RequestBody WorkScheduleDTO dto, @RequestParam Long userId){
        dto = workScheduleService.insert(dto, userId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<WorkScheduleDTO> update(@PathVariable Long id, @RequestBody WorkScheduleDTO dto){
        dto = workScheduleService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        workScheduleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}