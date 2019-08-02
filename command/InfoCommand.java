package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;

class InfoCommand implements Command {
    @Override
    public void execute() {
        boolean atmIsEmpty = true;
       for (CurrencyManipulator curMan : CurrencyManipulatorFactory.getAllCurrencyManipulators()) {
           if (curMan.hasMoney()){
               atmIsEmpty = false;
               ConsoleHelper.writeMessage(curMan.getCurrencyCode() + " - " + curMan.getTotalAmount());
           }
       }
       if (atmIsEmpty) ConsoleHelper.writeMessage("No money available.");

    }
}
