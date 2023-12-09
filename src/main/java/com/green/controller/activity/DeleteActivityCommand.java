package com.green.controller.activity;

import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteActivityCommand implements Command {
    private final Service service;

    public DeleteActivityCommand(Service service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException {
        int activityId = ControllerUtils.extractIdFromPath(request.getPathInfo());
        service.deleteActivity(activityId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
