package ru.ncedu.consoleapp.menu.commands.office;

import ru.ncedu.consoleapp.menu.commands.Command;

import ru.ncedu.consoleapp.menu.commands.office.utils.OfficeCommandsUtils;
import ru.ncedu.consoleapp.models.Office;
import ru.ncedu.consoleapp.repositories.OfficeRepository;

import java.util.Scanner;

public class AddOfficeCommand implements Command {

    private static AddOfficeCommand instance;

    private AddOfficeCommand() {
    }

    public static AddOfficeCommand getInstance() {
        if (instance == null) {
            instance = new AddOfficeCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        Office office = new Office();
        office.setName(OfficeCommandsUtils.getName(scanner));
        office.setPhoneNumber(OfficeCommandsUtils.getPhoneNumber(scanner));
        office.setCityId(OfficeCommandsUtils.getCityId(scanner));

        OfficeRepository.getInstance().add(office);

        return OfficeMenuCommand.getInstance();
    }
}
