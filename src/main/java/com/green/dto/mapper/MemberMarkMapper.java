package com.green.dto.mapper;

import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.dto.marks.MemberMarkResponseDTO;
import com.green.entity.Member;
import com.green.entity.MemberMark;

import java.util.Objects;

public class MemberMarkMapper {

    private MemberMarkMapper() {}

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
        if (Objects.nonNull(dto.getDate())) {
            memberMark.setDate(dto.getDate());
        }

        if (Objects.nonNull(dto.getMark())) {
            memberMark.setMark(dto.getMark());
        }
    }
}
