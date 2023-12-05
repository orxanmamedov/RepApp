package com.green.dto;

import com.green.entity.Activity;
import com.green.entity.Member;

public class MemberActivityDTO {
    private Member member;
    private Activity activity;

    // Конструктор, геттеры, сеттеры

    public MemberActivityDTO(Member member, Activity activity) {
        this.member = member;
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "MemberActivityDTO{" +
                "member=" + member +
                ", activity=" + activity +
                '}';
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
