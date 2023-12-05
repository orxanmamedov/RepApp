package com.green.service;

import com.green.dao.MemberDAO;
import com.green.dao.MemberDAOImpl;
import com.green.entity.Activity;
import com.green.entity.Member;

import java.util.List;

public class ServiceImpl implements Service {

    private MemberDAO memberDAO = new MemberDAOImpl();

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
    public List<Activity> getListOfActivity() {
        return null;
    }

    @Override
    public void saveActivity(Activity activity) {

    }

    @Override
    public Activity getActivityById(int id) {
        return null;
    }

    @Override
    public void deleteActivity(int id) {

    }
}
