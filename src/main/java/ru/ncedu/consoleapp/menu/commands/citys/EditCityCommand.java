package ru.ncedu.consoleapp.menu.commands.citys;

import java.util.List;
import java.util.Scanner;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.citys.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CitysRepository;
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
        List<City> citys = CitysRepository.getInstance().get();

        IOUtils.printSeparator();

        if (citys.isEmpty()) 
        {
            System.out.println("No citys have been found");
            IOUtils.waitForEnter();
            return CitysMenuCommand.getInstance();
        }

        for (City city : citys) 
        {
            CityCommandsUtils.printCity(city);
        }

        IOUtils.printSeparator();
        System.out.println("Enter ID of city to edit:");
        long id = IOUtils.getLong();

        City city = CitysRepository.getInstance().get(id);
        if (city == null) 
        {
            IOUtils.printSeparator();
            System.out.println("No citys with such ID have been found");
            IOUtils.waitForEnter();
            return this;
        }

        Scanner scanner = new Scanner(System.in);
        city.setName(CityCommandsUtils.getName(scanner));
        city.setDescription(CityCommandsUtils.getDescription(scanner));
        city.setDescription(CityCommandsUtils.getPhoneExtension(scanner));

        CitysRepository.getInstance().update(city);

        IOUtils.printSeparator();
        System.out.println("City has been updated");

        return CitysMenuCommand.getInstance();
    }
    
}
