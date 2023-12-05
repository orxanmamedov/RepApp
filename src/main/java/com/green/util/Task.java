package com.green.util;

import com.green.dto.MemberActivityDTO;

import java.util.List;


public class Task {
    public static void main(String[] args) {
        List<MemberActivityDTO> dtos = FormatData.inMemberActivityDto();
        System.out.println(dtos);

                for (MemberActivityDTO dto : dtos){
            System.out.println(dto.getMember().getName()
                    + " " + dto.getActivity().getSubject()
                    + " " + dto.getActivity().getTookTime());
        }
    }



}
