package com.todolist.mapper;

import com.todolist.dto.*;
import com.todolist.entity.*;
import com.todolist.service.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class Mapper {
    UserService userService;
    TeamService teamService;
    TaskService taskService;
    MessageService messageService;
    UserAndTeamService userAndTeamService;

    public Mapper(UserService userService, TeamService teamService, TaskService taskService, MessageService messageService, UserAndTeamService userAndTeamService) {
        this.userService = userService;
        this.teamService = teamService;
        this.taskService = taskService;
        this.messageService = messageService;
        this.userAndTeamService = userAndTeamService;
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setNickName(user.getNickName());
        userDTO.setId(user.getUserId());
        return userDTO;
    }

    public TeamDTO toDTO(Team team){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setTeamName(team.getTeamName());
        teamDTO.setTeamDescription(team.getTeamDescription());
        teamDTO.setId(team.getTeamId());
        teamDTO.setParents(team.getParents().stream()
                .map(Team::getTeamId)
                .collect(Collectors.toList())
        );
        teamDTO.setChildren(team.getChildren().stream()
                .map(Team::getTeamId)
                .collect(Collectors.toList())
        );
        return teamDTO;
    }

    public TaskDTO toDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setTaskNote(task.getNote());
        taskDTO.setId(task.getTaskId());
        taskDTO.setUserId(task.getUser().getUserId());
        taskDTO.setUseAndTeam(task.getUserAndTeam().stream()
                .map(UserAndTeam::getId)
                .collect(Collectors.toList())
        );
        return taskDTO;
    }

    public MessageDTO toDTO(Message message){
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message.getText());
        messageDTO.setId(message.getMessageId());
        messageDTO.setUserTeamId(message.getUserAndTeam().getId());
        return messageDTO;
    }

    public UserAuthDTO toDTO(UserAuth userAuth) {
        UserAuthDTO userAuthDTO = new UserAuthDTO();
        userAuthDTO.setEmail(userAuth.getEmail());
        userAuthDTO.setPassword(userAuth.getPassword());
        userAuthDTO.setId(userAuth.getUserId());
        userAuthDTO.setRole(userAuth.getRole());
        return userAuthDTO;
    }

    public UserAndTeamDTO toDTO(UserAndTeam userAndTeam) {
        UserAndTeamDTO userAndTeamDTO = new UserAndTeamDTO();
        userAndTeamDTO.setId(userAndTeam.getId());
        userAndTeamDTO.setUserId(userAndTeam.getUser().getUserId());
        userAndTeamDTO.setTeamId(userAndTeam.getTeam().getTeamId());
        userAndTeamDTO.setTasks(userAndTeam.getTasks().stream()
                .map(Task::getTaskId)
                .collect(Collectors.toList())
        );
        return  userAndTeamDTO;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setNickName(userDTO.getNickName());
        user.setUserId(userDTO.getId());
        return user;
    }

    public Team toTeam(TeamDTO teamDTO) {
        Team team = new Team();
        team.setTeamDescription(teamDTO.getTeamDescription());
        team.setTeamName(teamDTO.getTeamName());
        team.setTeamId(teamDTO.getId());
        team.setChildren(teamDTO.getChildren().stream()
                .map(a -> teamService.findTeamById(a))
                .collect(Collectors.toList())
        );
        team.setParents(teamDTO.getParents().stream()
                .map(a -> teamService.findTeamById(a))
                .collect(Collectors.toList())
        );
        return team;
    }

    public UserAuth toUserAuth(UserAuthDTO userAuthDTO) {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(userAuthDTO.getEmail());
        userAuth.setPassword(userAuthDTO.getPassword());
        userAuth.setRole(userAuthDTO.getRole());
        userAuth.setUser(userService.findUserById(userAuthDTO.getId()));
        return userAuth;
    }

    public Task toTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setTaskName(taskDTO.getTaskNote());
        task.setTaskId(taskDTO.getId());
        task.setTaskName(taskDTO.getTaskName());
        task.setUser(userService.findUserById(taskDTO.getUserId()));
        task.setUserAndTeam(taskDTO.getUseAndTeam().stream()
                .map(a -> userAndTeamService.findUserAndTeam(a))
                .collect(Collectors.toList())
        );
        return task;
    }

    public UserAndTeam toUserAndTeam(UserAndTeamDTO userAndTeamDTO) {
        UserAndTeam userAndTeam = new UserAndTeam();
        userAndTeam.setTeam(teamService.findTeamById(userAndTeamDTO.getTeamId()));
        userAndTeam.setUser(userService.findUserById(userAndTeamDTO.getUserId()));
        userAndTeam.setTasks(userAndTeamDTO.getTasks().stream()
                .map(a -> taskService.findTaskById(a))
                .collect(Collectors.toList())
        );
        return userAndTeam;
    }

    public Message toMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setMessageId(messageDTO.getId());
        message.setText(messageDTO.getMessage());
        message.setUserAndTeam(userAndTeamService.findUserAndTeam(messageDTO.getUserTeamId()));
        return message;
    }
}
