package com.green.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.controller.Command;
import com.green.service.MemberService;
import com.green.service.MemberServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MemberCommandDispatcher {

    private final Map<String, Command> commands = new HashMap<>();

    public MemberCommandDispatcher() {
        MemberService memberService = new MemberServiceImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        commands.put("GET", new GetMemberCommand(memberService, objectMapper));
        commands.put("POST", new PostMemberCommand(memberService, objectMapper));
        commands.put("PUT", new PutMemberCommand(memberService, objectMapper));
        commands.put("DELETE", new DeleteMemberCommand(memberService, objectMapper));
        commands.put("OPTIONS", new OptionsMemberCommand(memberService, objectMapper));
    }

    public Command getCommand(String name) {
        return commands.getOrDefault(name, (request, response) -> {
            throw new IllegalArgumentException(("Unknown command: " + name));
        });
    }
}
