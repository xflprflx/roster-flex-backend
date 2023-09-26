package com.rosterflex.application.repositories;

import com.rosterflex.application.models.ScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleTypeRepository extends JpaRepository<ScheduleType, Long> {
}
