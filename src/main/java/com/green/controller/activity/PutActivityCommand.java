package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.entity.Activity;
import com.green.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PutActivityCommand implements Command {
    private final Service service;
    private final ObjectMapper objectMapper;

    public PutActivityCommand(Service service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        String pathInfo = request.getPathInfo();
        int activityId = ControllerUtils.extractIdFromPath(pathInfo);
        Activity existingActivity = service.getActivityById(activityId);

        if (existingActivity != null) {
            handleUpdateActivity(request, response, activityId);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void handleUpdateActivity(HttpServletRequest request, HttpServletResponse response, int activityId)
            throws IOException {
        Activity updatedActivity = objectMapper.readValue(request.getReader(), Activity.class);
        updatedActivity.setId(activityId);
        service.saveActivity(updatedActivity);
        response.setStatus(HttpServletResponse.SC_OK);
    }


}
