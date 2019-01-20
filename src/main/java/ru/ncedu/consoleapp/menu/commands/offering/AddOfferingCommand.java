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

        offering.setOfferingPrice(Integer.valueOf(OfferingCommandsUtils.getStringFromConsole(scanner, "offeringPrice")));
        long productId = OfferingCommandsUtils.getProductId(scanner);
        offering.setProductId(productId);
        offering.setOfficeId(Long.valueOf(OfferingCommandsUtils.getStringFromConsole(scanner, "office id")));//Как появятся офисы, сделать проверку :)
        offering.setDescription(OfferingCommandsUtils.getStringFromConsole(scanner, "description"));

        OfferingRepository.getInstance().add(offering);

        return OfferingMenuCommand.getInstance();
    }
}
