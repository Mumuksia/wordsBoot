package com.muksia.tasks;

import com.muksia.model.Scheduler;
import com.muksia.repository.SchedulerRepository;
import com.muksia.services.EmailService;
import com.muksia.tasks.checker.SchedulerTimeChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Component
public class SchedulerTask {

    final static Logger LOGGER = LoggerFactory.getLogger(SchedulerTask.class);

    @Autowired
    private EmailService emailService;
    @Autowired
    SchedulerRepository schedulerRepository;
    @Autowired
    SchedulerTimeChecker schedulerTimeChecker;

    @Scheduled(fixedRate = 600000)
    public void checkDBForSchedules() throws IOException {

        List<Scheduler> schedulerList = schedulerRepository.findAll();

        LOGGER.info("found " + schedulerList.size() + " events");

        for (Scheduler scheduler : schedulerList){
            if (schedulerTimeChecker.checkScheduler(scheduler)){
                emailService.sendEmail(scheduler.getUser(), scheduler.getTitle());

                if (StringUtils.isEmpty(scheduler.getDayOfWeek())){
                    schedulerRepository.delete(scheduler);
                }
            }
        }

    }

}
