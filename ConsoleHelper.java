package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));
    private static ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "common_en");

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        while (true) {
            try {
                String result = bis.readLine();
                if (result.isEmpty()) {
                    writeMessage(res.getString("invalid.data"));
                    continue;
                }
                if (result.equalsIgnoreCase("exit"))
                    throw new InterruptOperationException();
                return result;
            } catch (IOException e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static String askCurrencyCode() throws InterruptOperationException {
        String result;
        writeMessage(res.getString("choose.currency.code"));
        do {
            result = readString();
            if (result.length() != 3)
                writeMessage(res.getString("invalid.data"));
        } while (result.length() != 3);
        return result.toUpperCase();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {
        writeMessage(String.format(res.getString("choose.denomination.and.count.format"), currencyCode));
        while (true) {
            String[] result = readString().split(" ", 2);
            try {
                int a = Integer.parseInt(result[0]);
                int b = Integer.parseInt(result[1]);
                if (a <= 0 || b <= 0) {
                    writeMessage(res.getString("invalid.data"));
                    continue;
                }
                return result;
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage(res.getString("choose.operation"));
        while (true) {
            String input = readString();
            try {
                int operCode = Integer.parseInt(input);
                return Operation.getAllowableOperationByOrdinal(operCode);
            } catch (Exception e) {
                writeMessage(res.getString("invalid.data"));
            }
        }
    }

    public static void printExitMessage(){
        writeMessage(res.getString("the.end"));
    }
}
