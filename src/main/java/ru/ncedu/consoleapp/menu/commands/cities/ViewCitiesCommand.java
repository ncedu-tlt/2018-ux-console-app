package ru.ncedu.consoleapp.menu.commands.cities;

import java.util.List;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.cities.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CitiesRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

public class ViewCitiesCommand implements Command{

    private static ViewCitiesCommand instance;

    private ViewCitiesCommand() {
    }

    public static ViewCitiesCommand getInstance() {
        if (instance == null) {
            instance = new ViewCitiesCommand();
        }
        return instance;
    }
    
    @Override
    public Command execute() {
        List<City> citys = CitiesRepository.getInstance().get();

        IOUtils.printSeparator();

        if (citys.isEmpty()) {
            System.out.println("No citys have been found");
            IOUtils.waitForEnter();
            return CitiesMenuCommand.getInstance();
        }

        for (City city : citys) {
            CityCommandsUtils.printCity(city);
        }

        IOUtils.waitForEnter();

        return CitiesMenuCommand.getInstance();
    }
    
}
