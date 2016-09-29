package com.ozerian.util;

import com.ozerian.entity.Task;
import com.sun.xml.internal.ws.util.NoCloseInputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class InOutData {

    public static String enteredChoice() throws IOException {

        NoCloseInputStream noCloseStream = new NoCloseInputStream(System.in);
        InputStreamReader inStreamReader = new InputStreamReader(noCloseStream);

        try (BufferedReader reader = new BufferedReader(inStreamReader)) {
            String result = reader.readLine();
            if ("1".equals(result) || "2".equals(result) || "3".equals(result)) {
                return result;
            } else {
                System.out.println("Please, enter \"1\", \"2\" or \"3\"");
                return InOutData.enteredChoice();
            }
        }
    }

    public static Task createTask() throws IOException, ParseException {
        NoCloseInputStream noCloseStream = new NoCloseInputStream(System.in);
        InputStreamReader inStreamReader = new InputStreamReader(noCloseStream);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Task task = new Task();
        try (BufferedReader reader = new BufferedReader(inStreamReader)) {
            System.out.println("Enter task's name: ");
            task.setName(reader.readLine());
            System.out.println("Enter task's date to done in format \"dd/MM/yyyy\" : ");
            task.setDoneDate(formatter.parse(reader.readLine()));
            System.out.println("Enter task's priority: ");
            task.setPriority(reader.readLine());
            return task;
        }
    }
}
