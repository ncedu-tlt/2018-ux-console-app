package ru.ncedu.consoleapp.menu.commands.cities.utils;

import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.models.Country;
import ru.ncedu.consoleapp.repositories.CountryRepository;
import ru.ncedu.consoleapp.utils.IOUtils;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.cities.CitiesMenuCommand;
import ru.ncedu.consoleapp.menu.commands.countries.utils.CountryCommandUtils;
import java.util.List;

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

    //получение id страны, проверка на наличие страны с таким id
    public static long getCountryId(Scanner scanner) {

        IOUtils.printSeparator();
        System.out.println("Enter country id of this city:");
        List<Country> countries = CountryRepository.getInstance().get();
        if (countries.isEmpty()) {
            System.out.println("No countries have been found");
            IOUtils.waitForEnter();

            return -1;
        }

        for (Country country : countries) {
            CountryCommandUtils.printCountry(country);
        }
        IOUtils.printPrompt();

        long countryId;
        String countryIdStr = scanner.nextLine();
        if (countryIdStr.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Country id can't be empty");
            countryId = getCountryId(scanner);
        } else {

            //проверка на ввод числового значения
            try{
                countryId = Long.parseLong(countryIdStr);
            }

            catch(NumberFormatException e){
                System.out.println("Country id is not a number");
                countryId = getCountryId(scanner);
            }

            Country country = CountryRepository.getInstance().get(countryId);
            if(country == null){
                IOUtils.printSeparator();
                System.out.println("There is no country with such id");
                IOUtils.waitForEnter();
                countryId = getCountryId(scanner);
            }
        }

        return countryId;
    }
}