package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.ChangeTurnDTO;
import com.rosterflex.application.services.ChangeTurnService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/changeTurns")
public class ChangeTurnController {

    @Autowired
    private ChangeTurnService changeTurnService;

    @GetMapping
    public ResponseEntity<Page<ChangeTurnDTO>> findAll(Pageable pageable){
        Page<ChangeTurnDTO> page = changeTurnService.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChangeTurnDTO> findById(@PathVariable Long id){
        ChangeTurnDTO dto = changeTurnService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping()
    public ResponseEntity<ChangeTurnDTO> insert(@Valid @RequestBody ChangeTurnDTO dto){
        ChangeTurnDTO newDto = changeTurnService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ChangeTurnDTO> update(@PathVariable Long id, @Valid @RequestBody ChangeTurnDTO dto){
        ChangeTurnDTO newDto = changeTurnService.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        changeTurnService.delete(id);
        return ResponseEntity.noContent().build();
    }
}