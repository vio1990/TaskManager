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

/**
 * Implementation class of TaskDao interface with override methods
 * with their realization.
 */
public class TaskDaoImpl implements TaskDao {

    /**
     * Method for addition task to database.
     *
     * @param task Task task for addition.
     * @return boolean true - if addition is success, false - if not.
     */
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

    /**
     * Method for obtaining of all actual tasks (not done).
     *
     * @return List of all tasks.
     */
    @Override
    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks;";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()) {

            while (resultSet.next()) {
                addTasksToList(tasks, resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Method for obtaining of all done tasks from database.
     *
     * @return List of all done tasks.
     */
    @Override
    public List<Task> getAllDoneTasks() {
        List<Task> doneTasks = new ArrayList<>();
        String sql = "SELECT * FROM done_tasks;";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sql);
             ResultSet resultSet = prepStatement.executeQuery()) {

            while (resultSet.next()) {
                addTasksToList(doneTasks, resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doneTasks;
    }

    /**
     * Make task is done by it's id.
     *
     * @param taskId int taskId.
     * @return boolean if query is being success.
     */
    @Override
    public boolean makeTaskDone(int taskId) {
        String sql = "INSERT INTO done_tasks SELECT * FROM tasks WHERE id= ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setInt(1, taskId);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        deleteTaskById(taskId);
        return false;
    }

    /**
     * Method for removal task by it's id.
     *
     * @param taskId int task id for removal.
     * @return boolean if removal from database. was success.
     */
    @Override
    public boolean deleteTaskById(int taskId) {
        String sql = "DELETE FROM tasks WHERE id= ?";

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement prepStatement = connection.prepareStatement(sql)) {
            prepStatement.setInt(1, taskId);
            return prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Private util method for addition task to list with check,
     * if this task is expired (through java.util.Date "before" method).
     *
     * @param tasks List list for tasks addition.
     * @param resultSet ResultSet for obtaining data from database.
     * @throws SQLException possible sql exception.
     */
    private void addTasksToList(List<Task> tasks, ResultSet resultSet) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getInt("id"));
        task.setName(resultSet.getString("task_name"));
        task.setDoneDate(new Date(resultSet.getDate("done_date").getTime()));
        task.setPriority(resultSet.getString("priority"));

        if (task.getDoneDate().before(new Date())) {
            task.setExpired(true);
        }

        tasks.add(task);
    }
}
