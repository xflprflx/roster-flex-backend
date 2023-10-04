package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.ScheduleDateDTO;
import com.rosterflex.application.services.ScheduleDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/scheduleDates")
public class ScheduleDateController {

    @Autowired
    private ScheduleDateService scheduleDateService;

    @GetMapping
    public ResponseEntity<Page<ScheduleDateDTO>> findAll(Pageable pageable){
        Page<ScheduleDateDTO> page = scheduleDateService.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<ScheduleDateDTO> findById(@PathVariable Long id){
        ScheduleDateDTO dto = scheduleDateService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<ScheduleDateDTO> insert(@RequestBody ScheduleDateDTO dto){
        dto = scheduleDateService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ScheduleDateDTO> update(@PathVariable Long id, @RequestBody ScheduleDateDTO dto){
        dto = scheduleDateService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        scheduleDateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}