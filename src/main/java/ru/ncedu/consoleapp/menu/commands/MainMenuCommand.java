package ru.ncedu.consoleapp.menu.commands;

import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class MainMenuCommand implements Command {

    private static MainMenuCommand instance;

    private MainMenuCommand() {
    }

    public static MainMenuCommand getInstance() {
        if (instance == null) {
            instance = new MainMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        IOUtils.printSeparator();
        IOUtils.printOption("0", "Exit");
        IOUtils.printSeparator();
        IOUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return ExitCommand.getInstance();
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }
    }
}
