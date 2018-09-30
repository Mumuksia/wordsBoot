package com.muksia.controllers;

import com.muksia.services.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    SchedulerService schedulerService;

    @RequestMapping("/set")
    @ResponseBody
    public void sendEmail(@RequestParam(value = "title", defaultValue = "Test") String title,
                          @RequestParam(value = "time") String time,
                          @RequestParam(value = "date") String date,
                          @RequestParam(value = "dayOfWeek") String dayOfWeek){
        schedulerService.setScheduler(title, time, date, dayOfWeek);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void sendEmail(){
        schedulerService.deleteAll();
    }
}
