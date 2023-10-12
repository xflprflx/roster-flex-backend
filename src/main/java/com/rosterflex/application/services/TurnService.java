package com.rosterflex.application.services;

import com.rosterflex.application.dtos.TurnDTO;
import com.rosterflex.application.models.Turn;
import com.rosterflex.application.repositories.TurnRepository;
import com.rosterflex.application.services.exceptions.DatabaseException;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TurnService {

    @Autowired
    private TurnRepository turnRepository;

    @Transactional(readOnly = true)
    public Page<TurnDTO> findAllPaged(Pageable pageable){
        Page<Turn> page = turnRepository.findAll(pageable);
        return page.map(x -> new TurnDTO(x));
    }

    @Transactional(readOnly = true)
    public TurnDTO findById(Long id) {
        Optional<Turn> obj = turnRepository.findById(id);
        Turn turn = obj.orElseThrow(() -> new ResourceNotFoundException("Turno não localizado."));
        return new TurnDTO(turn);
    }

    @Transactional
    public TurnDTO insert(TurnDTO dto) {
        Turn turn = new Turn();
        copyDtoToEntity(dto, turn);
        turn = turnRepository.save(turn);
        return new TurnDTO(turn);
    }

    @Transactional
    public TurnDTO update(Long id, TurnDTO dto) {
        try {
            Turn turn = turnRepository.getReferenceById(id);
            copyDtoToEntity(dto, turn);
            turnRepository.save(turn);
            return new TurnDTO(turn);
        } catch (MappingException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Id %d não encontrado.", id));
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!turnRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            turnRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    public Revisions<Long, Turn> revisions(Long id) {
        return turnRepository.findRevisions(id);
                /*.stream()
                .map(Object::toString)
                .collect(Collectors.toList());*/
    }

    private void copyDtoToEntity(TurnDTO dto, Turn entity) {
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setIntervalDuration(dto.getIntervalDuration());
    }
}
