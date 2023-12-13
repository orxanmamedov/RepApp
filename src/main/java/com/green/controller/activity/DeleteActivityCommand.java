package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.dto.activity.ActivityRequestDTO;
import com.green.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteActivityCommand implements Command {
    private final ActivityService service;
    private final ObjectMapper objectMapper;

    public DeleteActivityCommand(ActivityService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        ActivityRequestDTO activityRequestDTO = objectMapper.readValue(request.getReader(), ActivityRequestDTO.class);
        if (activityRequestDTO.getId() != 0) {
            int activityId = activityRequestDTO.getId();
            service.deleteActivity(activityId);

            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException {
//        int activityId = ControllerUtils.extractIdFromPath(request.getPathInfo());
//        service.deleteActivity(activityId);
//        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//    }
}
