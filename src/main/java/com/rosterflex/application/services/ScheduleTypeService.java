package com.rosterflex.application.services;

import com.rosterflex.application.models.ScheduleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rosterflex.application.repositories.ScheduleTypeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleTypeService {

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

    @Transactional(readOnly = true)
    public List<ScheduleType> findAll(){
        return scheduleTypeRepository.findAll();
    }
}
