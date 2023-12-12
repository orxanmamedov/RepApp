package com.green.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.dto.member.MemberRequestDTO;
import com.green.entity.Member;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostMemberCommand implements Command {

    private final MemberService service;
    private final ObjectMapper objectMapper;


    public PostMemberCommand(MemberService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        MemberRequestDTO member = objectMapper.readValue(request.getReader(), MemberRequestDTO.class);
        service.saveMember(member);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
