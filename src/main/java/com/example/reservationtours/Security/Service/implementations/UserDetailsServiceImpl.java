package com.example.reservationtours.Security.Service.implementations;

import com.example.reservationtours.DAO.Entities.User;
import com.example.reservationtours.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userService.findByUsername(username);
        if (u==null) throw new UsernameNotFoundException("User '"+username+"' not found");
        Collection<GrantedAuthority> authorities = u.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getUsername())
                .password(u.getPassword())
                .authorities(authorities)
                .build();
    }
}
