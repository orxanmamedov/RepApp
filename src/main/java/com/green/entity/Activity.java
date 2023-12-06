package com.green.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.TreeSet;


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
    private double tookTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    @JsonBackReference
    private Member member;

    public Activity() {
    }
    @PrePersist
    public void prePersist(){
        this.date = LocalDate.now();
    }
    public Activity(String subject, double tookTime) {
        this.subject = subject;
        this.tookTime = tookTime;
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

    public double getTookTime() {
        return tookTime;
    }

    public void setTookTime(double took_time) {
        this.tookTime = took_time;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", date=" + date +
                ", subject='" + subject + '\'' +
                ", tookTime=" + tookTime +
                ", member=" + member +

                '}';
    }
}
