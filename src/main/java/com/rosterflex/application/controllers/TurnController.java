package com.rosterflex.application.controllers;


import com.rosterflex.application.dtos.RevisionDataDTO;
import com.rosterflex.application.dtos.TurnDTO;
import com.rosterflex.application.models.EntityWithRevision;
import com.rosterflex.application.models.Turn;
import com.rosterflex.application.services.RevisionService;
import com.rosterflex.application.services.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/turns")
public class TurnController {

    @Autowired
    private TurnService turnService;

    @Autowired
    private RevisionService revisionService;

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

    @GetMapping("/revisions/{id}")
    public ResponseEntity<List<EntityWithRevision<Turn>>> getRevisions(@PathVariable Long id) {
        List<EntityWithRevision<Turn>> revisions = revisionService.getRevisions(id, Turn.class);
        if (revisions != null) {
            return new ResponseEntity(revisions, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/auditData/{id}")
    public ResponseEntity<List<RevisionDataDTO>> getRevisionsWithAttributeComparison(@PathVariable Long id) {
        List<RevisionDataDTO> revisions = revisionService.getRevisionsWithAttributeComparison(id, Turn.class);
        if (revisions != null) {
            return new ResponseEntity(revisions, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}