package com.rosterflex.application.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table(name = "tb_schedule_type")
public class ScheduleType implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double workedTime;
    private Double freeTime;
    private ChronoUnit unity;
    private Integer monthlyHours;
    private Integer daysOff;

    public ScheduleType() {
    }

    public ScheduleType(Long id, Double workedTime, Double freeTime, ChronoUnit unity, Integer monthlyHours, Integer daysOff) {
        this.id = id;
        this.workedTime = workedTime;
        this.freeTime = freeTime;
        this.unity = unity;
        this.monthlyHours = monthlyHours;
        this.daysOff = daysOff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getWorkedTime() {
        return workedTime;
    }

    public void setWorkedTime(Double workedTime) {
        this.workedTime = workedTime;
    }

    public Double getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Double freeTime) {
        this.freeTime = freeTime;
    }

    public ChronoUnit getUnity() {
        return unity;
    }

    public void setUnity(ChronoUnit unity) {
        this.unity = unity;
    }

    public Integer getMonthlyHours() {
        return monthlyHours;
    }

    public void setMonthlyHours(Integer monthlyHours) {
        this.monthlyHours = monthlyHours;
    }

    public Integer getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(Integer daysOff) {
        this.daysOff = daysOff;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleType that = (ScheduleType) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(workedTime, that.workedTime)) return false;
        if (!Objects.equals(freeTime, that.freeTime)) return false;
        if (unity != that.unity) return false;
        if (!Objects.equals(monthlyHours, that.monthlyHours)) return false;
        return Objects.equals(daysOff, that.daysOff);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (workedTime != null ? workedTime.hashCode() : 0);
        result = 31 * result + (freeTime != null ? freeTime.hashCode() : 0);
        result = 31 * result + (unity != null ? unity.hashCode() : 0);
        result = 31 * result + (monthlyHours != null ? monthlyHours.hashCode() : 0);
        result = 31 * result + (daysOff != null ? daysOff.hashCode() : 0);
        return result;
    }
}
