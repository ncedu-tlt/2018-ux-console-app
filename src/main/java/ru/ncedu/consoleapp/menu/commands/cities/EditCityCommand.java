package ru.ncedu.consoleapp.menu.commands.cities;

import java.util.List;
import java.util.Scanner;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.cities.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CitiesRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

public class EditCityCommand implements Command 
{
    private static EditCityCommand instance;

    private EditCityCommand() {
    }

    public static EditCityCommand getInstance() 
    {
        if (instance == null) 
        {
            instance = new EditCityCommand();
        }
        return instance;
    }
    
    @Override
    public Command execute() 
    {
        List<City> cities = CitiesRepository.getInstance().get();

        IOUtils.printSeparator();

        if (cities.isEmpty()) {
            System.out.println("No cities have been found");
            IOUtils.waitForEnter();
            return CitiesMenuCommand.getInstance();
        }

        for (City city : cities) {
            CityCommandsUtils.printCity(city);
        }

        IOUtils.printSeparator();
        System.out.println("Enter ID of city to edit:");
        long id = IOUtils.getLong();

        City city = CitiesRepository.getInstance().get(id);
        if (city == null) {
            IOUtils.printSeparator();
            System.out.println("No citys with such ID have been found");
            IOUtils.waitForEnter();
            return this;
        }

        Scanner scanner = new Scanner(System.in);
        city.setName(CityCommandsUtils.getName(scanner));
        city.setDescription(CityCommandsUtils.getDescription(scanner));
        city.setDescription(CityCommandsUtils.getPhoneExtension(scanner));

        CitiesRepository.getInstance().update(city);

        IOUtils.printSeparator();
        System.out.println("City has been updated");

        return CitiesMenuCommand.getInstance();
    }
    
}
