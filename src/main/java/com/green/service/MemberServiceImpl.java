package com.green.service;

import com.green.dao.MemberDAO;
import com.green.dao.MemberDAOImpl;
import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberServiceImpl implements MemberService{
    private final MemberDAO memberDAO = new MemberDAOImpl();
    @Override
    public List<MemberResponseDTO> getListOfMembers(Map<String,String[]> params) {

        return memberDAO.getListOfMembers(params);
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
