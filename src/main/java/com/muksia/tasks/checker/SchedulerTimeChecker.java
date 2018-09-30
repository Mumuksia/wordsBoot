package com.muksia.tasks.checker;

import com.muksia.model.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SchedulerTimeChecker {
    final static Logger LOGGER = LoggerFactory.getLogger(SchedulerTimeChecker.class);

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyyy hhmm");

    public boolean checkScheduler(Scheduler scheduler){

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Europe/Paris"));

        if (StringUtils.isEmpty(scheduler.getDayOfWeek())){

            Date scheduledTime = null;
            try {
                scheduledTime = simpleDateFormat.parse(scheduler.getDate() + " " + scheduler.getTime());
                return scheduledTime.before(new Date());
            } catch (ParseException e) {
                LOGGER.error("can not parse date", e);
            }
        } else {
            int hours = Integer.valueOf(scheduler.getTime().substring(0,2));
            int minutes = Integer.valueOf(scheduler.getTime().substring(2,4));
            return now.getDayOfWeek().toString().equals(scheduler.getDayOfWeek()) && now.getHour() > hours && now.getMinute() > minutes;
        }
        return false;
    }
}
