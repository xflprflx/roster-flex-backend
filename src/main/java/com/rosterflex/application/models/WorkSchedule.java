package com.rosterflex.application.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rosterflex.application.enums.ScheduleStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_work_schedule")
public class WorkSchedule implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate initialDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finalDate;
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ScheduleStatus scheduleStatus;

    @ManyToMany(mappedBy = "workSchedules")
    private Set<ScheduleDate> scheduleDates = new HashSet<>();

    public WorkSchedule() {
    }

    public WorkSchedule(Long id, LocalDate initialDate, LocalDate finalDate, String description, ScheduleStatus scheduleStatus) {
        this.id = id;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.description = description;
        this.scheduleStatus = scheduleStatus;
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

    public Set<ScheduleDate> getScheduleDates() {
        return scheduleDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkSchedule that = (WorkSchedule) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
