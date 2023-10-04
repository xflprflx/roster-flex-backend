package com.rosterflex.application.models;

import com.rosterflex.application.models.pk.TeamRolePK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "tb_team_role")
public class TeamRole {

    @EmbeddedId
    private TeamRolePK id = new TeamRolePK();

    private Integer quantity;

    public TeamRole() {
    }

    public TeamRole(Team team, Role role, Integer quantity) {
        id.setTeam(team);
        id.setRole(role);
        this.quantity = quantity;
    }

    public Team getTeam(){
        return id.getTeam();
    }

    public void setTeam(Team team){
        id.setTeam(team);
    }

    public Role getRole(){
        return id.getRole();
    }

    public void setRole(Role role){
        id.setRole(role);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamRole teamRole = (TeamRole) o;

        return Objects.equals(id, teamRole.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
