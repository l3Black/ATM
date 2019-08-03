package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CashMachine;
import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;
import com.javarush.task.task26.task2613.exception.NotEnoughMoneyException;

import java.util.Map;
import java.util.ResourceBundle;

class WithdrawCommand implements Command {
    private ResourceBundle res = ResourceBundle.getBundle(CashMachine.RESOURCE_PATH + "withdraw_en");
    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage(res.getString("before"));
        String codeCurrency = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator curMan = CurrencyManipulatorFactory.getManipulatorByCurrencyCode(codeCurrency);
        while (true){
            ConsoleHelper.writeMessage(res.getString("specify.amount"));
            String amountStr = ConsoleHelper.readString();
            try {
                int amountInt = Integer.parseInt(amountStr);
                if (!curMan.isAmountAvailable(amountInt)){
                    ConsoleHelper.writeMessage(res.getString("not.enough.money"));
                }
                Map<Integer, Integer> map = curMan.withdrawAmount(amountInt);
                map.forEach((key, value) -> ConsoleHelper.writeMessage(key + " - " + value));
                break;
            } catch (NumberFormatException e){
                ConsoleHelper.writeMessage(res.getString("specify.not.empty.amount"));
            } catch (NotEnoughMoneyException e){
                ConsoleHelper.writeMessage(res.getString("exact.amount.not.available"));
                break;
            }
        }
    }
}
