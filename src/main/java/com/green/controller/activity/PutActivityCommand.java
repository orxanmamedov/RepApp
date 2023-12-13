package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.activity.ActivityRequestDTO;
import com.green.dto.activity.ActivityResponseDTO;
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

        if (updatedActivityDTO.getId() != 0) {
            int activityId = updatedActivityDTO.getId();
            service.updateActivity(activityId, updatedActivityDTO);
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
//        String pathInfo = request.getPathInfo();
//        int activityId = ControllerUtils.extractIdFromPath(pathInfo);
//        ActivityResponseDTO existingActivity = service.getActivityById(activityId);
//
//        if (existingActivity != null) {
//            handleUpdateActivity(request, response, activityId);
//        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//
//    private void handleUpdateActivity(HttpServletRequest request, HttpServletResponse response, int activityId)
//            throws IOException {
//        ActivityRequestDTO updatedActivityDTO = objectMapper.readValue(request.getReader(), ActivityRequestDTO.class);
//
//        service.updateActivity(activityId, updatedActivityDTO);
//
//        response.setStatus(HttpServletResponse.SC_OK);
//    }


}
