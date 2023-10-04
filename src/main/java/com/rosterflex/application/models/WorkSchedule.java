package com.rosterflex.application.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_work_schedule")
public class WorkSchedule implements Serializable {
    private static  final long serialVersionUID =1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate initialDate;
    private LocalDate finalDate;
    private String description;

    public WorkSchedule() {
    }

    public WorkSchedule(Long id, LocalDate initialDate, LocalDate finalDate, String description) {
        this.id = id;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
        this.description = description;
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
