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

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
//    private List<UserAndTeam> usersAndTeam;

//    public List<UserAndTeam> getUsersAndTeam() {
//        return usersAndTeam;
//    }
//
//    public void setUsersAndTeam(List<UserAndTeam> usersAndTeam) {
//        this.usersAndTeam = usersAndTeam;
//    }

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
     CascadeType.REFRESH})
    @JoinTable(
            name = "team_relations",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id")
    )
    private List<Team> parents;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, mappedBy = "parents")
    private List<Team> children;


    // addition sync method
    public void addParent(Team team) {
        if (this.parents == null) {
            this.parents = new ArrayList<>();
        }
        this.parents.add(team);
        team.getChildren().add(this);
    }

    // addition sync method
    public void addChild(Team team) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(team);
        team.getParents().add(this);
    }

    // addition sync method
//    public void addUserAndTeam(UserAndTeam userAndTeam) {
//        if (this.usersAndTeam == null){
//            this.usersAndTeam = new ArrayList<>();
//        }
//        this.usersAndTeam.add(userAndTeam);
//        userAndTeam.setTeam(this);
//    }

    // deletion sync method

    // deletion sync method

    // deletion sync method

    public List<Team> getChildren() {
        return children;
    }

    public void setChildren(List<Team> children) {
        this.children = children;
    }

    public List<Team> getParents() {
        return parents;
    }

    public void setParents(List<Team> parents) {
        this.parents = parents;
    }

    public Team() {}

    public Team(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
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
