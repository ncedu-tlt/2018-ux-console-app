package ru.ncedu.consoleapp.menu.commands.countries;

import java.util.Scanner;
import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.countries.utils.CountryCommandUtils;
import ru.ncedu.consoleapp.models.Country;
import ru.ncedu.consoleapp.repositories.CountryRepository;

public class AddCountryCommand implements Command {

    private static AddCountryCommand instance;

    private AddCountryCommand() {

    }

    public static AddCountryCommand getInstance() {
        if (instance == null) {
            instance = new AddCountryCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        Country country = new Country();
        country.setName(CountryCommandUtils.getName(scanner));
        country.setPhoneExtension(CountryCommandUtils.getPhoneExtension(scanner));
        country.setFlag(CountryCommandUtils.getFlag(scanner));

        CountryRepository.getInstance().add(country);

        return CountriesMenuCommand.getInstance();
    }

}