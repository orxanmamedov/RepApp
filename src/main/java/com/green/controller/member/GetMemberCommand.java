package com.green.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;
import com.green.controller.ControllerUtils;
import com.green.dto.member.MemberResponseDTO;
import com.green.entity.Member;
import com.green.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class GetMemberCommand implements Command {
    private final MemberService service;
    private final ObjectMapper objectMapper;


    public GetMemberCommand(MemberService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    /*  /members - returns list of members
        /members?nameGroup=green- returns members from the "green" group.
        /members?id=1 - returns member with id=1.
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        String pathInfo = request.getPathInfo();
        String nameGroupParam = request.getParameter("nameGroup");
        String idParam = request.getParameter("id");

        List<MemberResponseDTO> members =
                (pathInfo == null && nameGroupParam == null && idParam == null) ? service.getListOfMembers() :
                        (pathInfo == null && nameGroupParam != null) ? service.getMembersByGroupName(nameGroupParam) :
                                (pathInfo == null && idParam != null) ? getSingleMemberById(idParam) :
                                        Collections.emptyList();

        if (!members.isEmpty()) {
            ControllerUtils.writeJsonResponse(response, members, objectMapper);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private List<MemberResponseDTO> getSingleMemberById(String idParam) {
        try {
            int memberId = Integer.parseInt(idParam);
            MemberResponseDTO member = service.getMemberById(memberId);
            return (member != null) ? Collections.singletonList(member) : Collections.emptyList();
        } catch (NumberFormatException e) {
            return Collections.emptyList();
        }
    }
}
//    @Override
//    public void execute(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
//        String pathInfo = request.getPathInfo();
//        if (pathInfo == null) {
//            handleListOfMembers(response);
//        } else {
//            handleSingleMember(response, pathInfo);
//        }
//    }
//
//    private void handleListOfMembers(HttpServletResponse response) throws IOException {
//        List<MemberResponseDTO> members = service.getListOfMembers();
//        ControllerUtils.writeJsonResponse(response, members, objectMapper);
//    }
//
//    private void handleSingleMember(HttpServletResponse response, String pathInfo)
//            throws IOException {
//        int memberId = ControllerUtils.extractIdFromPath(pathInfo);
//        MemberResponseDTO member = service.getMemberById(memberId);
//        if (member != null) {
//            ControllerUtils.writeJsonResponse(response, member, objectMapper);
//        } else {
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
//    }
//}
