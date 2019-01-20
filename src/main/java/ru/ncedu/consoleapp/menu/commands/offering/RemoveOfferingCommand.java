package ru.ncedu.consoleapp.menu.commands.offering;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.offering.utils.OfferingCommandsUtils;
import ru.ncedu.consoleapp.models.Offering;
import ru.ncedu.consoleapp.repositories.OfferingRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class RemoveOfferingCommand implements Command {
    private static RemoveOfferingCommand instance;

    private RemoveOfferingCommand() {
    }

    public static RemoveOfferingCommand getInstance() {
        if (instance == null) {
            instance = new RemoveOfferingCommand();
        }
        return instance;
    }
    @Override
    public Command execute() {
        List<Offering> offerings = OfferingRepository.getInstance().get();

        IOUtils.printSeparator();

        if (offerings.isEmpty()) {
            System.out.println("No offering have been found");
            IOUtils.waitForEnter();
            return OfferingMenuCommand.getInstance();
        }

        for (Offering offering : offerings) {
            OfferingCommandsUtils.printOffer(offering);
        }

        IOUtils.waitForEnter();

        System.out.println("Enter ID of offering to edit:");
        long id = IOUtils.getLong();

        Offering offering = OfferingRepository.getInstance().get(id);

        if(offering==null){
            System.out.println("No offering with ID");
            return RemoveOfferingCommand.getInstance();
        }

        OfferingRepository.getInstance().remove(id);

        return OfferingMenuCommand.getInstance();
    }
}
