package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.service.MemberMarkService;
import com.green.service.MemberMarkServiceImpl;
import com.green.service.MemberService;
import com.green.service.MemberServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MarkCommandDispatcher {
    private final Map<String, Command> commands = new HashMap<>();

    public MarkCommandDispatcher() {
        MemberMarkService memberMarkService = new MemberMarkServiceImpl();
        MemberService memberService = new MemberServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        commands.put("POST", new PostMarkCommand(memberMarkService, memberService, objectMapper));
        commands.put("PUT", new PutMarkCommand(memberMarkService, objectMapper));
        commands.put("DELETE", new DeleteMarkCommand(memberMarkService, objectMapper));
        commands.put("GET", new DeleteMarkCommand(memberMarkService, objectMapper));
        commands.put("OPTIONS", new OptionsMarkCommand(memberMarkService, objectMapper));
    }

    public Command getCommand(String name) {
        return commands.getOrDefault(name, (request, response) -> {
            throw new IllegalArgumentException(("Unknown command: " + name));
        });
    }
}
