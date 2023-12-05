package com.green.service;

import com.green.entity.Member;

import java.util.List;

public interface MemberService {

    public List<Member> getListOfMembers();
    public void saveMember(Member member);
    public Member getMemberById(int id);
    public void deleteMember(int id);
}
