package com.rosterflex.application.repositories;

import com.rosterflex.application.models.Role;
import com.rosterflex.application.models.TeamRole;
import com.rosterflex.application.models.pk.TeamRolePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRoleRepository extends JpaRepository<TeamRole, TeamRolePK> {
}
