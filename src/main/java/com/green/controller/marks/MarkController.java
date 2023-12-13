package com.green.controller.marks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.controller.Command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/marks")
public class MarkController  extends HttpServlet {
    private final MarkCommandDispatcher commandDispatcher = new MarkCommandDispatcher();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init() throws ServletException {
        super.init();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse
            resp) throws ServletException, IOException {
        String method = req.getMethod();
        Command command = commandDispatcher.getCommand(method);
        if (command != null) {
            command.execute(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }
}

