package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.controller.activity.DeleteActivityCommand;
import com.green.controller.activity.GetActivityCommand;
import com.green.controller.activity.PostActivityCommand;
import com.green.controller.activity.PutActivityCommand;
import com.green.service.ActivityService;
import com.green.service.ActivityServiceImpl;
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
        ObjectMapper objectMapper = new ObjectMapper();
//        commands.put("GET", new GetMemberMarkCommand(memberMarkService, objectMapper));
//        commands.put("POST", new PostMemberMarkCommand(memberMarkService, objectMapper));
//        commands.put("PUT", new PutMemberMarkCommand(memberMarkService, objectMapper));
//        commands.put("DELETE", new DeleteMemberMarkCommand(memberMarkService, objectMapper));
    }

    public Command getCommand(String name) {
        Command command = commands.get(name);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command: " + name);
        }
        return command;
    }
}
