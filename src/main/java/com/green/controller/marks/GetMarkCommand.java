package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.activity.ActivityResponseDTO;
import com.green.service.ActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetMarkCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
             response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
