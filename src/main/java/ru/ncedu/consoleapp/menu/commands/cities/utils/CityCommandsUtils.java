package ru.ncedu.consoleapp.menu.commands.cities.utils;

import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;


public class CityCommandsUtils {
    public static void printCity(City city) {
        IOUtils.printOption(String.valueOf(city.getId()), city.getName());
        System.out.println("    " + city.getDescription());
        System.out.println("    " + city.getPhoneExtension());
    }
    
    public static String getName(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter city name:");
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
        System.out.println("Enter city description:");
        IOUtils.printPrompt();

        String description = scanner.nextLine();
        if (description.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Description can't be empty");
            description = getName(scanner);
        }

        return description;
    }
    
    public static String getPhoneExtension(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter phone extension of this city:");
        IOUtils.printPrompt();

        String phoneExtension = scanner.nextLine();
        if (phoneExtension.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Phone pxtension can't be empty");
            phoneExtension = getName(scanner);
        }

        return phoneExtension;
    }
}