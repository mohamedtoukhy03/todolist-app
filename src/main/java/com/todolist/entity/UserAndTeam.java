package com.todolist.entity;

import com.todolist.entity.id.UserTeamId;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_team")
public class UserAndTeam {
    @EmbeddedId
    private UserTeamId id;

    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAndTeam")
//    private List<Message> messages;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinTable(
            name = "user_team_tasks",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
                    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
            },
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks;


    // addition sync method
//    public void  addMessage(Message message) {
//        if (this.messages == null) {
//            this.messages = new ArrayList<>();
//        }
//        this.messages.add(message);
//        message.setUserAndTeam(this);
//    }

    public void  addTask(Task task) {
        if (this.tasks == null) {
            this.tasks = new ArrayList<>();
        }
        this.tasks.add(task);
        task.getUserAndTeam().add(this);
    }

    // deletion sync method

    // deletion sync method


    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public UserAndTeam() {}

    public UserAndTeam(Team team, User user) {
        this.team = team;
        this.user = user;
    }

//    public List<Message> getMessages() {
//        return messages;
//    }
//
//    public void setMessages(List<Message> messages) {
//        this.messages = messages;
//    }



    public UserTeamId getId() {
        return id;
    }

    public void setId(UserTeamId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
