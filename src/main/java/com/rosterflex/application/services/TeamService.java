package com.rosterflex.application.services;

import com.rosterflex.application.dtos.TeamDTO;
import com.rosterflex.application.dtos.TeamRoleDTO;
import com.rosterflex.application.models.Role;
import com.rosterflex.application.models.Team;
import com.rosterflex.application.models.TeamRole;
import com.rosterflex.application.repositories.RoleRepository;
import com.rosterflex.application.repositories.TeamRepository;
import com.rosterflex.application.repositories.TeamRoleRepository;
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
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamRoleRepository teamRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public Page<TeamDTO> findAllPaged(Pageable pageable){
        Page<Team> page = teamRepository.findAll(pageable);
        return page.map(x -> new TeamDTO(x, x.getTeamRoles()));
    }

    @Transactional(readOnly = true)
    public TeamDTO findById(Long id) {
        Optional<Team> obj = teamRepository.findById(id);
        Team team = obj.orElseThrow(() -> new ResourceNotFoundException("Recurso não localizado."));
        return new TeamDTO(team, team.getTeamRoles());
    }

    @Transactional
    public TeamDTO insert(TeamDTO dto) {
        Team team = new Team();
        copyDtoToEntity(dto, team);
        for (TeamRoleDTO teamRoleDTO : dto.getTeamRoles()){
            Role role = roleRepository.getReferenceById(teamRoleDTO.getRoleId());
            TeamRole teamRole = new TeamRole(team, role, teamRoleDTO.getQuantity());
            team.getTeamRoles().add(teamRole);
        }
        team = teamRepository.save(team);
        teamRoleRepository.saveAll(team.getTeamRoles());
        return new TeamDTO(team, team.getTeamRoles());
    }

    @Transactional
    public TeamDTO update(Long id, TeamDTO dto) {
        try {
            Team team = teamRepository.getReferenceById(id);
            copyDtoToEntity(dto, team);
            for (TeamRoleDTO teamRoleDTO : dto.getTeamRoles()){
                Role role = roleRepository.getReferenceById(teamRoleDTO.getRoleId());
                TeamRole teamRole = new TeamRole(team, role, teamRoleDTO.getQuantity());
                teamRoleRepository.save(teamRole);
            }
            teamRepository.save(team);
            return new TeamDTO(team, team.getTeamRoles());
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
    }
}
