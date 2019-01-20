package ru.ncedu.consoleapp.menu.commands.countries;

import ru.ncedu.consoleapp.models.Country;
import ru.ncedu.consoleapp.models.City;
import ru.ncedu.consoleapp.repositories.CountryRepository;
import ru.ncedu.consoleapp.repositories.CitiesRepository;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.countries.utils.CountryCommandUtils;
import ru.ncedu.consoleapp.utils.IOUtils;
import java.util.List;

public class RemoveCountriesCommand implements Command {

    private static RemoveCountriesCommand instance;

    private RemoveCountriesCommand() {
    }

    public static RemoveCountriesCommand getInstance() {
        if (instance == null) {
            instance = new RemoveCountriesCommand();
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

        IOUtils.printSeparator();
        System.out.println("Enter county id to delete:");
        long id = IOUtils.getLong();

        Country country = CountryRepository.getInstance().get(id);
        if(country == null){
            IOUtils.printSeparator();
            System.out.println("There is no country with such ID");
            IOUtils.waitForEnter();
            return this;
        }
        else {
            List<City> counrysCities = CitiesRepository.getInstance().getByCountryId(country.getId());
            if (!counrysCities.isEmpty()) {
                for (City city : counrysCities) {
                    IOUtils.printSeparator();
                    CitiesRepository.getInstance().remove(city);
                    System.out.println("City " + city.getId() + " has been removed");
                    IOUtils.printSeparator();
                }
            }
        }
        boolean success = CountryRepository.getInstance().remove(id);

        if (success) {
            IOUtils.printSeparator();
            System.out.println("Country " + id + " has been removed");
            IOUtils.waitForEnter();
            return CountriesMenuCommand.getInstance();
        } else {
            IOUtils.printSeparator();
            System.out.println("There is no country with such ID");
            IOUtils.waitForEnter();
            return this;
        }
    }
}