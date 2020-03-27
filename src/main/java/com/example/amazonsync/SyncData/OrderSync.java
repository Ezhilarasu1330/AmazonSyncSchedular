package com.example.amazonsync.SyncData;

import com.example.amazonsync.AmazonSync.ImportAmazonDataService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OrderSync implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            new ImportAmazonDataService().getOrdersFromAmazonStore(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
