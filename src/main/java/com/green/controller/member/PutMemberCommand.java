package com.green.controller.member;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.entity.Member;
import com.green.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutMemberCommand implements Command {
    private final Service service;
    private final ObjectMapper objectMapper;

    public PutMemberCommand(Service service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        String pathInfo = request.getPathInfo();
        int memberId = ControllerUtils.extractIdFromPath(pathInfo);
        Member existingMember = service.getMemberById(memberId);

        if (existingMember != null) {
            handleUpdateMember(request, response, memberId);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleUpdateMember(HttpServletRequest request, HttpServletResponse response, int memberId)
            throws IOException {
        Member updatedMember = objectMapper.readValue(request.getReader(), Member.class);
        updatedMember.setId(memberId);
        service.saveMember(updatedMember);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
