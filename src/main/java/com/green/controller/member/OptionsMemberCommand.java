package com.green.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.service.ActivityService;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OptionsMemberCommand implements Command {
    private final ObjectMapper objectMapper;

    public OptionsMemberCommand(MemberService service, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        ControllerUtils.writeResponse(response, null, objectMapper, HttpServletResponse.SC_OK);

    }
}
