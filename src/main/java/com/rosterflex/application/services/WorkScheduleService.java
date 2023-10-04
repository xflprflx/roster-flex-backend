package com.rosterflex.application.services;

import com.rosterflex.application.dtos.WorkScheduleDTO;
import com.rosterflex.application.models.WorkSchedule;
import com.rosterflex.application.repositories.WorkScheduleRepository;
import com.rosterflex.application.services.exceptions.DatabaseException;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WorkScheduleService {

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Transactional(readOnly = true)
    public Page<WorkScheduleDTO> findAllPaged(Pageable pageable){
        Page<WorkSchedule> page = workScheduleRepository.findAll(pageable);
        return page.map(x -> new WorkScheduleDTO(x));
    }

    @Transactional(readOnly = true)
    public WorkScheduleDTO findById(Long id) {
        Optional<WorkSchedule> obj = workScheduleRepository.findById(id);
        WorkSchedule workSchedule = obj.orElseThrow(() -> new ResourceNotFoundException("Escala não localizado."));
        return new WorkScheduleDTO(workSchedule);
    }

    @Transactional
    public WorkScheduleDTO insert(WorkScheduleDTO dto) {
        WorkSchedule workSchedule = new WorkSchedule();
        copyDtoToEntity(dto, workSchedule);
        workSchedule = workScheduleRepository.save(workSchedule);
        return new WorkScheduleDTO(workSchedule);
    }

    @Transactional
    public WorkScheduleDTO update(Long id, WorkScheduleDTO dto) {
        try {
            WorkSchedule workSchedule = workScheduleRepository.getReferenceById(id);
            copyDtoToEntity(dto, workSchedule);
            workScheduleRepository.save(workSchedule);
            return new WorkScheduleDTO(workSchedule);
        } catch (MappingException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Id %d não encontrado.", id));
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!workScheduleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            workScheduleRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(WorkScheduleDTO dto, WorkSchedule entity) {
        entity.setInitialDate(dto.getInitialDate());
        entity.setFinalDate(dto.getFinalDate());
        entity.setDescription(dto.getDescription());
    }
}
