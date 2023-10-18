package com.rosterflex.application.models;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User implements Serializable, UserDetails {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String password;
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "schedule_type_id")
    private ScheduleType scheduleType;

    @OneToMany(mappedBy = "id.user")
    private Set<UserScheduleDate> userScheduleDates = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "turn_id")
    private Turn turn;

    @ManyToMany
    @JoinTable(name = "tb_user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    public User() {
    }

    public User(Long id, String username, String fullName, String email, String password, String imgUrl, Turn turn, ScheduleType scheduleType) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.imgUrl = imgUrl;
        this.turn = turn;
        this.scheduleType = scheduleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public ScheduleType getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(ScheduleType scheduleType) {
        this.scheduleType = scheduleType;
    }

    public Set<UserScheduleDate> getUserScheduleDates() {
        return userScheduleDates;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public boolean hasRole(String roleName){
        for (Role role : roles) {
            if (role.getAuthority().equals(roleName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
