package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetActivityCommand implements Command {
    private final ActivityService service;
    private final ObjectMapper objectMapper;


    public GetActivityCommand(ActivityService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        List<ActivityResponseDTO> activities = service.getListOfActivities(request.getParameterMap());
        ControllerUtils.writeResponse(response, activities, objectMapper, HttpServletResponse.SC_OK);
    }
}
