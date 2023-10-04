package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.TeamDTO;
import com.rosterflex.application.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<Page<TeamDTO>> findAll(Pageable pageable){
        Page<TeamDTO> page = teamService.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TeamDTO> findById(@PathVariable Long id){
        TeamDTO dto = teamService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping()
    public ResponseEntity<TeamDTO> insert(@Valid @RequestBody TeamDTO dto){
        TeamDTO newDto = teamService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TeamDTO> update(@PathVariable Long id, @Valid @RequestBody TeamDTO dto){
        TeamDTO newDto = teamService.update(id, dto);
        return ResponseEntity.ok().body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}