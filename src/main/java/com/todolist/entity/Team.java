package com.todolist.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Integer teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "description")
    private String teamDescription;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "team")
    private List<UserAndTeam> userAndTeam;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
     CascadeType.REFRESH})
    @JoinColumn(name = "parent_id")
    private Team parent;


    public void addUserAndTeam(UserAndTeam userTeam) {
        if (userAndTeam == null) {
            userAndTeam = new ArrayList<>();
        }
        userAndTeam.add(userTeam);
        userTeam.setTeam(this);
    }

    public List<UserAndTeam> getUserAndTeam() {
        return userAndTeam;
    }

    public void setUserAndTeam(List<UserAndTeam> userAndTeam) {
        this.userAndTeam = userAndTeam;
    }

    public Team getParent() {
        return parent;
    }

    public void setParent(Team parent) {
        this.parent = parent;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }
}
