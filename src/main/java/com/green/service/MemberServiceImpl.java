package com.green.service;

import com.green.dao.MemberDAO;
import com.green.dao.MemberDAOImpl;
import com.green.entity.Member;

import java.util.List;

public class MemberServiceImpl implements MemberService{

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
}
