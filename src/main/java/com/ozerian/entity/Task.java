package com.ozerian.entity;

import java.util.Date;

/**
 * Class for representation of Task entity.
 */
public class Task {

    private long id;
    private String name;
    private Date doneDate;
    private String priority;
    private boolean isExpired;

    /**
     * Empty constructor.
     */
    public Task() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id long entity id.
     * @param name String task name.
     * @param doneDate Date date for task execution.
     * @param priority String task priority.
     */
    public Task(long id, String name, Date doneDate, String priority) {
        this.id = id;
        this.name = name;
        this.doneDate = doneDate;
        this.priority = priority;
        this.isExpired = false;
    }

    /**
     * Get task id.
     *
     * @return long task id.
     */
    public long getId() {
        return id;
    }

    /**
     * Set task id.
     *
     * @param id long task id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get task name
     *
     * @return String task name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set task name.
     *
     * @param name String task name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get task date to execute.
     *
     * @return java.util.Date date task to done.
     */
    public Date getDoneDate() {
        return doneDate;
    }

    /**
     * Set task date for execution.
     *
     * @param doneDate java.util.Date date task for execution.
     */
    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    /**
     * Get task priority.
     *
     * @return String task priority.
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Set task priority.
     *
     * @param priority String task priority.
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * Get status if task is expired.
     *
     * @return boolean is task expired.
     */
    public boolean isExpired() {
        return isExpired;
    }

    /**
     * Set task status.
     *
     * @param expired boolean if task is expired.
     */
    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", doneDate=" + doneDate +
                ", priority='" + priority + '\'' +
                '}';
    }
}
