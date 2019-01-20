package ru.ncedu.consoleapp.menu.commands.countries.utils;

import ru.ncedu.consoleapp.models.Country;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.Scanner;

public class CountryCommandUtils {

    public static void printCountry(Country country) {
        IOUtils.printOption(String.valueOf(country.getId()), country.getName());
        System.out.println("    " + country.getPhoneExtension());
        System.out.println("    " + country.getFlag());
    }

    public static String getName(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter country name:");
        IOUtils.printPrompt();

        String name = scanner.nextLine();
        if (name.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Country name can't be empty");
            name = getName(scanner);
        }

        return name;
    }

    public  static String getPhoneExtension(Scanner scanner){
        IOUtils.printSeparator();
        System.out.println("Enter phone extension:");
        IOUtils.printPrompt();

        String phoneExtension = scanner.nextLine();

        if(phoneExtension.isEmpty()){
            IOUtils.printSeparator();
            System.out.println("Phone extension can't be empty");
            phoneExtension = getPhoneExtension(scanner);
        }

        return phoneExtension;
    }

    public  static String getFlag(Scanner scanner){
        IOUtils.printSeparator();
        System.out.println("Enter flag:");
        IOUtils.printPrompt();

        String flag = scanner.nextLine();

        if(flag.isEmpty()){
            IOUtils.printSeparator();
            System.out.println("Flag can't be empty");
            flag = getFlag(scanner);
        }

        return flag;
    }
}