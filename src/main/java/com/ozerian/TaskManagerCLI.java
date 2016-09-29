package com.ozerian;


import com.ozerian.dao.TaskDao;
import com.ozerian.dao.TaskDaoImpl;
import com.ozerian.entity.Task;
import com.ozerian.util.InOutData;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class TaskManagerCLI {
    public static void main(String[] args) {

        try {
            TaskDao taskDao = new TaskDaoImpl();
            boolean isContinue = true;
            System.out.println("Hi! Welcome to th TaskManager program!");
            do {

                System.out.println("If you want to add new task, please, enter \"1\"");
                System.out.println("If you want to see all tasks, please, enter \"2\"");
                System.out.println("If you want to exit, please, enter \"3\"");

                String choice = InOutData.enteredChoice();

                switch (choice) {
                    case "1":
                        Task task = InOutData.createTask();
                        taskDao.addTask(task);
                        break;
                    case "2":
                        List<Task> tasks = taskDao.getAllTasks();

                        if (tasks.isEmpty()) {
                            System.out.println("There are no tasks!");
                            break;
                        }
                        tasks.forEach(System.out::println);
                        System.out.println("If you have done some task enter \"1\". Back to the menu - \"2\". For Exit - \"3\"");
                        String currentChoice = InOutData.enteredChoice();

                        if ("1".equals(currentChoice)) {
                            System.out.println("Please entered it's id");
                            int taskId = InOutData.getDoneTaskId();
                            taskDao.makeTaskDone(taskId);
                        } else if ("3".equals(currentChoice)) {
                            isContinue = false;
                        }
                        break;
                    case "3":
                        isContinue = false;
                        break;
                }
            } while (isContinue == true);

            System.out.println("Thank you! Good bye!");

        } catch (IOException ex) {
            System.out.println("Wrong input data!");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
