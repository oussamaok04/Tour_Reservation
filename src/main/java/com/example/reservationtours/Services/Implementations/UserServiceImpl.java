package com.example.reservationtours.Services.Implementations;

import com.example.reservationtours.DAO.Entities.Role;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.DAO.Repositories.UserRepository;
import com.example.reservationtours.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository repo){
        this.userRepository = repo;
    }

    //************************* implementations *****************

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public User promoteToAdmin(User user) {
        return null;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(Long id, User user) throws Exception {
        Optional<User> userToEdit = userRepository.findById(id);
        User userFromDb;

        if (userToEdit.isPresent()){
            userFromDb = userToEdit.get();
            userFromDb.setEmail(user.getEmail());
            userFromDb.setName(user.getName());
            userFromDb.setRoles(user.getRoles());
            userFromDb.setPassword(user.getPassword());
        } else {
            throw new Exception("User not found!");
        }

        return userRepository.save(userFromDb);
    }

    @Override
    public void DeleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
