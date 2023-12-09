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
import java.util.List;

public class GetMemberCommand implements Command {
    private final Service service;
    private final ObjectMapper objectMapper;


    public GetMemberCommand(Service service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo==null) {
            handleListOfMembers(response);
        } else  {
            handleSingleMember(response, pathInfo);
        }
    }

    private void handleListOfMembers(HttpServletResponse response) throws IOException {
        List<Member> members = service.getListOfMembers();
        ControllerUtils.writeJsonResponse(response, members, objectMapper);
    }

    private void handleSingleMember(HttpServletResponse response, String pathInfo)
            throws IOException {
        int memberId = ControllerUtils.extractIdFromPath(pathInfo);
        Member member = service.getMemberById(memberId);
        if (member != null) {
            ControllerUtils.writeJsonResponse(response, member, objectMapper);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
