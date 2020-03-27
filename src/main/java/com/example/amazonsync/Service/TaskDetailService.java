package com.example.amazonsync.Service;

import com.example.amazonsync.DBModels.TaskDetails;
import com.example.amazonsync.Repository.TaskDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TaskDetailService {

    @Autowired
    private TaskDetailsRepository taskDetailsRepository;

    public Collection<TaskDetails> getTaskDetails() {
        return this.taskDetailsRepository.findAll();
    }
}