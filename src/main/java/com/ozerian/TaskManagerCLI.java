package com.ozerian;


import com.ozerian.dao.TaskDao;
import com.ozerian.dao.TaskDaoImpl;
import com.ozerian.entity.Task;
import com.ozerian.util.InOutData;

import java.io.IOException;
import java.text.ParseException;

public class TaskManagerCLI {
    public static void main(String[] args) {

        try {
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
                        TaskDao taskDao = new TaskDaoImpl();
                        taskDao.addTask(task);
                        break;
                    case "2":
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
