package com.green.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerUtils {
    private ControllerUtils(){}

    public static void writeResponse(HttpServletResponse response,Object data, ObjectMapper objectMapper, int statusCode)
            throws IOException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(statusCode);
        if (data!=null){
            response.getWriter().write(objectMapper.writeValueAsString(data));
        }
    }
}
