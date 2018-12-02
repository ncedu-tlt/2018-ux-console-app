package ru.ncedu.consoleapp.menu.commands.citys;

import java.util.Scanner;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.citys.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CitysRepository;

public class AddCityCommand implements Command 
{
    
    private static AddCityCommand instance;
    
    private AddCityCommand() 
    {
        
    }
    
    public static AddCityCommand getInstance() 
    {
        if (instance == null) 
        {
            instance = new AddCityCommand();
        }
        return instance;
    }

    @Override
    public Command execute() 
    {
        Scanner scanner = new Scanner(System.in);

        City city = new City();
        city.setName(CityCommandsUtils.getName(scanner));
        city.setDescription(CityCommandsUtils.getDescription(scanner));
        city.setPhoneExtension(CityCommandsUtils.getPhoneExtension(scanner));

        CitysRepository.getInstance().add(city);

        return CitysMenuCommand.getInstance();
    }
    
}