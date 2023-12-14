package com.green.controller.member;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.member.MemberRequestDTO;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutMemberCommand implements Command {
    private final MemberService service;
    private final ObjectMapper objectMapper;

    public PutMemberCommand(MemberService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        MemberRequestDTO member = objectMapper.readValue(request.getReader(), MemberRequestDTO.class);
        int code = HttpServletResponse.SC_OK;
        if (member.getId() != 0) {
            service.updateMember(member.getId(), member);
        } else {
            code = HttpServletResponse.SC_BAD_REQUEST;
        }
        ControllerUtils.writeResponse(response, null, objectMapper, code);
    }
}
