package com.rosterflex.application.dtos;

import com.rosterflex.application.enums.ScheduleStatus;
import com.rosterflex.application.models.WorkSchedule;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDate;

public class WorkScheduleDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long id;
    @FutureOrPresent
    private LocalDate initialDate;
    @Future
    private LocalDate finalDate;
    @NotBlank
    private String description;
    private ScheduleStatus scheduleStatus;

    public WorkScheduleDTO() {
    }

    public WorkScheduleDTO(Long id, LocalDate initialDate, LocalDate finalDate, String description, ScheduleStatus scheduleStatus) {
        this.id = id;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.description = description;
        this.scheduleStatus = scheduleStatus;
    }

    public WorkScheduleDTO(WorkSchedule workSchedule) {
        this.id = workSchedule.getId();
        this.initialDate = workSchedule.getInitialDate();
        this.finalDate = workSchedule.getFinalDate();
        this.description = workSchedule.getDescription();
        this.scheduleStatus = workSchedule.getScheduleStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(LocalDate finalDate) {
        this.finalDate = finalDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScheduleStatus getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(ScheduleStatus scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
