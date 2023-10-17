package com.rosterflex.application.services;

import com.rosterflex.application.dtos.ChangeTurnDTO;
import com.rosterflex.application.enums.ShiftStatus;
import com.rosterflex.application.models.ChangeTurn;
import com.rosterflex.application.models.ScheduleDate;
import com.rosterflex.application.models.User;
import com.rosterflex.application.models.pk.UserScheduleDatePK;
import com.rosterflex.application.repositories.ChangeTurnRepository;
import com.rosterflex.application.repositories.ScheduleDateRepository;
import com.rosterflex.application.repositories.UserRepository;
import com.rosterflex.application.repositories.UserScheduleDateRepository;
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

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ChangeTurnService {

    @Autowired
    private ChangeTurnRepository changeTurnRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleDateRepository scheduleDateRepository;

    @Autowired
    private UserScheduleDateRepository userScheduleDateRepository;

    @Transactional(readOnly = true)
    public Page<ChangeTurnDTO> findAllPaged(Pageable pageable){
        Page<ChangeTurn> page = changeTurnRepository.findAll(pageable);
        return page.map(x -> new ChangeTurnDTO(x));
    }

    @Transactional(readOnly = true)
    public ChangeTurnDTO findById(Long id) {
        Optional<ChangeTurn> obj = changeTurnRepository.findById(id);
        ChangeTurn changeTurn = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não localizado."));
        return new ChangeTurnDTO(changeTurn);
    }

    @Transactional
    public ChangeTurnDTO insert(ChangeTurnDTO dto) {
        ChangeTurn changeTurn = new ChangeTurn();
        changeTurn.setRequestDate(LocalDate.now());
        changeTurn.setPeerApproval(ShiftStatus.PENDING);
        changeTurn.setManagerApproval(ShiftStatus.PENDING);
        copyDtoToEntity(dto, changeTurn);
        changeTurn = changeTurnRepository.save(changeTurn);
        return new ChangeTurnDTO(changeTurn);
    }

    @Transactional
    public ChangeTurnDTO update(Long id, ChangeTurnDTO dto) {
        try {
            ChangeTurn changeTurn = changeTurnRepository.getReferenceById(id);
            changeTurn.setId(id);
            copyDtoToEntity(dto, changeTurn);
            changeTurnRepository.save(changeTurn);
            return new ChangeTurnDTO(changeTurn);
        } catch (MappingException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Id %d não encontrado.", id));
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!changeTurnRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            changeTurnRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ChangeTurnDTO dto, ChangeTurn entity) {
        entity.setMessage(dto.getMessage());
        User userOriginalSchedule = userRepository.getReferenceById(dto.getUserOriginalSchedule().getId());
        User userProposedSchedule = userRepository.getReferenceById(dto.getUserProposedSchedule().getId());
        ScheduleDate scheduleDateOriginalSchedule = scheduleDateRepository.getReferenceById(dto.getScheduleDateOriginalSchedule().getId());
        ScheduleDate scheduleDateProposedSchedule = scheduleDateRepository.getReferenceById(dto.getScheduleDateProposedSchedule().getId());
        entity.setOriginalSchedule(userScheduleDateRepository.getReferenceById(new UserScheduleDatePK(userOriginalSchedule, scheduleDateOriginalSchedule)));
        entity.setProposedSchedule(userScheduleDateRepository.getReferenceById(new UserScheduleDatePK(userProposedSchedule, scheduleDateProposedSchedule)));
    }
}
