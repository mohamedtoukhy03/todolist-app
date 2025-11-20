package com.todolist.dto.request;

import jakarta.validation.constraints.*;

public class UserRequest {
    @NotBlank(message = "Nickname is required.")
    private String nickName;

    @NotBlank(message = "Username is required.")
    private String userName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email must be a valid format.")
    private String email;

    @NotBlank(message = "Password is required.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=])[A-Za-z\\d!@#$%^&+=]{8,30}$",
            message = "Password must be 8-30 characters long and include: 1 Uppercase, 1 Lowercase, 1 Digit, and 1 Special Character."
    )
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
