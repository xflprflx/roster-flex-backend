package com.rosterflex.application.repositories;

import com.rosterflex.application.models.ScheduleDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ScheduleDateRepository extends JpaRepository<ScheduleDate, Long> {

    boolean existsByDate(LocalDate date);
    ScheduleDate findByDate(LocalDate date);
}
