package com.example.reservationtours.DAO.Repositories;

import com.example.reservationtours.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String email);
}
