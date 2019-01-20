package ru.ncedu.consoleapp.menu.commands.offering;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.offering.utils.OfferingCommandsUtils;
import ru.ncedu.consoleapp.models.Offering;
import ru.ncedu.consoleapp.repositories.OfferingRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class ViewOfferingCommand implements Command {
    private static ViewOfferingCommand instance;

    private ViewOfferingCommand() {
    }

    public static ViewOfferingCommand getInstance() {
        if (instance == null) {
            instance = new ViewOfferingCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Offering> offering = OfferingRepository.getInstance().get();

        IOUtils.printSeparator();

        if (offering.isEmpty()) {
            System.out.println("No offering have been found");
            IOUtils.waitForEnter();
            return OfferingMenuCommand.getInstance();
        }

        for (Offering offer : offering) {
            OfferingCommandsUtils.printOffer(offer);
        }

        IOUtils.waitForEnter();

        return OfferingMenuCommand.getInstance();
    }
}
