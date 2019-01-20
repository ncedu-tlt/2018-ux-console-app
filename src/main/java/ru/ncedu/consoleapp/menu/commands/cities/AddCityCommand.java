package ru.ncedu.consoleapp.menu.commands.cities;

import java.util.Scanner;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.cities.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CitiesRepository;


public class AddCityCommand implements Command {
    
    private static AddCityCommand instance;
    
    private AddCityCommand() {
        
    }
    
    public static AddCityCommand getInstance() {
        if (instance == null) {
            instance = new AddCityCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        City city = new City();
        long param = CityCommandsUtils.getCountryId(scanner);
        if(param == -1)return CitiesMenuCommand.getInstance();
        else city.setCountryId(param);                         //установка id страны, в которой находится город
        city.setName(CityCommandsUtils.getName(scanner));
        city.setDescription(CityCommandsUtils.getDescription(scanner));
        city.setPhoneExtension(CityCommandsUtils.getPhoneExtension(scanner));

        CitiesRepository.getInstance().add(city);

        return CitiesMenuCommand.getInstance();
    }
    
}