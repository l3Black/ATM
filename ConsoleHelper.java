package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        while (true) {
            try {
                String result = bis.readLine();
                if (result.isEmpty()) {
                    writeMessage("You did not enter data. Please, reenter: ");
                    continue;
                }
                if (result.equalsIgnoreCase("exit"))
                    throw new InterruptOperationException();
                return result;
            } catch (IOException e) {
                writeMessage("Oops! Please, reenter: ");
            }
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        writeMessage("Please, enter currency code: ");
        do {
            result = readString();
            if (result.length() != 3)
                writeMessage("Error, the code must consist of 3 symbols reenter, please:");
        } while (result.length() != 3);

        return result.toUpperCase();

    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage("Please, enter " + currencyCode + " face value and number of banknotes:");
        while (true) {
            String[] result = readString().split(" ", 2);
            try {
                int a = Integer.parseInt(result[0]);
                int b = Integer.parseInt(result[1]);
                if (a <= 0 || b <= 0) {
                    writeMessage("The entered data is not correct reenter, please:");
                    continue;
                }
                return result;
            } catch (Exception e) {
                writeMessage("The entered data is not correct reenter, please:");
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Select operation:");
        while (true) {
            String input = readString();
            try {
                int operCode = Integer.parseInt(input);
                return Operation.getAllowableOperationByOrdinal(operCode);
            } catch (Exception e) {
                writeMessage("Error. Reenter, please: ");
            }
        }
    }
}
