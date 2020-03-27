package com.example.amazonsync.DBModels;

import javax.persistence.*;

@Entity
@Table(name = "TASK_DETAILS")
public class TaskDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_DETAILS_ID")
    private Long taskDetailsId;

    @Column(name = "TASK_NAME")
    private String TaskName;

    @Column(name = "TASK_POLLING_TIME")
    private Integer TaskTime;

    @Column(name = "TASK_FILE")
    private String TaskClassFile;

    @Column(name = "TASK_STATUS")
    private String TaskStatus;

    public long getTaskDetailsId() {
        return taskDetailsId;
    }

    public void setTaskDetailsId(long taskDetailsId) {
        this.taskDetailsId = taskDetailsId;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public Integer getTaskTime() {
        return TaskTime;
    }

    public void setTaskTime(Integer taskTime) {
        TaskTime = taskTime;
    }

    public String getTaskClassFile() {
        return TaskClassFile;
    }

    public void setTaskClassFile(String taskClassFile) {
        TaskClassFile = taskClassFile;
    }

    public String getTaskStatus() {
        return TaskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        TaskStatus = taskStatus;
    }

}
