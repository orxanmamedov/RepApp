package com.green.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerUtils {
    private ControllerUtils(){}
    public static int extractIdFromPath(String pathInfo){
        String [] pathSegments=pathInfo.split("/");
        return Integer.parseInt(pathSegments[pathSegments.length-1]);
    }
    public static void writeJsonResponse(HttpServletResponse response,Object data, ObjectMapper objectMapper)
            throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(data));
    }
}
