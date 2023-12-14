package com.green.controller.activity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.service.ActivityService;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostActivityCommand implements Command {
    private final ActivityService activityService;
    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    public PostActivityCommand(ActivityService service,MemberService memberService, ObjectMapper objectMapper) {
        this.activityService = service;
        this.objectMapper = objectMapper;
        this.memberService =memberService;
        this.objectMapper.registerModule(new JavaTimeModule());

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        ActivityRequestDTO activityDTO = objectMapper.readValue(request.getReader(), ActivityRequestDTO.class);
        MemberResponseDTO memberResponseDTO = memberService.getMemberById(activityDTO.getMemberId());
        activityDTO.setMemberId(memberResponseDTO.getId());
        activityService.saveActivity(activityDTO);
        ControllerUtils.writeResponse(response, null, objectMapper, HttpServletResponse.SC_CREATED);
    }
}
