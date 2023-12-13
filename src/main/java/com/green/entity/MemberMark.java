package com.green.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "member_marks")
public class MemberMark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "mark")
    private Double mark;

    public MemberMark() {
    }

    public MemberMark(Member member, Double mark) {
        this.member = member;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public LocalDate getDate() {
        return date;
    }

    public Double getMark() {
        return mark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }
}

