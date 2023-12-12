package com.green.dto.member;

import java.time.LocalDate;
import java.util.Map;

public class MemberRequestDTO {

    private String name;
    private Map<LocalDate, Double> marks;
    private String nameGroup;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getNameGroup() {
        return nameGroup;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }
    public Map<LocalDate, Double> getMarks() {
        return marks;
    }

    public void setMarks(Map<LocalDate, Double> marks) {
        this.marks = marks;
    }
}
