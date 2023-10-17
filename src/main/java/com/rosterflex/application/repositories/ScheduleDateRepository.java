package com.rosterflex.application.repositories;

import com.rosterflex.application.models.ScheduleDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ScheduleDateRepository extends JpaRepository<ScheduleDate, Long> {

    boolean existsByDate(LocalDate date);
}
