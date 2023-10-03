package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.dtos.TurnDTO;
import com.rosterflex.application.services.ScheduleTypeService;
import com.rosterflex.application.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/turns")
public class TurnController {

    @Autowired
    private TurnService turnService;

    @GetMapping
    public ResponseEntity<Page<TurnDTO>> findAll(Pageable pageable){
        Page<TurnDTO> page = turnService.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OPERATOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TurnDTO> findById(@PathVariable Long id){
        TurnDTO dto = turnService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<TurnDTO> insert(@RequestBody TurnDTO dto){
        dto = turnService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TurnDTO> update(@PathVariable Long id, @RequestBody TurnDTO dto){
        dto = turnService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        turnService.delete(id);
        return ResponseEntity.noContent().build();
    }
}