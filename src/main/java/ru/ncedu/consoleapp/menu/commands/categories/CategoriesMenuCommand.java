package ru.ncedu.consoleapp.menu.commands.categories;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.MainMenuCommand;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class CategoriesMenuCommand implements Command {

    private static CategoriesMenuCommand instance;

    private CategoriesMenuCommand() {
    }

    public static CategoriesMenuCommand getInstance() {
        if (instance == null) {
            instance = new CategoriesMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        IOUtils.printSeparator();
        System.out.println("Categories:");
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
                return ViewCategoriesCommand.getInstance();
            case 2:
                return AddCategoryCommand.getInstance();
            case 3:
                return EditCategoryCommand.getInstance();
            case 4:
                return RemoveCategoryCommand.getInstance();
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }
    }
}
