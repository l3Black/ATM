package com.javarush.task.task26.task2613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString(){
        while (true) {
            try {
                String result = bis.readLine();
                if (result.isEmpty()) {
                    System.out.println("You did not enter data. Please, reenter: ");
                    continue;
                }
                return result;
            } catch (IOException e) {
                System.out.println("Oops! Please, reenter: ");
            }
        }
    }

    public static String askCurrencyCode(){
        String result;
        System.out.println("Please, enter currency code: ");
        do {
            result = readString();
            if (result.length() != 3)
                System.out.println("Error, the code must consist of 3 symbols. Reenter, please:");
        } while (result.length() != 3);

        return result.toUpperCase();

    }

    public static String[] getValidTwoDigits(String currencyCode) {
        System.out.println("Please, enter face value and number of banknotes:");
        while (true) {
            try {
                String[] result = readString().split(" ", 2);
                int a = Integer.parseInt(result[0]);
                int b = Integer.parseInt(result[1]);
                if (a <= 0 || b <= 0) {
                    System.out.println("The entered data is not correct. Reenter, please:");
                    continue;
                }
                return result;
            } catch (Exception e) {
                System.out.println("The entered data is not correct. Reenter, please:");
            }
        }
    }
}
