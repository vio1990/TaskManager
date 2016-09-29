package com.ozerian.dao;

import com.ozerian.entity.Task;

import java.util.List;

public interface TaskDao {

    boolean addTask(Task task);

    List<Task> getAllTasks();
}
