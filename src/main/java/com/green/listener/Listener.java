package com.green.listener;


import com.green.util.TaskToRun;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // This method will be called when the servlet context is initialized
        TaskToRun taskToRun = new TaskToRun();
        taskToRun.runTheTaskAtScheduledTime(17, 53, 0);
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // This method will be called when the servlet context is destroyed
        // You can perform cleanup tasks here if needed

    }
}
