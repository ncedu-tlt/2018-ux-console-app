package ru.ncedu.consoleapp.menu.commands;

import ru.ncedu.consoleapp.menu.commands.categories.CategoriesMenuCommand;
import ru.ncedu.consoleapp.menu.commands.offering.OfferingMenuCommand;
import ru.ncedu.consoleapp.menu.commands.office.OfficeMenuCommand;
import ru.ncedu.consoleapp.menu.commands.products.ProductsMenuCommand;
import ru.ncedu.consoleapp.menu.commands.cities.CitiesMenuCommand;
import ru.ncedu.consoleapp.menu.commands.countries.CountriesMenuCommand;
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
        IOUtils.printOption("1", "Categories");
        IOUtils.printOption("2", "Products");
        IOUtils.printOption("3", "Import data from yandex market");
        IOUtils.printOption("4", "Cities");
        IOUtils.printOption("5", "Countries");
        IOUtils.printOption("6", "Offices");
        IOUtils.printOption("7", "Offerings");
        IOUtils.printOption("0", "Exit");
        IOUtils.printSeparator();
        IOUtils.printPrompt();

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 0:
                return ExitCommand.getInstance();
            case 1:
                return CategoriesMenuCommand.getInstance();
            case 2:
                return ProductsMenuCommand.getInstance();
            case 3:
                return ImportDataMenuCommand.getInstance();
            case 4:
                return CitiesMenuCommand.getInstance();
            case 5:
                return CountriesMenuCommand.getInstance();
            case 6:
                return OfficeMenuCommand.getInstance();
            case 7:
                return OfferingMenuCommand.getInstance();
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }
    }
}
