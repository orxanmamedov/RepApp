package com.green.rest;

import com.green.dao.ActivityDAO;
import com.green.dao.ActivityDAOImpl;
import com.green.dao.MemberDAO;
import com.green.dao.MemberDAOImpl;
import com.green.entity.Activity;
import com.green.entity.Member;
import com.green.service.Service;
import com.green.service.ServiceImpl;
import io.javalin.Javalin;

import io.javalin.http.Context;
import java.util.List;

public class RestApi {
     Service service = new ServiceImpl();
    public static void main(String[] args) {


        Javalin app = Javalin.create();

        app.get("/members", RestApi::getMembers);
        app.post("/members", RestApi::saveMember);
        app.get("/members/{id}", RestApi::getMemberById);
        app.delete("/members/{}", RestApi::deleteMember);

        app.get("/activities", RestApi::getActivities);
        app.post("/activities", RestApi::saveActivity);
        app.get("/activities/{id}", RestApi::getActivityById);
        app.delete("/activities/{id}", RestApi::deleteActivity);

        app.start(8080);
    }

    private static void getMembers(Context ctx) {
        MemberDAO memberDAO = new MemberDAOImpl();
        List<Member> members = memberDAO.getListOfMembers();
        ctx.json(members);
    }

    private static void saveMember(Context ctx) {
        MemberDAO memberDAO = new MemberDAOImpl();
        Member member = ctx.bodyAsClass(Member.class);
        memberDAO.saveMember(member);
        ctx.status(201);
    }
    private static void getMemberById(Context ctx) {
        MemberDAO memberDAO = new MemberDAOImpl();
        String memberIdParam = ctx.pathParam("id");

        try {
            int memberId = Integer.parseInt(memberIdParam);
            Member member = memberDAO.getMemberById(memberId);
            if (member != null) {
                ctx.json(member);
            } else {
                ctx.status(404).result("Member not found");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid member ID");
        }
    }

    private static void deleteMember(Context ctx) {
        MemberDAO memberDAO = new MemberDAOImpl();
        String memberIdParam = ctx.pathParam("id");

        try {
            int memberId = Integer.parseInt(memberIdParam);
            memberDAO.deleteMember(memberId);
            ctx.status(204);
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid member ID");
        }
    }

    private static void getActivities(Context ctx) {
        ActivityDAO activityDAO = new ActivityDAOImpl();
        List<Activity> activities = activityDAO.getListOfActivities();
        ctx.json(activities);
    }

    private static void saveActivity(Context ctx) {
        ActivityDAO activityDAO = new ActivityDAOImpl();
        Activity activity = ctx.bodyAsClass(Activity.class);
        activityDAO.saveActivity(activity);
        ctx.status(201);
    }

    private static void getActivityById(Context ctx) {
        ActivityDAO activityDAO = new ActivityDAOImpl();
        String activityIdParam = ctx.pathParam("id");

        try {
            int activityId = Integer.parseInt(activityIdParam);
            Activity activity = activityDAO.getActivityById(activityId);
            if (activity != null) {
                ctx.json(activity);
            } else {
                ctx.status(404).result("Activity not found");
            }
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid activity ID");
        }
    }

    private static void deleteActivity(Context ctx) {
        ActivityDAO activityDAO = new ActivityDAOImpl();
        String activityIdParam = ctx.pathParam("id");

        try {
            int activityId = Integer.parseInt(activityIdParam);
            activityDAO.deleteActivity(activityId);
            ctx.status(204);
        } catch (NumberFormatException e) {
            ctx.status(400).result("Invalid activity ID");
        }
    }

}