package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String codeCurrency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator curMan = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(codeCurrency);
        while (true){
            ConsoleHelper.writeMessage("Please, enter amount:");
            String amountStr = ConsoleHelper.readString();
            try {
                int amountInt = Integer.parseInt(amountStr);
                if (!curMan.isAmountAvailable(amountInt)){
                    throw new NotEnoughMoneyException();
                }
                Map<Integer, Integer> map = curMan.withdrawAmount(amountInt);
                map.forEach((key, value) -> ConsoleHelper.writeMessage(key + " - " + value));
                break;
            } catch (NumberFormatException e){
                ConsoleHelper.writeMessage("Data is not correct");
            } catch (NotEnoughMoneyException e){
                ConsoleHelper.writeMessage("Not enough cash at the ATM");
                break;
            }

        }
    }
}
