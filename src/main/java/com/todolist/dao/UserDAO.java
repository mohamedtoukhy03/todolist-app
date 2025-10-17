package com.todolist.dao;


import com.todolist.entity.*;

import java.util.List;

public interface UserDAO {
    // CRUD features
    public User createUser(User user);
    public User findUserByNickName(String nickName);
    public User findUserById(Integer id);
    public User updateUser(User user);
    public void deleteUserById(Integer id);
    public void deleteUserByNickName(String nickName);
    public UserAuth findUserAuthByUserId(Integer id);
    public List<Task> findIndividualTaskByUserId(Integer id);
    public List<Team> findTeamByUserId(Integer id);
    public List<Task> findTeamTaskByUserId(Integer id);
    public List<Message> findMessageByUserId(Integer id);
}
