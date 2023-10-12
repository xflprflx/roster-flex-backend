package com.rosterflex.application.models;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_turn")
@Audited
public class Turn implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime intervalDuration;

    @OneToMany(mappedBy = "turn")
    @NotAudited
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "turn")
    @NotAudited
    private Set<UserScheduleDate> userScheduleDates = new HashSet<>();

    public Turn() {
    }

    public Turn(Long id, LocalTime startTime, LocalTime endTime, LocalTime intervalDuration) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalDuration = intervalDuration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getIntervalDuration() {
        return intervalDuration;
    }

    public void setIntervalDuration(LocalTime intervalDuration) {
        this.intervalDuration = intervalDuration;
    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<UserScheduleDate> getUserScheduleDates() {
        return userScheduleDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Turn turn = (Turn) o;

        return Objects.equals(id, turn.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
