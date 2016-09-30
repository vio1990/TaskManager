package com.ozerian;

import com.ozerian.dao.TaskDao;
import com.ozerian.dao.TaskDaoImpl;
import com.ozerian.entity.Task;
import com.ozerian.util.InOutData;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Command line interface class for program running.
 */
public class TaskManagerCLI {
    public static void main(String[] args) {

        try {
            TaskDao taskDao = new TaskDaoImpl();

            // flag if user want to close program.
            boolean isContinue = true;
            System.out.println("Hi! Welcome to th TaskManager program!");

            do {

                System.out.println("If you want to add new task, please, enter \"1\"");
                System.out.println("If you want to see all tasks, please, enter \"2\"");
                System.out.println("If you want to exit, please, enter \"3\"");

                // define user's choice.
                String choice = InOutData.enteredChoice();

                switch (choice) {
                    // task addition.
                    case "1":
                        Task task = InOutData.createTask();
                        taskDao.addTask(task);
                        break;
                    // show all tasks.
                    case "2":
                        List<Task> tasks = taskDao.getAllTasks();

                        // check if list is empty.
                        if (tasks.isEmpty()) {
                            System.out.println("There are no tasks!");
                            break;
                        }
                        System.out.println("The list of all actual tasks!");

                        // check if task is expired and add inform message if so.
                        for (Task actualTask : tasks) {
                            System.out.print(actualTask);

                            if (actualTask.isExpired() == true) {
                                System.out.print("   EXPIRED!!!");
                            }

                            System.out.println();
                        }

                        System.out.println("If you have done some task enter \"1\". Back to the menu - \"2\". See list of done tasks - \"3\"");
                        String currentChoice = InOutData.enteredChoice();

                        // Sections with "done task" making and "back to the menu".
                        if ("1".equals(currentChoice)) {
                            System.out.println("Please entered it's id");
                            int taskId = InOutData.getDoneTaskId();
                            taskDao.makeTaskDone(taskId);
                        } else if ("3".equals(currentChoice)) {
                            List<Task> doneTasks = taskDao.getAllDoneTasks();

                            // check if list is empty.
                            if (doneTasks.isEmpty()) {
                                System.out.println("There are no done tasks!");
                                break;
                            }

                            System.out.println("The list of all done tasks!");
                            doneTasks.forEach(System.out::println);
                        }
                        break;
                    // exit from program.
                    case "3":
                        isContinue = false;
                        break;
                }
            } while (isContinue == true);

            System.out.println("Thank you! Good bye!");

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Wrong input data!");
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error during date parse exception!");
        }
    }
}
