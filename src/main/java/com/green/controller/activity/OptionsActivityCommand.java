package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OptionsActivityCommand implements Command {
    private final ObjectMapper objectMapper;

    public OptionsActivityCommand(ActivityService service, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        ControllerUtils.writeResponse(response, null, objectMapper, HttpServletResponse.SC_OK);
    }
}
