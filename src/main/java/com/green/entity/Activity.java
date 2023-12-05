package com.green.entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "subject")
    private String subject;
    @Column(name = "took_time")
    private double took_time;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    public Activity() {
    }

    public Activity(LocalDate date, String subject, double took_time) {
        this.date = date;
        this.subject = subject;
        this.took_time = took_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public double getTook_time() {
        return took_time;
    }

    public void setTook_time(double took_time) {
        this.took_time = took_time;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }


}
