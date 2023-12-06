package com.green.dao;

import com.green.entity.Member;
import java.util.List;

public interface MemberDAO {
     List<Member> getListOfMembers();
     void saveMember(Member member);
     Member getMemberById(int id);
     void deleteMember(int id);




}
