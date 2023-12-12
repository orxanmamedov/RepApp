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
    private List<Activity> activities;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "date_marks", joinColumns = @JoinColumn(name = "member_id"))
    @MapKeyColumn(name = "date")
    @Column(name = "mark")
    private Map<LocalDate, Double> marks;

    public Member() {
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public void setActivityMap(Map<LocalDate, Double> marks) {
        this.marks = marks;
    }

    public Member(String name) {
        this.name = name;
    }

    public Group getGroup() {
        return group;
    }

    public Map<LocalDate, Double> getMarks() {
        return marks;
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
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
