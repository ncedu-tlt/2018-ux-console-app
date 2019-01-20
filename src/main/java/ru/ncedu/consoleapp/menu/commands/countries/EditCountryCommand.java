package ru.ncedu.consoleapp.menu.commands.countries;

import java.util.List;
import java.util.Scanner;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.countries.utils.CountryCommandUtils;
import ru.ncedu.consoleapp.models.Country;
import ru.ncedu.consoleapp.repositories.CountryRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

public class EditCountryCommand implements Command{

    private static EditCountryCommand instance;

    private EditCountryCommand() {
    }

    public static EditCountryCommand getInstance() {
        if (instance == null) {
            instance = new EditCountryCommand();
        }
        return instance;
    }

    @Override
    public Command execute(){

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
        System.out.println("Enter ID of country to edit:");
        long id = IOUtils.getLong();

        Country country = CountryRepository.getInstance().get(id);
        if (country == null) {
            IOUtils.printSeparator();
            System.out.println("No countries with such ID have been found");
            IOUtils.waitForEnter();
            return this;
        }

        Scanner scanner = new Scanner(System.in);
        country.setName(CountryCommandUtils.getName(scanner));
        country.setPhoneExtension(CountryCommandUtils.getPhoneExtension(scanner));
        country.setFlag(CountryCommandUtils.getFlag(scanner));

        CountryRepository.getInstance().update(country);

        IOUtils.printSeparator();
        System.out.println("Country" + id + "has been updated");

        return CountriesMenuCommand.getInstance();
    }
}