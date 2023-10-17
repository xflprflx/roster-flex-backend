package com.rosterflex.application.services;

import com.rosterflex.application.dtos.TeamDTO;
import com.rosterflex.application.dtos.TeamRoleDTO;
import com.rosterflex.application.dtos.UserDTO;
import com.rosterflex.application.models.Role;
import com.rosterflex.application.models.Team;
import com.rosterflex.application.models.TeamRole;
import com.rosterflex.application.models.User;
import com.rosterflex.application.repositories.RoleRepository;
import com.rosterflex.application.repositories.TeamRepository;
import com.rosterflex.application.repositories.TeamRoleRepository;
import com.rosterflex.application.repositories.UserRepository;
import com.rosterflex.application.services.exceptions.DatabaseException;
import com.rosterflex.application.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.MappingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamRoleRepository teamRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<TeamDTO> findAllPaged(Pageable pageable){
        Page<Team> page = teamRepository.findAll(pageable);
        return page.map(x -> new TeamDTO(x));
    }

    @Transactional(readOnly = true)
    public TeamDTO findById(Long id) {
        Optional<Team> obj = teamRepository.findById(id);
        Team team = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não localizado."));
        return new TeamDTO(team);
    }

    @Transactional
    public TeamDTO insert(TeamDTO dto) {
        Team team = new Team();
        copyDtoToEntity(dto, team);
        team = teamRepository.save(team);
        teamRoleRepository.saveAll(team.getTeamRoles());
        userRepository.saveAll(team.getEmployees());

        return new TeamDTO(team);
    }

    @Transactional
    public TeamDTO update(Long id, TeamDTO dto) {
        try {
            Team team = teamRepository.getReferenceById(id);
            team.setId(id);
            copyDtoToEntity(dto, team);
            team.getEmployees().forEach(employee -> employee.getTeam().setId(id));
            teamRoleRepository.saveAll(team.getTeamRoles());
            userRepository.saveAll(team.getEmployees());
            teamRepository.save(team);
            return new TeamDTO(team);
        } catch (MappingException | EntityNotFoundException e) {
            throw new ResourceNotFoundException(String.format("Id %d não encontrado.", id));
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!teamRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            teamRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(TeamDTO dto, Team entity) {
        entity.setName(dto.getName());
        User manager = userRepository.getReferenceById(dto.getManager().getId());
        entity.setManager(manager);
        entity.getEmployees().clear();
        entity.getTeamRoles().clear();

        for (TeamRoleDTO teamRoleDTO : dto.getTeamRoles()){
            Role role = roleRepository.getReferenceById(teamRoleDTO.getRoleId());
            TeamRole teamRole = new TeamRole(entity, role, teamRoleDTO.getQuantity());
            entity.getTeamRoles().add(teamRole);
        }

        for (UserDTO employeeDTO : dto.getEmployees()){
            User employee = userRepository.getReferenceById(employeeDTO.getId());
            employee.setTeam(entity);
            entity.getEmployees().add(employee);
        }
    }
}
