package com.green.service;

import com.green.dao.MemberDAO;
import com.green.dao.MemberDAOImpl;
import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;

import java.util.List;

public class MemberServiceImpl implements MemberService{
    private final MemberDAO memberDAO = new MemberDAOImpl();
    @Override
    public List<MemberResponseDTO> getListOfMembers() {
        return memberDAO.getListOfMembers();
    }

    @Override
    public void saveMember(MemberRequestDTO member) {
        memberDAO.saveMember(member);
    }

    @Override
    public MemberResponseDTO getMemberById(int id) {
        return memberDAO.getMemberById(id);
    }

    @Override
    public void deleteMember(int id) {
        memberDAO.deleteMember(id);
    }

    public void updateMember(int memberId, MemberRequestDTO updatedMemberDTO){memberDAO.updateMember(memberId,updatedMemberDTO);}

}
