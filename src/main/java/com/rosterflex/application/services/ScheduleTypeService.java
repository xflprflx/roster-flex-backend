package com.rosterflex.application.services;

import com.rosterflex.application.dtos.RoleDTO;
import com.rosterflex.application.dtos.ScheduleTypeDTO;
import com.rosterflex.application.dtos.UserDTO;
import com.rosterflex.application.models.Role;
import com.rosterflex.application.models.ScheduleType;
import com.rosterflex.application.models.User;
import com.rosterflex.application.repositories.ScheduleTypeRepository;
import com.rosterflex.application.services.exceptions.DatabaseException;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class ScheduleTypeService {

    @Autowired
    private ScheduleTypeRepository scheduleTypeRepository;

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
        ScheduleType scheduleType = new ScheduleType();
        copyDtoToEntity(dto, scheduleType);
        scheduleType = scheduleTypeRepository.save(scheduleType);
        return new ScheduleTypeDTO(scheduleType);
    }

    @Transactional
    public ScheduleTypeDTO update(Long id, ScheduleTypeDTO dto) {
        try {
            ScheduleType scheduleType = scheduleTypeRepository.getReferenceById(id);
            copyDtoToEntity(dto, scheduleType);
            scheduleTypeRepository.save(scheduleType);
            return new ScheduleTypeDTO(scheduleType);
        } catch (MappingException | EntityNotFoundException e) {
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

    private void copyDtoToEntity(ScheduleTypeDTO dto, ScheduleType entity) {
        entity.setName(dto.getName());
        entity.setWorkedTime(dto.getWorkedTime());
        entity.setFreeTime(dto.getFreeTime());
        entity.setUnity(dto.getUnity());
        entity.setMonthlyHours(dto.getMonthlyHours());
        entity.setDaysOff(dto.getDaysOff());
    }
}
