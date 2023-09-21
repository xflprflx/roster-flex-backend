package com.rosterflex.application.services;

import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.models.ScheduleType;
import com.rosterflex.application.repositories.ScheduleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
}
