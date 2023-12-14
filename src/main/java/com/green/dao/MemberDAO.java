package com.green.dao;

import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;

import java.util.List;
import java.util.Map;

public interface MemberDAO {
    List<MemberResponseDTO> getListOfMembers(Map<String, String[]> parameterMap);

    void saveMember(MemberRequestDTO memberDTO);

    MemberResponseDTO getMemberById(int id);

    void deleteMember(int id);

    void updateMember(int memberId, MemberRequestDTO updatedMemberDTO);
}
