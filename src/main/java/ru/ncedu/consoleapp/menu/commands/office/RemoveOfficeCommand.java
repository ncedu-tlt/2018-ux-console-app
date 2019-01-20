package ru.ncedu.consoleapp.menu.commands.office;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.office.utils.OfficeCommandsUtils;
import ru.ncedu.consoleapp.models.Office;
import ru.ncedu.consoleapp.repositories.OfficeRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class RemoveOfficeCommand implements Command {

    private static RemoveOfficeCommand instance;

    private RemoveOfficeCommand() {
    }

    public static RemoveOfficeCommand getInstance() {
        if (instance == null) {
            instance = new RemoveOfficeCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {

        List<Office> offices = OfficeRepository.getInstance().get();

        IOUtils.printSeparator();

        if (offices.isEmpty()) {
            System.out.println("Offices not found");
            IOUtils.waitForEnter();
            return OfficeMenuCommand.getInstance();
        }

        for (Office office : offices) {
            OfficeCommandsUtils.printOffice(office);
        }

        IOUtils.printSeparator();
        System.out.println("Enter ID of office to delete: ");
        long id = IOUtils.getLong();

        boolean success = OfficeRepository.getInstance().remove(id);

        if (success) {
            IOUtils.printSeparator();
            System.out.println("Office with ID " + id + " has been delete");
            IOUtils.waitForEnter();
            return OfficeMenuCommand.getInstance();
        } else {
            IOUtils.printSeparator();
            System.out.println("Office with ID " + id + " not found");
            IOUtils.waitForEnter();
            return this;
        }
    }
}
