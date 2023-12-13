package com.green.service;

import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;

import java.util.List;
import java.util.Map;

public interface MemberService  {

    List<MemberResponseDTO> getListOfMembers(Map<String,String[]> params);
    void saveMember(MemberRequestDTO memberDTO);
    MemberResponseDTO getMemberById(int id);
    void deleteMember(int id);
    void updateMember(int memberId, MemberRequestDTO updatedMemberDTO);

}
