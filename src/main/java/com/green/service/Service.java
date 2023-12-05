package com.green.service;

import com.green.entity.Activity;
import com.green.entity.Member;

import java.util.List;

public interface Service {

    public List<Member> getListOfMembers();
    public void saveMember(Member member);
    public Member getMemberById(int id);
    public void deleteMember(int id);
    public List<Activity> getListOfActivity();
    public void saveActivity(Activity activity);
    public Activity getActivityById(int id);
    public void deleteActivity(int id);
}
