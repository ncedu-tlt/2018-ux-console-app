package ru.ncedu.consoleapp.menu.commands.countries;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.countries.utils.CountryCommandUtils;
import ru.ncedu.consoleapp.models.Country;
import ru.ncedu.consoleapp.repositories.CountryRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class ViewCountriesCommand implements Command {

    private static ViewCountriesCommand instance;

    private ViewCountriesCommand() {
    }

    public static ViewCountriesCommand getInstance() {

        if (instance == null) {
            instance = new ViewCountriesCommand();
        }

        return instance;
    }

    @Override
    public Command execute() {
        List<Country> countries = CountryRepository.getInstance().get();

        IOUtils.printSeparator();

        if (countries.isEmpty()) {
            System.out.println("No countries have been found");
            IOUtils.waitForEnter();

            return CountriesMenuCommand.getInstance();
        }

        for (Country country : countries) {
            CountryCommandUtils.printCountry(country);
        }

        IOUtils.waitForEnter();

        return CountriesMenuCommand.getInstance();
    }
}