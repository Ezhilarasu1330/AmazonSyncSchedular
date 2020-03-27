package com.example.amazonsync.Service;

import com.example.amazonsync.DBModels.TaskDetails;
import org.quartz.*;
import org.quartz.Job;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    public void startScheduler(final TaskDetails taskDetail) throws SchedulerException, ClassNotFoundException {
        try {
            System.out.println("Start Schedular : "+taskDetail.getTaskName());
            final JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(taskDetail.getTaskClassFile())).build();
            jobDetail.getJobDataMap().put("taskId", taskDetail.getTaskDetailsId());

            // System.out.println("Task ID " + taskDetail.getTaskDetailsId());

            final Trigger trigger = TriggerBuilder.newTrigger().withIdentity("task-" + taskDetail.getTaskDetailsId())
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(taskDetail.getTaskTime()).repeatForever())
                    .build();

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);

            System.out.println("Schedule Job End ");
        } catch (Exception e) {
            System.out.println("Error Occured : ");
            e.printStackTrace();
        }
    }
}
