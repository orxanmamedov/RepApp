package com.green.controller.marks;

import com.green.controller.Command;

import com.green.controller.ControllerUtils;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.service.ActivityService;
import com.green.service.MemberService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetMarkCommand implements Command {

    private final ActivityService service;
    private final ObjectMapper objectMapper;

    public GetMarkCommand(ActivityService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {

        ControllerUtils.writeResponse(response, null, objectMapper, HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
