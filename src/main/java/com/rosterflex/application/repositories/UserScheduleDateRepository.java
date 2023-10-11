package com.rosterflex.application.repositories;

import com.rosterflex.application.models.UserScheduleDate;
import com.rosterflex.application.models.pk.UserScheduleDatePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserScheduleDateRepository extends JpaRepository<UserScheduleDate, UserScheduleDatePK> {
}
