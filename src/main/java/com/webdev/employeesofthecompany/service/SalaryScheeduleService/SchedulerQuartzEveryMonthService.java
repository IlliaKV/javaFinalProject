package com.webdev.employeesofthecompany.service.SalaryScheeduleService;

import static org.quartz.CronScheduleBuilder.monthlyOnDayAndHourAndMinute;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import com.webdev.employeesofthecompany.domain.Employee;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchedulerQuartzEveryMonthService {

    public void run() throws Exception {
        Logger log = LoggerFactory.getLogger(SchedulerQuartzEveryMonthService.class);

        log.info("------- Initializing ----------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        // computer a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());

        log.info("------- Scheduling Job  -------------------");

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(EveryMonthJobService.class)
                .withIdentity("зарплата1", "Расчетный лист1")
                .build();
        // Trigger the job to run on the next round minute
       // Trigger trigger = newTrigger().withIdentity("зарплата1", "Расчетный лист1").startAt(runTime).build();

        Trigger trigger = newTrigger()
                .withIdentity("зарплата1", "Расчетный лист1")
                .startNow()
                .withSchedule(monthlyOnDayAndHourAndMinute(5, 15, 0)) // fire on the 5th day of every month at 15:00
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + runTime);

        // Start up the scheduler (nothing can actually run until the
        // scheduler has been started)
        sched.start();

        log.info("------- Started Scheduler -----------------");

//        // wait long enough so that the scheduler as an opportunity to
//        // run the job!
//        log.info("------- Waiting 65 seconds... -------------");
//        try {
//            // wait 65 seconds to show job
//            Thread.sleep(65L * 1000L);
//            // executing...
//        } catch (Exception e) {
//            //
//        }
//
//        // shut down the scheduler
//        log.info("------- Shutting Down ---------------------");
//        sched.shutdown(true);
//        log.info("------- Shutdown Complete -----------------");
    }
}
