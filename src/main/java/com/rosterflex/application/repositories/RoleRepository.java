package com.rosterflex.application.repositories;

import com.rosterflex.application.models.Role;
import com.rosterflex.application.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
