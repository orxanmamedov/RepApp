package com.green.util;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Timer;
import java.util.TimerTask;

public class TaskToRun {
    private Timer timer = new Timer();
    private ReportBuilder reportBuilder = new ReportBuilder();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                new GMailer().sendMail("sub", reportBuilder.report());
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }

        }
    };

    public TimerTask getTask(){
        return task;
    }
}
