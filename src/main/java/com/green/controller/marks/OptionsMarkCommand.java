package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.service.ActivityService;
import com.green.service.MemberMarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OptionsMarkCommand implements Command {
    private final ObjectMapper objectMapper;

    public OptionsMarkCommand(MemberMarkService service, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        ControllerUtils.writeResponse(response, null, objectMapper, HttpServletResponse.SC_OK);

    }
}
