package com.rosterflex.application.dtos;

import com.rosterflex.application.models.Team;
import com.rosterflex.application.models.TeamRole;
import com.rosterflex.application.models.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TeamDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long id;
    private String name;

    private Set<TeamRoleDTO> teamRolesDTO = new HashSet<>();
    private Set<UserDTO> employeesDTO = new HashSet<>();

    public TeamDTO() {
    }

    public TeamDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
    }

    public TeamDTO(Team team, Set<TeamRole> teamRoles) {
        this.id = team.getId();
        this.name = team.getName();
        teamRoles.forEach(teamRole -> this.teamRolesDTO.add(new TeamRoleDTO(teamRole)));
    }

    public TeamDTO(Team team, Set<TeamRole> teamRoles, Set<User> employees) {
        this.id = team.getId();
        this.name = team.getName();
        teamRoles.forEach(teamRole -> this.teamRolesDTO.add(new TeamRoleDTO(teamRole)));
        employees.forEach(employee -> this.employeesDTO.add(new UserDTO(employee)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TeamRoleDTO> getTeamRoles() {
        return teamRolesDTO;
    }

    public Set<UserDTO> getEmployees() {
        return employeesDTO;
    }
}
