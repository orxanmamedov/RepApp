package com.green.controller.activity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.service.Service;
import com.green.service.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class ActivityCommandDispatcher {
    private final Map<String, Command> commands = new HashMap<>();

    public ActivityCommandDispatcher(){
        Service service = new ServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        commands.put("GET", new GetActivityCommand(service, objectMapper));
        commands.put("POST", new PostActivityCommand(service, objectMapper));
        commands.put("PUT", new PutActivityCommand(service, objectMapper));
        commands.put("DELETE", new DeleteActivityCommand(service));
    }
    public Command getCommand(String name) {
        Command command = commands.get(name);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command: " + name);
        }
        return command;
    }
}


