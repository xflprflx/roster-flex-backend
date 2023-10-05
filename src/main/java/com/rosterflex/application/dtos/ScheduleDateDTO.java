package com.rosterflex.application.dtos;

import com.rosterflex.application.models.ScheduleDate;

import java.io.Serializable;
import java.time.LocalDate;

public class ScheduleDateDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long id;
    private LocalDate date;
    private boolean holiday;

    public ScheduleDateDTO() {
    }

    public ScheduleDateDTO(Long id, LocalDate date, boolean holiday) {
        this.id = id;
        this.date = date;
        this.holiday = holiday;
    }

    public ScheduleDateDTO(ScheduleDate scheduleDate) {
        this.id = scheduleDate.getId();
        this.date = scheduleDate.getDate();
        this.holiday = scheduleDate.isHoliday();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isHoliday() {
        return holiday;
    }

    public void setHoliday(boolean holiday) {
        this.holiday = holiday;
    }
}