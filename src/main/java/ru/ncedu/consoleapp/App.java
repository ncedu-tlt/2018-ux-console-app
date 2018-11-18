package ru.ncedu.consoleapp;

import ru.ncedu.consoleapp.menu.CommandExecutor;
import ru.ncedu.consoleapp.menu.commands.MainMenuCommand;

public class App {

    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor(MainMenuCommand.getInstance());
        executor.run();
    }

}