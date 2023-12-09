package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.entity.Activity;
import com.green.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetActivityCommand implements Command {
    private final Service service;
    private final ObjectMapper objectMapper;


    public GetActivityCommand(Service service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo==null) {
            handleListOfActivities(response);
        } else  {
            handleSingleActivity(response, pathInfo);
        }
    }

    private void handleListOfActivities(HttpServletResponse response) throws IOException {
        List<Activity> activities = service.getListOfActivities();
        ControllerUtils.writeJsonResponse(response, activities, objectMapper);
    }

    private void handleSingleActivity(HttpServletResponse response, String pathInfo)
            throws IOException {
        int activityId = ControllerUtils.extractIdFromPath(pathInfo);
        Activity activity = service.getActivityById(activityId);
        if (activity != null) {
            ControllerUtils.writeJsonResponse(response, activity, objectMapper);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
