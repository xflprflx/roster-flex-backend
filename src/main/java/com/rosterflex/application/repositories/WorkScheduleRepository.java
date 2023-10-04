package com.rosterflex.application.repositories;

import com.rosterflex.application.models.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {
}
