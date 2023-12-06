package com.green.rest;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.service.Service;
import com.green.service.ServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/")
public class MyServlet extends HttpServlet {
    private static final String PATH_TO_MEMBER ="/members";
    private static final String PATH_TO_ACTIVITIES="/activities";
    private final Service service = new ServiceImpl();
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void init() throws ServletException {
        super.init();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }


    //Show data
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getServletPath();
        if (pathInfo.startsWith(PATH_TO_MEMBER)) {
            if (pathInfo.equals(PATH_TO_MEMBER)) {
                List<Member> members = service.getListOfMembers();
                writeJsonResponse(resp, members);
            } else {
                int memberId = extractIdFromPath(pathInfo);
                Member member = service.getMemberById(memberId);
                if (member != null) {
                    writeJsonResponse(resp, member);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        } else if (pathInfo.startsWith(PATH_TO_ACTIVITIES)) {
            if (pathInfo.equals(PATH_TO_ACTIVITIES)) {
                List<Activity> activities = service.getListOfActivities();
                writeJsonResponse(resp, activities);
            } else {
                int activityId = extractIdFromPath(pathInfo);
                Activity activity = service.getActivityById(activityId);
                if (activity != null) {
                    writeJsonResponse(resp, activity);
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }
    }
    //Insert data
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getServletPath();
        if (pathInfo.equals(PATH_TO_MEMBER)){
            Member member = objectMapper.readValue(req.getReader(), Member.class);
            service.saveMember(member);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } else if (pathInfo.equals(PATH_TO_ACTIVITIES)) {
            Activity activity = objectMapper.readValue(req.getReader(), Activity.class);
            Member member = service.getMemberById(activity.getMember().getId());
            activity.setMember(member);
            service.saveActivity(activity);
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
    }
//    Update data
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getServletPath();

        if (pathInfo.startsWith(PATH_TO_MEMBER)) {
            int memberId = extractIdFromPath(pathInfo);
            Member existingMember = service.getMemberById(memberId);

            if (existingMember != null) {
                Member updatedMember = objectMapper.readValue(req.getReader(), Member.class);
                updatedMember.setId(memberId);
                service.saveMember(updatedMember);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        } else if (pathInfo.startsWith(PATH_TO_ACTIVITIES)) {
            int activityId = extractIdFromPath(pathInfo);
            Activity existingActivity = service.getActivityById(activityId);

            if (existingActivity != null) {
                Activity updatedActivity = objectMapper.readValue(req.getReader(), Activity.class);
                updatedActivity.setId(activityId);
                service.saveActivity(updatedActivity);
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getServletPath();

        if (pathInfo.startsWith(PATH_TO_MEMBER)) {
            int memberId = extractIdFromPath(pathInfo);
            service.deleteMember(memberId);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else if (pathInfo.startsWith(PATH_TO_ACTIVITIES)) {
            int activityId = extractIdFromPath(pathInfo);
            service.deleteActivity(activityId);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
    }
    private int extractIdFromPath(String pathInfo) {
        String[] pathSegments = pathInfo.split("/");
        return Integer.parseInt(pathSegments[pathSegments.length - 1]);
    }

    private void writeJsonResponse(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(objectMapper.writeValueAsString(data));
    }
}