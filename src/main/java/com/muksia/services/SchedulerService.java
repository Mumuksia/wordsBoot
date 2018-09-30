package com.muksia.services;

import com.muksia.model.Scheduler;
import com.muksia.repository.SchedulerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    final static Logger LOGGER = LoggerFactory.getLogger(SchedulerService.class);

    @Autowired
    private SchedulerRepository schedulerRepository;

    private String defaultUser = "Yurko";

    public void setScheduler(String title, String time, String date, String dayOfWeek){
        Scheduler.SchedulerBuilder builder = new Scheduler.SchedulerBuilder();

        LOGGER.info("Scheduler saved " +
                schedulerRepository.save(builder.withTitle(title).withDate(date).withTime(time).withDayOfWeek(dayOfWeek).withUser(defaultUser).build()));
    }

    public void deleteAll(){
        schedulerRepository.deleteAll();
    }
}
