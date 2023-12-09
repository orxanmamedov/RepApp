package com.green.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.green.controller.Command;
import com.green.service.Service;
import com.green.service.ServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MemberCommandDispatcher {

    private final Map<String, Command> commands = new HashMap<>();

    public MemberCommandDispatcher(){
        Service service = new ServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        commands.put("GET", new GetMemberCommand(service, objectMapper));
        commands.put("POST", new PostMemberCommand(service, objectMapper));
        commands.put("PUT", new PutMemberCommand(service, objectMapper));
        commands.put("DELETE", new DeleteMemberCommand(service));
    }
    public Command getCommand(String name) {
        Command command = commands.get(name);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command: " + name);
        }
        return command;
    }
}
