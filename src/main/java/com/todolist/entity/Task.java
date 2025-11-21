package com.todolist.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY,  cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "tasks")
    private List<UserAndTeam> userAndTeam;

    public void addUserAndTeam(UserAndTeam userAndTeam) {
        if (this.userAndTeam == null) {
            this.userAndTeam = new ArrayList<>();
        }
        this.userAndTeam.add(userAndTeam);
        userAndTeam.getTasks().add(this);
    }

    public List<UserAndTeam> getUserAndTeam() {
        return userAndTeam;
    }

    public void setUserAndTeam(List<UserAndTeam> userAndTeams) {
        this.userAndTeam= userAndTeams;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
