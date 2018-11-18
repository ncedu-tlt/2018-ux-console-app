package ru.ncedu.consoleapp.menu.commands;

import ru.ncedu.consoleapp.utils.IOUtils;

public class ExitCommand implements Command {

    private static ExitCommand instance;

    private ExitCommand() {
    }

    public static ExitCommand getInstance() {
        if (instance == null) {
            instance = new ExitCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        IOUtils.printSeparator();
        System.out.println("Have a nice day!");

        return null;
    }
}
