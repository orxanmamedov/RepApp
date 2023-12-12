package com.green.dto.mapper;

import com.green.dto.activity.ActivityResponseDTO;
import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
import com.green.entity.MemberGroup;

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
        if (member.getNameGroup() != null) {
            dto.setNameGroup(member.getNameGroup().getGroupName());
        }
        if (member.getActivityMap() != null) {
            dto.setActivityMap(new HashMap<>(member.getActivityMap()));
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
            member.setNameGroup(new MemberGroup(dto.getNameGroup()));
        }
        if (dto.getActivityMap() != null) {
            member.setActivityMap(new HashMap<>(dto.getActivityMap()));
        }
    }

    public static MemberResponseDTO mapMemberWithActivityMap(Member member) {
        MemberResponseDTO dto = MemberMapper.toResponseDTO(member);
        Map<LocalDate, Double> activityMap = member.getActivityMap();
        dto.setActivityMap(activityMap != null ? new HashMap<>(activityMap) : null);

        return dto;
    }

}