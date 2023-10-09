package com.rosterflex.application.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rosterflex.application.models.Turn;
import com.rosterflex.application.models.User;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TurnDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime intervalDuration;

    public TurnDTO() {
    }

    public TurnDTO(Long id, LocalTime startTime, LocalTime endTime, LocalTime intervalDuration) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.intervalDuration = intervalDuration;
    }

    public TurnDTO(Turn turn) {
        this.id = turn.getId();
        this.startTime = turn.getStartTime();
        this.endTime = turn.getEndTime();
        this.intervalDuration = turn.getIntervalDuration();
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

}
