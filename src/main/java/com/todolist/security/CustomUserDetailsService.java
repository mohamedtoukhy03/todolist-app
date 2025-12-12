package com.todolist.security;

import com.todolist.dao.UserDAO;
import com.todolist.entity.User;
import com.todolist.entity.UserAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    public CustomUserDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDAO.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        UserAuth userAuth = user.getUserAuth();
        if (userAuth == null) {
            throw new UsernameNotFoundException("User authentication details not found for email: " + email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        // Use role from database or default to ROLE_USER
        String role = userAuth.getRole() != null ? userAuth.getRole() : "ROLE_USER";
        authorities.add(new SimpleGrantedAuthority(role));

        return org.springframework.security.core.userdetails.User.builder()
                .username(userAuth.getEmail())
                .password(userAuth.getPassword())
                .authorities(authorities)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
