package com.rosterflex.application.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_schedule_date")
public class ScheduleDate implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean holiday;

    @OneToMany(mappedBy = "id.scheduleDate")
    private Set<UserScheduleDate> userScheduleDates = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_schedule_date_work_schedule",
            joinColumns = @JoinColumn(name = "schedule_date_id"),
            inverseJoinColumns = @JoinColumn(name = "work_schedule_id"))
    private Set<WorkSchedule> workSchedules = new HashSet<>();

    public ScheduleDate() {
    }

    public ScheduleDate(Long id, LocalDate date, boolean holiday) {
        this.id = id;
        this.date = date;
        this.holiday = holiday;
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

    public Set<WorkSchedule> getWorkSchedules() {
        return workSchedules;
    }

    public Set<UserScheduleDate> getUserScheduleDates() {
        return userScheduleDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleDate that = (ScheduleDate) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
