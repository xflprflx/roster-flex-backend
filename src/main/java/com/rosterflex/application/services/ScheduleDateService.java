package com.rosterflex.application.services;

import com.rosterflex.application.dtos.ScheduleDateDTO;
import com.rosterflex.application.dtos.UserScheduleDateDTO;
import com.rosterflex.application.models.ScheduleDate;
import com.rosterflex.application.models.Turn;
import com.rosterflex.application.models.User;
import com.rosterflex.application.models.UserScheduleDate;
import com.rosterflex.application.models.pk.UserScheduleDatePK;
import com.rosterflex.application.repositories.ScheduleDateRepository;
import com.rosterflex.application.repositories.TurnRepository;
import com.rosterflex.application.repositories.UserRepository;
import com.rosterflex.application.repositories.UserScheduleDateRepository;
import com.rosterflex.application.services.exceptions.ArgumentNotValidException;
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
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

@Service
public class ScheduleDateService {

    @Autowired
    private ScheduleDateRepository scheduleDateRepository;

    @Autowired
    private TurnRepository turnRepository;

    @Autowired
    private UserScheduleDateRepository userScheduleDateRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<ScheduleDateDTO> findAllPaged(Pageable pageable){
        Page<ScheduleDate> page = scheduleDateRepository.findAll(pageable);
        return page.map(x -> new ScheduleDateDTO(x));
    }

    @Transactional(readOnly = true)
    public ScheduleDateDTO findById(Long id) {
        Optional<ScheduleDate> obj = scheduleDateRepository.findById(id);
        ScheduleDate scheduleDate = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não localizada."));
        return new ScheduleDateDTO(scheduleDate);
    }

    @Transactional
    public ScheduleDateDTO insert(ScheduleDateDTO dto) {
        ScheduleDate scheduleDate = new ScheduleDate();
        copyDtoToEntity(dto, scheduleDate);
        if(scheduleDateRepository.existsByDate(scheduleDate.getDate())){
            throw new ArgumentNotValidException("Data já existente");
        }
        scheduleDate = scheduleDateRepository.save(scheduleDate);
        userScheduleDateRepository.saveAll(scheduleDate.getUserScheduleDates());
        return new ScheduleDateDTO(scheduleDate);
    }

    @Transactional
    public ScheduleDateDTO update(Long id, ScheduleDateDTO dto) {
        try {
            ScheduleDate scheduleDate = scheduleDateRepository.getReferenceById(id);
            copyDtoToEntity(dto, scheduleDate);
            userScheduleDateRepository.saveAll(scheduleDate.getUserScheduleDates());
            scheduleDateRepository.save(scheduleDate);
            return new ScheduleDateDTO(scheduleDate);
        } catch (MappingException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Id %d não encontrado.", id));
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!scheduleDateRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            scheduleDateRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ScheduleDateDTO dto, ScheduleDate entity) {
        entity.setDate(dto.getDate());
        entity.setHoliday(dto.isHoliday());
        if(!entity.getUserScheduleDates().isEmpty()){
            userScheduleDateRepository.deleteAll(entity.getUserScheduleDates());
        }
        entity.getUserScheduleDates().clear();
        for (UserScheduleDateDTO userScheduleDateDTO : dto.getUserScheduleDates()) {
            User user = userRepository.getReferenceById(userScheduleDateDTO.getUserId());
            Turn turn = turnRepository.getReferenceById(userScheduleDateDTO.getTurn().getId());
            UserScheduleDate userScheduleDate = new UserScheduleDate(user, entity, turn);
            entity.getUserScheduleDates().add(userScheduleDate);
        }
    }

}
