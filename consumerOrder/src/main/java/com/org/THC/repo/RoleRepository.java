package com.org.THC.repo;

import java.util.Optional;

import com.org.THC.model.ERole;
import com.org.THC.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}