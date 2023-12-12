package com.green.controller.member;


import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMemberCommand implements Command {
    private final MemberService service;

    public DeleteMemberCommand(MemberService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException {
        int memberId = ControllerUtils.extractIdFromPath(request.getPathInfo());
        service.deleteMember(memberId);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
