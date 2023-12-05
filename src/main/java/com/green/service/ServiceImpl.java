package com.green.service;

import com.green.dao.ActivityDAO;
import com.green.dao.ActivityDAOImpl;
import com.green.dao.MemberDAO;
import com.green.dao.MemberDAOImpl;
import com.green.entity.Activity;
import com.green.entity.Member;

import java.util.List;

public class ServiceImpl implements Service {

    private MemberDAO memberDAO = new MemberDAOImpl();
    private ActivityDAO activityDAO = new ActivityDAOImpl();

    @Override
    public List<Member> getListOfMembers() {
        return memberDAO.getListOfMembers();
    }

    @Override
    public void saveMember(Member member) {
        memberDAO.saveMember(member);
    }

    @Override
    public Member getMemberById(int id) {
        return memberDAO.getMemberById(id);
    }

    @Override
    public void deleteMember(int id) {
        memberDAO.deleteMember(id);
    }

    @Override
    public List<Activity> getListOfActivities() {
        return activityDAO.getListOfActivities();
    }

    @Override
    public void saveActivity(Activity activity) {
        activityDAO.saveActivity(activity);
    }

    @Override
    public Activity getActivityById(int id) {
        return activityDAO.getActivityById(id);
    }

    @Override
    public void deleteActivity(int id) {
        activityDAO.deleteActivity(id);
    }
}
