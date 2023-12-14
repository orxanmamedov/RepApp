package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.activity.ActivityRequestDTO;
import com.green.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutActivityCommand implements Command {
    private final ActivityService service;
    private final ObjectMapper objectMapper;

    public PutActivityCommand(ActivityService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        ActivityRequestDTO updatedActivityDTO = objectMapper.readValue(request.getReader(), ActivityRequestDTO.class);
        int code = HttpServletResponse.SC_OK;
        if (updatedActivityDTO.getId() != 0) {
            service.updateActivity(updatedActivityDTO.getId(), updatedActivityDTO);
        } else {
            code = HttpServletResponse.SC_BAD_REQUEST;
        }
        ControllerUtils.writeResponse(response, null, objectMapper, code);
    }
}
