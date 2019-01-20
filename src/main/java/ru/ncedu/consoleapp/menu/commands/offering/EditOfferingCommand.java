package ru.ncedu.consoleapp.menu.commands.offering;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.offering.utils.OfferingCommandsUtils;
import ru.ncedu.consoleapp.models.Offering;
import ru.ncedu.consoleapp.repositories.OfferingRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class EditOfferingCommand implements Command{
    private static EditOfferingCommand instance;

    private EditOfferingCommand() {
    }

    public static EditOfferingCommand getInstance() {
        if (instance == null) {
            instance = new EditOfferingCommand();
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

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of offering to edit:");
        long id = IOUtils.getLong();

        Offering offering = OfferingRepository.getInstance().get(id);

        if(offering==null){
            System.out.println("No offering with ID");
            return EditOfferingCommand.getInstance();
        }

        offering.setOfferingPrice(Integer.valueOf(OfferingCommandsUtils.getStringFromConsole(scanner, "offering price")));
        long productId = OfferingCommandsUtils.getProductId(scanner);
        offering.setProductId(productId);
        offering.setOfficeId(Long.valueOf(OfferingCommandsUtils.getStringFromConsole(scanner, "office id")));//Как появятся офисы, сделать проверку :)
        offering.setDescription(OfferingCommandsUtils.getStringFromConsole(scanner, "description"));

        OfferingRepository.getInstance().update(offering);

        return OfferingMenuCommand.getInstance();
    }
}
