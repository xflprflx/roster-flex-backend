package com.rosterflex.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private UserDTO manager;

    private Set<TeamRoleDTO> teamRolesDTO = new HashSet<>();

    private Set<UserDTO> employeesDTO = new HashSet<>();

    public TeamDTO() {
    }

    public TeamDTO(Long id, String name, User manager) {
        this.id = id;
        this.name = name;
        this.manager = new UserDTO(manager);
    }

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
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

    public UserDTO getManager() {
        return manager;
    }

    public void setManager(UserDTO manager) {
        this.manager = manager;
    }
}
