package ru.ncedu.consoleapp.menu.commands.office;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.office.utils.OfficeCommandsUtils;
import ru.ncedu.consoleapp.models.Office;
import ru.ncedu.consoleapp.repositories.OfficeRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class ViewOfficeCommand implements Command {

    private static ViewOfficeCommand instance;

    private ViewOfficeCommand() {
    }

    public static ViewOfficeCommand getInstance() {
        if (instance == null) {
            instance = new ViewOfficeCommand();
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

        IOUtils.waitForEnter();

        return OfficeMenuCommand.getInstance();
    }
}
