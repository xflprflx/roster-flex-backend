package com.rosterflex.application.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "tb_turn")
public class Turn implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime intervalDuration;

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
