package com.ozerian.entity;

import java.util.Date;

public class Task {

    private long id;
    private String name;
    private Date doneDate;
    private TaskPriority priority;

    public Task() {
    }

    public Task(long id, String name, Date doneDate, TaskPriority priority) {
        this.id = id;
        this.name = name;
        this.doneDate = doneDate;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }
}
