package com.rosterflex.application.services;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.models.ScheduleType;
import com.rosterflex.application.repositories.ScheduleTypeRepository;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
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
        ScheduleType scheduleType = new ScheduleType();
        copyDtoToEntity(dto, scheduleType);
        scheduleType = scheduleTypeRepository.save(scheduleType);
        return new ScheduleTypeDTO(scheduleType);
    }

    private void copyDtoToEntity(ScheduleTypeDTO dto, ScheduleType entity){
        entity.setName(dto.getName());
        entity.setDaysOff(dto.getDaysOff());
        entity.setWorkedTime(dto.getWorkedTime());
        entity.setFreeTime(dto.getFreeTime());
        entity.setMonthlyHours(dto.getMonthlyHours());
        entity.setUnity(dto.getUnity());
    }
}
