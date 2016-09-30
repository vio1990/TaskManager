package com.ozerian.dao;

import com.ozerian.entity.Task;

import java.util.List;

/**
 * TaskDao interface with the main necessary methods for program
 * functioning.
 */
public interface TaskDao {

    boolean addTask(Task task);

    List<Task> getAllTasks();

    List<Task> getAllDoneTasks();

    boolean makeTaskDone(int taskId);

    boolean deleteTaskById(int taskId);
}
