package ru.ncedu.consoleapp.menu.commands.office;


import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.MainMenuCommand;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class OfficeMenuCommand implements Command {
    private static OfficeMenuCommand instance;

    private OfficeMenuCommand() {
    }

    public static OfficeMenuCommand getInstance() {
        if (instance == null) {
            instance = new OfficeMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        IOUtils.printSeparator();
        System.out.println("Offices:");
        IOUtils.printOption("1", "View");
        IOUtils.printOption("2", "Add");
        IOUtils.printOption("3", "Edit");
        IOUtils.printOption("4", "Remove");
        IOUtils.printOption("0", "Back");
        IOUtils.printSeparator();
        IOUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewOfficeCommand.getInstance();
            case 2:
                return AddOfficeCommand.getInstance();
            case 3:
                return EditOfficeCommand.getInstance();
            case 4:
                return RemoveOfficeCommand.getInstance();
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }

    }
}
