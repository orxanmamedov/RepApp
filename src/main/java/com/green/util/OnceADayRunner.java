package com.green.util;


import org.checkerframework.checker.units.qual.C;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;





public class OnceADayRunner {


    public static void main(String[] args) {
        Calendar currentDate = Calendar.getInstance();
        Date now = currentDate.getTime();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    new GMailer().sendMail("sub", "msg");
                } catch (GeneralSecurityException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }

            }
        };

        Calendar scheduledTime = Calendar.getInstance();
        scheduledTime.set(Calendar.HOUR_OF_DAY, 23);
        scheduledTime.set(Calendar.MINUTE, 59);
        scheduledTime.set(Calendar.SECOND, 0);
        scheduledTime.set(Calendar.MILLISECOND, 0);

        // If the scheduled time has already passed for today, move it to tomorrow
        if (scheduledTime.getTime().before(now)) {
            scheduledTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        // Calculate the delay until the first run
        long initialDelay = scheduledTime.getTimeInMillis() - now.getTime();

        System.out.println("Scheduled time: " + initialDelay);

        timer.schedule(task, initialDelay, 86400000);

    }


}
