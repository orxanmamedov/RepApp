package com.green.dto.member;

import com.green.dto.activity.ActivityResponseDTO;

import java.util.List;

public class MemberResponseDTO {
    private int id;
    private String name;
    private List<ActivityResponseDTO> activities;

    public MemberResponseDTO() {
    }
    public List<ActivityResponseDTO> getActivities() {
        return activities;
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

