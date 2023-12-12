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
    private MemberGroup nameGroup;

    @ElementCollection
    @CollectionTable(name = "member_activities", joinColumns = @JoinColumn(name = "member_id"))
    @MapKeyColumn(name = "activity_date")
    @Column(name = "activity_value")
    @Basic(optional = true)
    private Map<LocalDate, Double> activityMap;


    public Member() {
    }

    public void addActivityToMember(Activity activity) {
        if (this.activities == null) {
            activities = new ArrayList<>();
        }
        activities.add(activity);
        activity.setMember(this);

    }

    public void setNameGroup(MemberGroup nameGroup) {
        this.nameGroup = nameGroup;
    }

    public void setActivityMap(Map<LocalDate, Double> activityMap) {
        this.activityMap = activityMap;
    }

    public Member(String name) {
        this.name = name;
    }

    public MemberGroup getNameGroup() {
        return nameGroup;
    }

    public Map<LocalDate, Double> getActivityMap() {
        return activityMap;
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
