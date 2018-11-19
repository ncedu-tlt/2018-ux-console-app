package ru.ncedu.consoleapp.menu.commands.categories.utils;

import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class CategoryCommandsUtils {

    public static void printCategory(Category category) {
        IOUtils.printOption(String.valueOf(category.getId()), category.getName());
        System.out.println("    " + category.getDescription());
    }

    public static String getName(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter category name:");
        IOUtils.printPrompt();

        String name = scanner.nextLine();
        if (name.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Name can't be empty");
            name = getName(scanner);
        }

        return name;
    }

    public static String getDescription(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter category description:");
        IOUtils.printPrompt();

        String description = scanner.nextLine();
        if (description.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Description can't be empty");
            description = getName(scanner);
        }

        return description;
    }

}
