package com.example.reservationtours.DAO.Repositories;

import com.example.reservationtours.DAO.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByRoleName(String roleName);
}
