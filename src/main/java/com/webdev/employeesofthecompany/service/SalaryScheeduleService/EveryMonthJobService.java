package com.webdev.employeesofthecompany.service.SalaryScheeduleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EveryMonthJobService implements Job {

    @Autowired
    private AutomaticallyCalculateSalaryService a;

    private static Logger _log = LoggerFactory.getLogger(EveryMonthJobService.class);

    public EveryMonthJobService() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        _log.info("Salary Day! :) - " + new Date());
        a.run();
    }
}
