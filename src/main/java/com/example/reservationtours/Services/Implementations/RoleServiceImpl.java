package com.example.reservationtours.Services.Implementations;

import com.example.reservationtours.DAO.Entities.Reservation;
import com.example.reservationtours.DAO.Entities.Role;
import com.example.reservationtours.DAO.Repositories.RoleRepository;
import com.example.reservationtours.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository repo){
        this.roleRepository = repo;
    }

    //************************* implementations *****************

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role editRole(Long id, Role role) throws Exception {
        Optional<Role> roleToEdit = roleRepository.findById(id);
        Role roleFromDb;

        if (roleToEdit.isPresent()){
            roleFromDb = roleToEdit.get();
            roleFromDb.setRoleName(role.getRoleName());
            roleFromDb.setUsers(role.getUsers());
        } else {
            throw new Exception("Reservation was not found : EditRes");
        }

        return roleRepository.save(roleFromDb);
    }

    @Override
    public void DeleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }
}
