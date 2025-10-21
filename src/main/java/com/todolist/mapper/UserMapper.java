package com.todolist.mapper;

import com.todolist.dto.request.UserRequest;
import com.todolist.dto.response.UserResponse;
import com.todolist.entity.User;
import com.todolist.entity.UserAuth;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public UserResponse toDTO(User user, UserAuth userAuth) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setNickName(user.getNickName());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(userAuth.getEmail());
        return userResponse;
    }

    public User toUser(UserRequest userRequest){
        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setNickName(userRequest.getNickName());
        return user;
    }

    public UserAuth toAuth(UserRequest userRequest){
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(userRequest.getEmail());
        userAuth.setPassword(userRequest.getPassword());
        return userAuth;
    }
}
