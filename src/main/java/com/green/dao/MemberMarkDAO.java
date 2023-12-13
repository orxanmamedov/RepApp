package com.green.dao;

import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.dto.marks.MemberMarkResponseDTO;

import java.util.List;
import java.util.Map;

public interface MemberMarkDAO {
    List<MemberMarkResponseDTO> getListOfMemberMarks(Map<String, String[]> params);
    void saveMemberMark(MemberMarkRequestDTO memberMarkDTO, int memberId);
    MemberMarkResponseDTO getMemberMarkById(int id);
    void deleteMemberMark(int id);
    void updateMemberMark(int memberMarkId, MemberMarkRequestDTO updatedMemberMarkDTO);
}
