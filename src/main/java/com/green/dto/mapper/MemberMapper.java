package com.green.dto.mapper;

import com.green.dto.activity.ActivityResponseDTO;
import com.green.dto.marks.MemberMarkResponseDTO;
import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
import com.green.entity.Group;
import com.green.entity.MemberMark;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemberMapper {
    private MemberMapper() {
    }

    public static MemberResponseDTO toResponseDTO(Member member) {
        MemberResponseDTO dto = new MemberResponseDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());

        List<ActivityResponseDTO> activityDTOs = member.getActivities().stream()
                .map(ActivityMapper::toResponseDTO)
                .collect(Collectors.toList());
        dto.setActivities(activityDTOs);
        if (member.getGroup() != null) {
            dto.setNameGroup(member.getGroup().getName());
        }
        if (member.getMemberMarks() != null) {
            List<MemberMarkResponseDTO> marksList = member.getMemberMarks().stream()
                    .map(MemberMarkMapper::toResponseDTO)
                    .collect(Collectors.toList());

            dto.setMarks(marksList);
        }

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
        if (dto.getNameGroup() != null) {
            member.setGroup(new Group(dto.getNameGroup()));
        }
        if (dto.getMarks() != null) {
            List<MemberMark> updatedMemberMarks = dto.getMarks().entrySet().stream()
                    .map(entry -> new MemberMark(member, entry.getKey(), entry.getValue()))
                    .collect(Collectors.toList());

            member.getMemberMarks().clear();
            member.getMemberMarks().addAll(updatedMemberMarks);
        }
    }
}