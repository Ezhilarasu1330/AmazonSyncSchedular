package com.example.amazonsync;

import com.example.amazonsync.DBModels.TaskDetails;
import com.example.amazonsync.Service.ScheduleService;
import com.example.amazonsync.Service.TaskDetailService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

@Component
public class ScheduleStarter {

    @Autowired
    private TaskDetailService taskDetailService;

    @Autowired
    private ScheduleService scheduleService;

    @PostConstruct
    public void init(){
        this.runSchedule();
    }

    public void runSchedule() {

        final Collection<TaskDetails> taskDetailList = this.taskDetailService.getTaskDetails();
        taskDetailList.forEach(taskDetail -> {
            System.out.println("Id  = " + taskDetail.getTaskDetailsId() + "; Task Name = " + taskDetail.getTaskName() + " Polling Time : " + taskDetail.getTaskTime());
            try {
                scheduleService.startScheduler(taskDetail);
            } catch ( SchedulerException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

}
