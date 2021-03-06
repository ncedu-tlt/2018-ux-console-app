package ru.ncedu.consoleapp.menu.commands.cities;

import java.util.List;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.cities.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CitiesRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

public class RemoveCityCommand implements Command  {
    
    private static RemoveCityCommand instance;

    private RemoveCityCommand() {
        
    }

    public static RemoveCityCommand getInstance() {
        if (instance == null) {
            instance = new RemoveCityCommand();
        }
        return instance;
    }
    @Override
    public Command execute() {
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
        System.out.println("Enter ID of city to delete:");
        long id = IOUtils.getLong();
        
        boolean success = CitiesRepository.getInstance().remove(id);

        if (success) {
            IOUtils.printSeparator();
            System.out.println("City " + id + " has been removed");
            IOUtils.waitForEnter();
            return CitiesMenuCommand.getInstance();
        } 
        else {
            IOUtils.printSeparator();
            System.out.println("There is no city with such ID");
            IOUtils.waitForEnter();
            return this;
        }
        
    }
    
}
