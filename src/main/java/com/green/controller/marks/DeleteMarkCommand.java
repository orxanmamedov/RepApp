package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.marks.MemberMarkRequestDTO;
import com.green.service.MemberMarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteMarkCommand implements Command {
    private final MemberMarkService service;
    private final ObjectMapper objectMapper;

    public DeleteMarkCommand(MemberMarkService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        MemberMarkRequestDTO memberMarkRequestDTO = objectMapper.readValue(request.getReader(), MemberMarkRequestDTO.class);
        int code = HttpServletResponse.SC_OK;
        if (memberMarkRequestDTO.getId() != 0) {
            int memberMarkId = memberMarkRequestDTO.getId();
            service.deleteMemberMark(memberMarkId);
        } else {
            code = HttpServletResponse.SC_BAD_REQUEST;
        }
        ControllerUtils.writeResponse(response, null, objectMapper, code);

    }
}
