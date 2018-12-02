package ru.ncedu.consoleapp.menu.commands.citys;

import java.util.List;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.citys.utils.CityCommandsUtils;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CitysRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

public class ViewCitysCommand implements Command
{

    private static ViewCitysCommand instance;

    private ViewCitysCommand() {
    }

    public static ViewCitysCommand getInstance() 
    {
        if (instance == null) 
        {
            instance = new ViewCitysCommand();
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

        IOUtils.waitForEnter();

        return CitysMenuCommand.getInstance();
    }
    
}
