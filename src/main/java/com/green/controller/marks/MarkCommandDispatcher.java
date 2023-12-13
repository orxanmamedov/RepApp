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
        MemberService memberService = new MemberServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        commands.put("POST", new PostMarkCommand(memberMarkService,memberService,objectMapper));
        commands.put("PUT", new PutMarkCommand(memberMarkService, objectMapper));
        commands.put("DELETE", new DeleteMarkCommand(memberMarkService, objectMapper));
    }
    public Command getCommand(String name) {
        Command command = commands.get(name);
        if (command == null) {
            throw new IllegalArgumentException("Unknown command: " + name);
        }
        return command;
    }
}
