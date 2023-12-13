package com.green.dto.member;

import com.green.dto.activity.ActivityResponseDTO;
import com.green.dto.marks.MemberMarkResponseDTO;
import com.green.entity.Group;

import java.util.List;

public class MemberResponseDTO {
    private Integer id;
    private String name;
    private List<ActivityResponseDTO> activities;
    private List<MemberMarkResponseDTO> marks;
    private String nameGroup;

    public MemberResponseDTO() {
    }

    public MemberResponseDTO(Integer id, String name, List<ActivityResponseDTO> activities, Group nameGroup) {
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

    public List<MemberMarkResponseDTO> getMarks() {
        return marks;
    }

    public void setMarks(List<MemberMarkResponseDTO> marks) {
        this.marks = marks;
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

