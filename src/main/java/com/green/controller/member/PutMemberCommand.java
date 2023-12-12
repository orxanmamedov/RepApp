package com.green.controller.member;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.member.MemberRequestDTO;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
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
        String pathInfo = request.getPathInfo();
        int memberId = ControllerUtils.extractIdFromPath(pathInfo);
        MemberResponseDTO existingMember = service.getMemberById(memberId);

        if (existingMember != null) {
           handleUpdateMember(request, response, memberId);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    private void handleUpdateMember(HttpServletRequest request, HttpServletResponse response, int memberId)
            throws IOException {
        MemberRequestDTO updatedMemberDTO = objectMapper.readValue(request.getReader(), MemberRequestDTO.class);

        service.updateMember(memberId, updatedMemberDTO);

        response.setStatus(HttpServletResponse.SC_OK);
    }



//    private void handleUpdateMember(HttpServletRequest request, HttpServletResponse response, int memberId)
//            throws IOException {
//        MemberRequestDTO updatedMember = objectMapper.readValue(request.getReader(), MemberRequestDTO.class);
//
//         updatedMember.setId(memberId);
//        service.saveMember(updatedMember);
//        response.setStatus(HttpServletResponse.SC_OK);
//    }
}
