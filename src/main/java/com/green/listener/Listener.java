package com.green.listener;


import com.green.util.TaskToRun;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    TaskToRun taskToRun = new TaskToRun();
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        // This method will be called when the servlet context is initialized

        taskToRun.runTheTaskAtScheduledTime(18, 00, 0);
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

        taskToRun.shutdownScheduler();
    }
}
