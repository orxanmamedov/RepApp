package com.green.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.entity.Member;
import com.green.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostMemberCommand implements Command {

    private final Service service;
    private final ObjectMapper objectMapper;


    public PostMemberCommand(Service service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        Member member = objectMapper.readValue(request.getReader(), Member.class);
        service.saveMember(member);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }


}
