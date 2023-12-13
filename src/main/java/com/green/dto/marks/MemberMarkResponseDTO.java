package com.green.dto.marks;

import java.time.LocalDate;

public class MemberMarkResponseDTO {
    private int id;
    private int memberId;
    private LocalDate date;
    private Double mark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public MemberMarkResponseDTO() {
    }

    public MemberMarkResponseDTO(int id, int memberId, LocalDate date, Double mark) {
        this.id = id;
        this.memberId = memberId;
        this.date = date;
        this.mark = mark;
    }
}
