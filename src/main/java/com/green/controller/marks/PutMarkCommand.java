package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.service.MemberMarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutMarkCommand implements Command {
    private final MemberMarkService service;
    private final ObjectMapper objectMapper;

    public PutMarkCommand(MemberMarkService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        MemberMarkRequestDTO updatedMemberMarkDTO = objectMapper.readValue(request.getReader(), MemberMarkRequestDTO.class);
        if (updatedMemberMarkDTO.getId() != 0) {
            service.updateMemberMark(updatedMemberMarkDTO.getId(), updatedMemberMarkDTO);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
