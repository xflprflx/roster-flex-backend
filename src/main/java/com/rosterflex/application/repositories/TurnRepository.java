package com.rosterflex.application.repositories;

import com.rosterflex.application.models.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnRepository extends
        JpaRepository<Turn, Long>,
        RevisionRepository<Turn, Long, Long> {
}
