package com.example.amazonsync.SyncData;

import com.example.amazonsync.AmazonSync.ImportAmazonDataService;
import com.example.amazonsync.DBModels.TaskDetails;
import com.example.amazonsync.Repository.TaskDetailsRepository;
import com.example.amazonsync.utils.PropertiesUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ProductSync implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            // new ImportAmazonDataService().getProductsFromAmazonStore(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
