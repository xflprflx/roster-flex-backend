package com.rosterflex.application.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_team")
public class Team implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "id.team")
    private Set<TeamRole> teamRoles = new HashSet<>();

    public Team() {
    }

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public Set<TeamRole> getTeamRoles() {
        return teamRoles;
    }

    public List<Role> getRoles(){
        return teamRoles.stream().map(x -> x.getRole()).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
