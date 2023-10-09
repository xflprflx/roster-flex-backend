package com.rosterflex.application.repositories;

import com.rosterflex.application.models.ChangeTurn;
import com.rosterflex.application.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeTurnRepository extends JpaRepository<ChangeTurn, Long> {
}
