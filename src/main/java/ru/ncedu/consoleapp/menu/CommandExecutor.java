package ru.ncedu.consoleapp.menu;

import ru.ncedu.consoleapp.menu.commands.Command;

public class CommandExecutor {

    private Command command;

    public CommandExecutor(Command initialCommand) {
        this.command = initialCommand;
    }

    public void run() {
        while (command != null) {
            command = command.execute();
        }
    }

}
