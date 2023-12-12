package com.green.dto.member;

import com.green.dto.activity.ActivityResponseDTO;
import com.green.entity.Group;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class MemberResponseDTO {
    private int id;
    private String name;
    private List<ActivityResponseDTO> activities;
    private Map<LocalDate, Double> activityMap;
    private String nameGroup;

    public MemberResponseDTO() {
    }
    public MemberResponseDTO(int id, String name, List<ActivityResponseDTO> activities, Group nameGroup) {
        this.id = id;
        this.name = name;
        this.activities = activities;
        this.nameGroup = (nameGroup != null) ? nameGroup.getName() : null;
    }
    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
    public List<ActivityResponseDTO> getActivities() {
        return activities;
    }

    public Map<LocalDate, Double> getActivityMap() {
        return activityMap;
    }

    public void setActivityMap(Map<LocalDate, Double> activityMap) {
        this.activityMap = activityMap;
    }

    public void setActivities(List<ActivityResponseDTO> activities) {
        this.activities = activities;
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
}

