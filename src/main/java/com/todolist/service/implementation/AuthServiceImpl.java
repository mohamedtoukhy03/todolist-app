package com.todolist.service.implementation;

import com.todolist.dao.UserDAO;
import com.todolist.dto.request.LoginRequest;
import com.todolist.dto.request.RegisterRequest;
import com.todolist.dto.response.AuthResponse;
import com.todolist.entity.User;
import com.todolist.entity.UserAuth;
import com.todolist.exception.DuplicateEntityException;
import com.todolist.exception.UnauthorizedException;
import com.todolist.security.JwtUtil;
import com.todolist.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder, 
                          JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        // Check if user already exists
        if (userDAO.findUserByEmail(registerRequest.getEmail()).isPresent()) {
            throw new DuplicateEntityException("User with email " + registerRequest.getEmail() + " already exists");
        }

        // Create new user
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setNickName(registerRequest.getNickName());

        // Create user auth
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(registerRequest.getEmail());
        userAuth.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        
        user.addUserAuth(userAuth);

        // Save user (cascades to userAuth)
        User savedUser = userDAO.createUser(user);

        // Generate JWT token
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getUserId());
        claims.put("userName", savedUser.getUserName());
        String token = jwtUtil.generateToken(savedUser.getUserAuth().getEmail(), claims);

        return new AuthResponse(
            token,
            savedUser.getUserId(),
            savedUser.getUserAuth().getEmail(),
            savedUser.getUserName(),
            savedUser.getNickName()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            // Get user details
            User user = userDAO.findUserByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

            // Generate JWT token
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", user.getUserId());
            claims.put("userName", user.getUserName());
            String token = jwtUtil.generateToken(loginRequest.getEmail(), claims);

            return new AuthResponse(
                token,
                user.getUserId(),
                user.getUserAuth().getEmail(),
                user.getUserName(),
                user.getNickName()
            );

        } catch (AuthenticationException e) {
            throw new UnauthorizedException("Invalid email or password");
        }
    }
}
