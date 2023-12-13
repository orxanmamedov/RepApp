package com.green.service;

import com.green.dao.MemberMarkDAO;
import com.green.dao.MemberMarkDAOImpl;
import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.dto.marks.MemberMarkResponseDTO;

import java.util.List;
import java.util.Map;

public class MemberMarkServiceImpl implements MemberMarkService {
    private final MemberMarkDAO memberMarkDAO = new MemberMarkDAOImpl();

    @Override
    public List<MemberMarkResponseDTO> getListOfMemberMarks(Map<String, String[]> parameterMap) {
        return memberMarkDAO.getListOfMemberMarks(parameterMap);
    }

    @Override
    public void saveMemberMark(MemberMarkRequestDTO memberMarkDTO, int memberId) {
        memberMarkDAO.saveMemberMark(memberMarkDTO, memberId);
    }

    @Override
    public MemberMarkResponseDTO getMemberMarkById(int id) {
        return memberMarkDAO.getMemberMarkById(id);
    }

    @Override
    public void deleteMemberMark(int id) {
        memberMarkDAO.deleteMemberMark(id);
    }

    @Override
    public void updateMemberMark(int memberMarkId, MemberMarkRequestDTO updatedMemberMarkDTO) {
        memberMarkDAO.updateMemberMark(memberMarkId, updatedMemberMarkDTO);
    }
}
