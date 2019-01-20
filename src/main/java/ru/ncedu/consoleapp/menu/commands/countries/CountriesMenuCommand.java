package ru.ncedu.consoleapp.menu.commands.countries;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.MainMenuCommand;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class CountriesMenuCommand implements Command{

    private static CountriesMenuCommand instance;

    private CountriesMenuCommand() {
    }

    public static CountriesMenuCommand getInstance() {
        if (instance == null) {
            instance = new CountriesMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        IOUtils.printSeparator();
        System.out.println("Countries:");
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
                return ViewCountriesCommand.getInstance();
            case 2:
                return AddCountryCommand.getInstance();
            case 3:
                return EditCountryCommand.getInstance();
            case 4:
                return RemoveCountriesCommand.getInstance();
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }
    }
}