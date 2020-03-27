package com.example.amazonsync.AmazonSync;

import com.example.amazonsync.Service.IAmazonUtilService;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.ArrayList;

public class ImportAmazonDataService {

    @Autowired
    private IAmazonUtilService IAmazonUtilService;

    public ArrayList<String> getChannelLoc() {
        ArrayList<String> channelLoc = new ArrayList<String>();
        channelLoc.add("US");
        channelLoc.add("CA");
        channelLoc.add("MX");
        return channelLoc;
    }

    public void getProductsFromAmazonStore(JobExecutionContext context) throws SQLException, ClassNotFoundException {
        try {
            final Long taskID = (Long) context.getJobDetail().getJobDataMap().get("taskId");
            IAmazonUtilService.getChannelConfig("US");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getOrdersFromAmazonStore(JobExecutionContext context) throws SQLException, ClassNotFoundException {

        final long taskID = (long) context.getJobDetail().getJobDataMap().get("taskId");
        IAmazonUtilService.getChannelConfig("CA");
    }
}
