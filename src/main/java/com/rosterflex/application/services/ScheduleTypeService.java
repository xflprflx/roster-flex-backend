package com.rosterflex.application.services;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.models.ScheduleType;
import com.rosterflex.application.repositories.ScheduleTypeRepository;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleTypeService {

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public List<ScheduleTypeDTO> findAll(){
        List<ScheduleType> list = scheduleTypeRepository.findAll();
        return list.stream().map(x -> new ScheduleTypeDTO(x)).collect(Collectors.toList());
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
            scheduleType = modelMapper.map(dto, ScheduleType.class);
            scheduleType.setId(id);
            scheduleType = scheduleTypeRepository.save(scheduleType);
            return new ScheduleTypeDTO(scheduleType);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(String.format("Id %d não encontrado.", id));
        }
    }
}
