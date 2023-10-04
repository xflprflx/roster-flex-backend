package com.rosterflex.application.dtos;

import com.rosterflex.application.models.Team;
import com.rosterflex.application.models.TeamRole;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TeamDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long id;
    private String name;

    private Set<TeamRoleDTO> teamRoleDTOS = new HashSet<>();

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
        teamRoles.forEach(teamRole -> this.teamRoleDTOS.add(new TeamRoleDTO(teamRole)));
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
        return teamRoleDTOS;
    }
}
