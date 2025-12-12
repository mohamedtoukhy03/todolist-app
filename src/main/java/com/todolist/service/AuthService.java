package com.todolist.service;

import com.todolist.dto.request.LoginRequest;
import com.todolist.dto.request.RegisterRequest;
import com.todolist.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest registerRequest);
    AuthResponse login(LoginRequest loginRequest);
}
