package com.ozerian.util;

import com.ozerian.entity.Task;
import com.sun.xml.internal.ws.util.NoCloseInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class InOutData {

    /**
     * Method for definition of user choice.
     *
     * @return String user's choice after input.
     * @throws IOException possible i/o exception.
     */
    public static String enteredChoice() throws IOException {
        try (BufferedReader reader = createReader()) {
            String result = reader.readLine();

            if ("1".equals(result) || "2".equals(result) || "3".equals(result)) {
                return result;
            } else {
                System.out.println("Please, enter \"1\", \"2\" or \"3\"");
                return InOutData.enteredChoice();
            }
        }
    }

    /**
     * Method for task creation woth input of task name, date and priority.
     *
     * @return Task for addition it to database.
     * @throws IOException possible IO exception.
     * @throws ParseException possible exception during date parsing.
     */
    public static Task createTask() throws IOException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Task task = new Task();
        try (BufferedReader reader = createReader()) {
            System.out.println("Enter task's name: ");
            task.setName(reader.readLine());
            System.out.println("Enter task's date to done in format \"dd/MM/yyyy\" : ");
            task.setDoneDate(formatter.parse(reader.readLine()));
            System.out.println("Enter task's priority: ");
            task.setPriority(reader.readLine());
            return task;
        }
    }

    /**
     * Little method for obtaining of task id for making it done.
     *
     * @return int task id, input by user from keyboard.
     * @throws IOException possible IO Exception.
     */
    public static int getDoneTaskId() throws IOException {
        try (BufferedReader reader = createReader()) {
            return Integer.valueOf(reader.readLine());
        }
    }

    /**
     * Method for creation of stream to handle input data from keyboard.
     * NoCloseInputStream method is using for support of main streams in working state,
     * after closing in loops.
     *
     * @return BufferedReader ready for using.
     */
    private static BufferedReader createReader() {
        NoCloseInputStream noCloseStream = new NoCloseInputStream(System.in);
        InputStreamReader inStreamReader = new InputStreamReader(noCloseStream);
        return new BufferedReader(inStreamReader);
    }
}