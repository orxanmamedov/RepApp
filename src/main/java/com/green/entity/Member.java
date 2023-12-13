package com.green.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "member", fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "member", fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<MemberMark> memberMarks;


    public Member() {
    }

    public Set<MemberMark> getMemberMarks() {
        return memberMarks;
    }

    public void setMemberMarks(Set<MemberMark> memberMarks) {
        this.memberMarks = memberMarks;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Member(String name) {
        this.name = name;
    }
    public Group getGroup() {
        return group;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
