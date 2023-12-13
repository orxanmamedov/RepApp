package com.green.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GetMemberCommand implements Command {
    private final MemberService service;
    private final ObjectMapper objectMapper;


    public GetMemberCommand(MemberService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {

        List<MemberResponseDTO> members = service.getListOfMembers(request.getParameterMap());
        if (!members.isEmpty()) {
            ControllerUtils.writeJsonResponse(response, members, objectMapper);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}


