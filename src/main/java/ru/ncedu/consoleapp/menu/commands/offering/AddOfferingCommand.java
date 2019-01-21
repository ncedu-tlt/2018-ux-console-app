package ru.ncedu.consoleapp.menu.commands.offering;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.offering.utils.OfferingCommandsUtils;
import ru.ncedu.consoleapp.models.Offering;
import ru.ncedu.consoleapp.repositories.OfferingRepository;

import java.util.Scanner;

public class AddOfferingCommand implements Command {

    private static AddOfferingCommand instance;

    private AddOfferingCommand() {
    }

    public static AddOfferingCommand getInstance() {
        if (instance == null) {
            instance = new AddOfferingCommand();
        }
        return instance;
    }
    @Override
    public Command execute() {

        Scanner scanner = new Scanner(System.in);
        Offering offering = new Offering();

        offering.setProductId(OfferingCommandsUtils.getProductId(scanner));
        offering.setOfficeId(OfferingCommandsUtils.getOfficeId(scanner));
        offering.setOfferingPrice(OfferingCommandsUtils.getDoubleFromConsole(scanner, "offering price"));

        OfferingRepository.getInstance().add(offering);

        return OfferingMenuCommand.getInstance();
    }
}
