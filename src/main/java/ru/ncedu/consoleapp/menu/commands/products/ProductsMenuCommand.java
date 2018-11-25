package ru.ncedu.consoleapp.menu.commands.products;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.MainMenuCommand;
import ru.ncedu.consoleapp.menu.commands.categories.*;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class ProductsMenuCommand implements Command {

    private static ProductsMenuCommand instance;

    private ProductsMenuCommand() {
    }

    public static ProductsMenuCommand getInstance() {
        if (instance == null) {
            instance = new ProductsMenuCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        IOUtils.printSeparator();
        System.out.println("Products:");
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
                return ViewProductsCommand.getInstance();
            case 2:
                return AddProductCommand.getInstance();
            case 3:
                return EditProductCommand.getInstance();
            case 4:
                return RemoveProductCommand.getInstance();
            default:
                IOUtils.showMessageAndWait("Unexpected option!");
                return this;
        }
    }
}
