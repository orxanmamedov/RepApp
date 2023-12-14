package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.service.MemberMarkService;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostMarkCommand implements Command {
    private final MemberMarkService memberMarkService;
    private final MemberService memberService;
    private final ObjectMapper objectMapper;

    public PostMarkCommand(MemberMarkService memberMarkService, MemberService memberService, ObjectMapper objectMapper) {
        this.memberMarkService = memberMarkService;
        this.memberService = memberService;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        MemberMarkRequestDTO memberMarkDTO = objectMapper.readValue(request.getReader(), MemberMarkRequestDTO.class);
        MemberResponseDTO memberResponseDTO = memberService.getMemberById(memberMarkDTO.getMemberId());
        int code = HttpServletResponse.SC_CREATED;
        if (memberResponseDTO != null) {
            memberMarkDTO.setMemberId(memberResponseDTO.getId());
            memberMarkService.saveMemberMark(memberMarkDTO, memberMarkDTO.getMemberId());

        } else {
            code = HttpServletResponse.SC_BAD_REQUEST;
        }
        ControllerUtils.writeResponse(response, null, objectMapper, code);
    }
}
