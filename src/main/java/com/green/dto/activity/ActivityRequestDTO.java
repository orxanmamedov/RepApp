package com.green.dto.activity;


import java.time.LocalDate;

public class ActivityRequestDTO {
    private LocalDate date;
    private String subject;
    private double tookTime;
    private int memberId;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getTookTime() {
        return tookTime;
    }

    public void setTookTime(double tookTime) {
        this.tookTime = tookTime;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
