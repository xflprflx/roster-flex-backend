package com.rosterflex.application.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_schedule_date")
public class ScheduleDate implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private boolean holiday;

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
