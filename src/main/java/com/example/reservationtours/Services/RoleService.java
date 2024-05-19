package com.example.reservationtours.Services;

import com.example.reservationtours.DAO.Entities.Role;
import com.example.reservationtours.DAO.Entities.User;

public interface RoleService {
    Role addRole(Role role);
    Role editRole(Long id, Role role) throws Exception;
    void DeleteRole(Long id);
}
