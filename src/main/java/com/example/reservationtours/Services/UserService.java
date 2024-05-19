package com.example.reservationtours.Services;

import com.example.reservationtours.DAO.Entities.User;

public interface UserService {
    User register(User user);
    User promoteToAdmin(User user);
    User addUser(User user);
    User editUser(Long id, User user) throws Exception;
    void DeleteUser(Long id);
}
