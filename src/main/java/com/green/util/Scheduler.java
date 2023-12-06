package com.green.util;

import java.util.Calendar;
import java.util.Date;


public class Scheduler {
    static long calculateInitialDelay(int hour, int minute, int second) {
        Calendar scheduledTime = Calendar.getInstance();
        scheduledTime.set(Calendar.HOUR_OF_DAY, hour);
        scheduledTime.set(Calendar.MINUTE, minute);
        scheduledTime.set(Calendar.SECOND, second);

        Date now = Calendar.getInstance().getTime();
        // If the scheduled time has already passed for today, move it to tomorrow
        if (scheduledTime.getTime().before(now)) {
            scheduledTime.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Calculate the delay until the first run
        return scheduledTime.getTimeInMillis() - now.getTime();
    }
}
