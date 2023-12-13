package com.green.dto.mapper;

import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.dto.marks.MemberMarkResponseDTO;
import com.green.entity.Member;
import com.green.entity.MemberMark;

public class MemberMarkMapper {

    private MemberMarkMapper() {
    }

    public static MemberMarkResponseDTO toResponseDTO(MemberMark memberMark) {
        MemberMarkResponseDTO dto = new MemberMarkResponseDTO();
        dto.setId(memberMark.getId());
        dto.setMemberId(memberMark.getMember().getId());
        dto.setDate(memberMark.getDate());
        dto.setMark(memberMark.getMark());
        return dto;
    }

    public static MemberMark fromRequestDTO(MemberMarkRequestDTO dto, Member member) {
        MemberMark memberMark = new MemberMark();
        memberMark.setMember(member);
        memberMark.setDate(dto.getDate());
        memberMark.setMark(dto.getMark());
        return memberMark;
    }

    public static void updateFromRequestDTO(MemberMark memberMark, MemberMarkRequestDTO dto) {
        if (dto.getDate() != null) {
            memberMark.setDate(dto.getDate());
        }
        if (dto.getMark() != null) {
            memberMark.setMark(dto.getMark());
        }
    }
}
