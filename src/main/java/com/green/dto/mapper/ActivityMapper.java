package com.green.dto.mapper;

import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.entity.Activity;
import com.green.entity.Member;

public class ActivityMapper {

    private ActivityMapper(){}

    public static ActivityResponseDTO toResponseDTO(Activity activity) {
        ActivityResponseDTO dto = new ActivityResponseDTO();
        dto.setId(activity.getId());
        dto.setDate(activity.getDate());
        dto.setSubject(activity.getSubject());
        dto.setTookTime(activity.getTookTime());
        dto.setMemberId(activity.getMember().getId());
        return dto;
    }

    public static Activity fromRequestDTO(ActivityRequestDTO dto, Member member) {
        Activity activity = new Activity();
        activity.setDate(dto.getDate());
        activity.setSubject(dto.getSubject());
        activity.setTookTime(dto.getTookTime());
        activity.setMember(member);
        return activity;
    }

    public static void updateFromRequestDTO(Activity activity, ActivityRequestDTO dto) {
            activity.setDate(dto.getDate());
            activity.setSubject(dto.getSubject());
            activity.setTookTime(dto.getTookTime());
        }

//        if (dto.getDate() != null) {
//            activity.setDate(dto.getDate());
//        }
//
//        if (dto.getSubject() != null) {
//            activity.setSubject(dto.getSubject());
//        }
//
//        if (dto.getTookTime() != 0.0) {
//            activity.setTookTime(dto.getTookTime());
//        }
//    }
}
