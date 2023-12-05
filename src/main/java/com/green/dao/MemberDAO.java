package com.green.dao;

import com.green.entity.Activity;
import com.green.entity.Member;

import java.util.List;

public interface MemberDAO {
    public List<Member> getListOfMembers();
    public void saveMember(Member member);
    public Member getMemberById(int id);
    public void deleteMember(int id);




}
