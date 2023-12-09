package com.green.util;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.green.util.Scheduler.calculateInitialDelay;

public class TaskToRun {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private ReportBuilder reportBuilder = new ReportBuilder();
    private JavaMailer javaMailer = new JavaMailer();

    private Runnable task = () -> {
        try {
            javaMailer.sendMail("Daily Report", reportBuilder.report());
        } catch (Exception e) {
            // Handle exceptions gracefully
            e.printStackTrace(); // Log the exception or perform appropriate logging
        }
    };

    public static void scheduleTask(long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(new TaskToRun().task, initialDelay, period, unit);
    }
    public void runTheTaskAtScheduledTime(int hour, int minute, int second){
        scheduleTask(calculateInitialDelay(hour, minute, second), 86400000, TimeUnit.MILLISECONDS);
        System.out.println("SCHEDULED TIME ---------- " + calculateInitialDelay(hour, minute, second)/(60*60*1000) + " Hours");
    }

    public static void shutdownScheduler() {
        scheduler.shutdown();
    }
}
