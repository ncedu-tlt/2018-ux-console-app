package ru.ncedu.consoleapp.menu.commands.offering;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.MainMenuCommand;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class OfferingMenuCommand implements Command {
    private static OfferingMenuCommand instance;

    public OfferingMenuCommand(){}

    public static OfferingMenuCommand getInstance() {
        if (instance == null) {
            instance = new OfferingMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        IOUtils.printSeparator();
        System.out.println("Offering:");
        IOUtils.printOption("1", "View");
        IOUtils.printOption("2", "Add");
        IOUtils.printOption("3", "Edit");
        IOUtils.printOption("4", "Remove");
        IOUtils.printOption("0", "Back");
        IOUtils.printSeparator();
        IOUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.next();
        int choice = userChoice.matches("[0-4]")?Integer.parseInt(userChoice):9;

        switch (choice) {
            case 0:
                return MainMenuCommand.getInstance();
            case 1:
                return ViewOfferingCommand.getInstance();
            case 2:
                return AddOfferingCommand.getInstance();
            case 3:
                return EditOfferingCommand.getInstance();
            case 4:
                return RemoveOfferingCommand.getInstance();
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }
    }
}
