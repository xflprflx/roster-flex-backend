package com.rosterflex.application.dtos;

import com.rosterflex.application.models.ScheduleType;

import java.io.Serializable;
import java.time.temporal.ChronoUnit;

public class ScheduleTypeDTO implements Serializable {
    private static  final long serialVersionUID =1L;

    private Long id;
    private String name;
    private Double workedTime;
    private Double freeTime;
    private ChronoUnit unity;
    private Integer monthlyHours;
    private Integer daysOff;

    public ScheduleTypeDTO() {
    }

    public ScheduleTypeDTO(Long id, String name, Double workedTime, Double freeTime, ChronoUnit unity, Integer monthlyHours, Integer daysOff) {
        this.id = id;
        this.name = name;
        this.workedTime = workedTime;
        this.freeTime = freeTime;
        this.unity = unity;
        this.monthlyHours = monthlyHours;
        this.daysOff = daysOff;
    }

    public ScheduleTypeDTO(ScheduleType scheduleType) {
        this.id = scheduleType.getId();
        this.name = scheduleType.getName();
        this.workedTime = scheduleType.getWorkedTime();
        this.freeTime = scheduleType.getFreeTime();
        this.unity = scheduleType.getUnity();
        this.monthlyHours = scheduleType.getMonthlyHours();
        this.daysOff = scheduleType.getDaysOff();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
