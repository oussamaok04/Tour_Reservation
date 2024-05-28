package com.example.reservationtours.Services.Implementations;

import com.example.reservationtours.DAO.Entities.Role;
import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.DAO.Repositories.UserRepository;
import com.example.reservationtours.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

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
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public User editUser(Long id, User user) throws Exception {
        Optional<User> userToEdit = userRepository.findById(id);
        User userFromDb;

        if (userToEdit.isPresent()){
            userFromDb = userToEdit.get();
            userFromDb.setEmail(user.getEmail());
            userFromDb.setUsername(user.getUsername());
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

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
//        for (User u: users) {
//            for (Role r: u.getRoles()) {
//                if (r.getRoleName().equals("ADMIN")){
//                    users.remove(u);
//                }
//            }
//        }
        return users;
    }
}
