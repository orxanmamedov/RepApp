package com.green.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.green.util.Scheduler.calculateInitialDelay;

public class TaskToRun {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final JavaMailer javaMailer = new JavaMailer();

    private final Runnable task = () -> {
        try {

            javaMailer.sendMail("Daily Report", "");

        } catch (Exception e) {
            // Handle exceptions gracefully
            System.out.println("SOMETHING WRONGGG");
            e.printStackTrace(); // Log the exception or perform appropriate logging
        }
    };

    public static void scheduleTask(long initialDelay, long period, TimeUnit unit) {
        scheduler.scheduleAtFixedRate(new TaskToRun().task, initialDelay, period, unit);
    }
    public void runTheTaskAtScheduledTime(int hour, int minute, int second){
        scheduleTask(calculateInitialDelay(hour, minute, second), 86400000, TimeUnit.MILLISECONDS);
        System.out.println("SCHEDULED TIME ---------- " + calculateInitialDelay(hour, minute, second)/(60*1000) + " Minutes");
    }

    public static void shutdownScheduler() {
        scheduler.shutdown();
    }
}
