package com.ozerian.dao;

import com.ozerian.connection.ConnectionManager;
import com.ozerian.entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    @Override
    public boolean addTask(Task task) {

        String sqlQuery = "INSERT INTO tasks VALUES ( DEFAULT , ?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sqlQuery)) {
            prepStatement.setString(1, task.getName());
            prepStatement.setDate(2, new java.sql.Date(task.getDoneDate().getTime()));
            prepStatement.setString(3, task.getPriority());
            return prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sqlQuery = "SELECT * FROM tasks;";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = prepStatement.executeQuery()) {

            while (resultSet.next()) {
                addTasksToList(tasks, resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private void addTasksToList(List<Task> tasks, ResultSet resultSet) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setName(resultSet.getString("task_name"));
        task.setDoneDate(new Date(resultSet.getDate("done_date").getTime()));
        task.setPriority(resultSet.getString("priority"));
        tasks.add(task);
    }

}
