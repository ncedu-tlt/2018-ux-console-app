package ru.ncedu.consoleapp.menu.commands.office;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.office.utils.OfficeCommandsUtils;
import ru.ncedu.consoleapp.models.Office;
import ru.ncedu.consoleapp.repositories.OfficeRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class EditOfficeCommand implements Command {

    private static EditOfficeCommand instance;

    private EditOfficeCommand() {
    }

    public static EditOfficeCommand getInstance() {
        if (instance == null) {
            instance = new EditOfficeCommand();
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
        System.out.println("Enter ID of office to edit: ");
        long id = IOUtils.getLong();

        Office office = OfficeRepository.getInstance().get(id);
        if (office == null) {
            IOUtils.printSeparator();
            System.out.println("No offices with such ID have been found");
            IOUtils.waitForEnter();
            return this;
        }

        Scanner scanner = new Scanner(System.in);
        office.setName(OfficeCommandsUtils.getName(scanner));
        office.setPhoneNumber(OfficeCommandsUtils.getPhoneNumber(scanner));
        office.setCityId(OfficeCommandsUtils.getCityId(scanner));

        OfficeRepository.getInstance().update(office);

        IOUtils.printSeparator();
        System.out.println("Office has been update");

        return OfficeMenuCommand.getInstance();
    }
}
