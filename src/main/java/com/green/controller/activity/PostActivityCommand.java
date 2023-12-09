package com.green.controller.activity;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.service.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostActivityCommand implements Command {
    private final Service service;
    private final ObjectMapper objectMapper;

    public PostActivityCommand(Service service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());

    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        Activity activity = objectMapper.readValue(request.getReader(), Activity.class);
        Member member = service.getMemberById(activity.getMember().getId());
        activity.setMember(member);
        service.saveActivity(activity);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }
}
