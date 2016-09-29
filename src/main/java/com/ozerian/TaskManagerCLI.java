package com.ozerian;


import com.ozerian.util.InOutData;

import java.io.IOException;

public class TaskManagerCLI {
    public static void main(String[] args) {

        try {
            boolean isContinue = true;
            System.out.println("Hi! Welcome to th TaskManager program!");
            do {

                int choice = InOutData.enteredChoice();

                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        isContinue = false;
                        break;
                }
            } while (isContinue == true);

            System.out.println("Thank you! Good bye!");

        } catch (IOException ex) {
            System.out.println("Wrong input data!");
        }
    }
}
