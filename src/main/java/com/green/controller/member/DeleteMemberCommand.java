package com.green.controller.member;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.dto.member.MemberRequestDTO;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMemberCommand implements Command {
    private final MemberService service;
    private final ObjectMapper objectMapper;

    public DeleteMemberCommand(MemberService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        MemberRequestDTO memberRequestDTO = objectMapper.readValue(request.getReader(), MemberRequestDTO.class);
        if (memberRequestDTO.getId() != 0) {
            int memberId = memberRequestDTO.getId();
            service.deleteMember(memberId);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
