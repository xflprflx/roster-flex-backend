package com.rosterflex.application.services;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.models.ScheduleType;
import com.rosterflex.application.repositories.ScheduleTypeRepository;
import com.rosterflex.application.services.exceptions.DatabaseException;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ScheduleTypeService {

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Page<ScheduleTypeDTO> findAllPaged(Pageable pageable){
        Page<ScheduleType> page = scheduleTypeRepository.findAll(pageable);
        return page.map(x -> new ScheduleTypeDTO(x));
    }

    @Transactional(readOnly = true)
    public ScheduleTypeDTO findById(Long id) {
        Optional<ScheduleType> obj = scheduleTypeRepository.findById(id);
        ScheduleType scheduleType = obj.orElseThrow(() -> new ResourceNotFoundException("Tipo de escala não localizada."));
        return new ScheduleTypeDTO(scheduleType);
    }

    @Transactional
    public ScheduleTypeDTO insert(ScheduleTypeDTO dto) {
        ScheduleType scheduleType = modelMapper.map(dto, ScheduleType.class);
        scheduleType = scheduleTypeRepository.save(scheduleType);
        return new ScheduleTypeDTO(scheduleType);
    }

    @Transactional
    public ScheduleTypeDTO update(Long id, ScheduleTypeDTO dto) {
        try {
            ScheduleType scheduleType = scheduleTypeRepository.getReferenceById(id);
            modelMapper.map(dto, scheduleType.getClass());
            scheduleType = scheduleTypeRepository.save(scheduleType);
            return new ScheduleTypeDTO(scheduleType);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(String.format("Id %d não encontrado.", id));
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!scheduleTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            scheduleTypeRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
