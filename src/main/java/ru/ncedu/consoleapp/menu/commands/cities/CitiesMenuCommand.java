package ru.ncedu.consoleapp.menu.commands.cities;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.MainMenuCommand;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class CitiesMenuCommand implements Command {
    private static CitiesMenuCommand instance;
    
    private CitiesMenuCommand() {
    }

    public static CitiesMenuCommand getInstance() {
        if (instance == null) {
            instance = new CitiesMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() 
    {
        IOUtils.printSeparator();
        System.out.println("Cities:");
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
                return ViewCitiesCommand.getInstance();
            case 2:
                return AddCityCommand.getInstance();
            case 3:
                return EditCityCommand.getInstance();
            case 4:
                return RemoveCityCommand.getInstance();
            case 9:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }
    }
}