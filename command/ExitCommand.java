package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class ExitCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("You want exit? 'y'(yes) or 'n'(no):");
        String answer = ConsoleHelper.readString().trim();
        if (answer.equalsIgnoreCase("y"))
            ConsoleHelper.writeMessage("Goodbye!");
    }
}
