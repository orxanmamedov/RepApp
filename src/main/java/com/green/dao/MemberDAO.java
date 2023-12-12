package com.green.dao;

import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
import java.util.List;

public interface MemberDAO {
     List<MemberResponseDTO> getListOfMembers();
     void saveMember(MemberRequestDTO memberDTO);
     MemberResponseDTO getMemberById(int id);
     void deleteMember(int id);
     void updateMember(int memberId, MemberRequestDTO updatedMemberDTO);
     List<MemberResponseDTO> getMembersByGroupName(String groupName);

    List<MemberResponseDTO> getListOfMembersWithActivityMap();
}
