package ru.ncedu.consoleapp.menu.commands.office.utils;

import ru.ncedu.consoleapp.menu.commands.cities.AddCityCommand;
import ru.ncedu.consoleapp.menu.commands.cities.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.models.Office;
import ru.ncedu.consoleapp.repositories.CitiesRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class OfficeCommandsUtils {

    public static void printOffice(Office office) {
        IOUtils.printOption(String.valueOf(office.getId()), office.getName());
//        System.out.println(office.getDescription());
        System.out.println(office.getPhoneNumber());
        System.out.println("City ID: " + office.getCityId());
    }

    public static String getName(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter office name: ");
        IOUtils.printPrompt();

        String name = scanner.nextLine();
        if (name.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Office name can't be empty!");
            name = getName(scanner);
        }

        return name;
    }

    public static String getPhoneNumber(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter phone of this office: ");
        IOUtils.printPrompt();

        String phone = scanner.nextLine();
        if (phone.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Phone can't be empty!");
            phone = getPhoneNumber(scanner);
        }
        return phone;
    }

    public static long getCityId(Scanner scanner) {
        IOUtils.printSeparator();

        List<City> cities = CitiesRepository.getInstance().get();
        long id;

        if (cities.isEmpty()){
            AddCityCommand.getInstance().execute();
            id = getCityId(scanner);
        }
        else {
            for (City city : cities){
                CityCommandsUtils.printCity(city);
            }
            System.out.println("Enter city id where locate office or enter -1 if you want to create new city: ");

            id = IOUtils.getLong();

            if (id==-1){
                AddCityCommand.getInstance().execute();
                id = getCityId(scanner);
            }
            else{
                City city = CitiesRepository.getInstance().get(id);
                if (city == null) {
                    IOUtils.printSeparator();
                    System.out.println("City with ID " + id + " not found");
                    id = getCityId(scanner);
                }
            }
        }
        return id;
    }

}
