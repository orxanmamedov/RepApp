package com.green.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @Column(name = "id")
    int id;
    @Column(name = "name")
    String name;
    @Column(name = "date")
    Date date;
    @Column(name = "subject")
    String subject;
    @Column(name = "took_time")
    double took_time;

    public Member(String name, Date date, String subject, double took_time) {
        this.name = name;
        this.date = date;
        this.subject = subject;
        this.took_time = took_time;
    }


    public Member() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", subject='" + subject + '\'' +
                ", took_time=" + took_time +
                '}';
    }
}
