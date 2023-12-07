package com.green.util;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Timer;
import java.util.TimerTask;

import static com.green.util.Scheduler.calculateInitialDelay;

public class TaskRunner{
    private TaskToRun taskToRun = new TaskToRun();
    private Timer timer = new Timer();

    public void runTheTaskAtScheduledTime(int hour, int minute, int second){
        timer.schedule(taskToRun.getTask(), calculateInitialDelay(hour, minute, second), 86400000);
        System.out.println("SCHEDULED TIME ----------" + calculateInitialDelay(hour, minute, second));
    }
}
