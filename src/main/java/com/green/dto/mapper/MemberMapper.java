package com.green.dto.mapper;

import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;

public class MemberMapper {
    private MemberMapper(){}

    public static MemberResponseDTO toResponseDTO(Member member) {
        MemberResponseDTO dto = new MemberResponseDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        return dto;
    }

    public static Member fromRequestDTO(MemberRequestDTO dto) {
        Member member = new Member();
        member.setName(dto.getName());
        return member;
    }

    public static void updateFromRequestDTO(Member member, MemberRequestDTO dto) {
        if (dto.getName() != null) {
            member.setName(dto.getName());
        }
    }
}

