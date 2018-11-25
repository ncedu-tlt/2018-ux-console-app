package ru.ncedu.consoleapp.menu.commands.products;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.products.utils.ProductCommandsUtils;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.ProductsRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class RemoveProductCommand implements Command{

    private static RemoveProductCommand instance;

    private RemoveProductCommand() {
    }

    public static RemoveProductCommand getInstance() {
        if (instance == null) {
            instance = new RemoveProductCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Product> products = ProductsRepository.getInstance().get();

        IOUtils.printSeparator();

        if (products.isEmpty()) {
            System.out.println("No products have been found");
            IOUtils.waitForEnter();
            return ProductsMenuCommand.getInstance();
        }

        for (Product product : products) {
            ProductCommandsUtils.printProduct(product);
        }

        IOUtils.printSeparator();
        System.out.println("Enter ID of product to delete:");
        long id = IOUtils.getLong();

        boolean success = ProductsRepository.getInstance().remove(id);

        if (success) {
            IOUtils.printSeparator();
            System.out.println("Product " + id + " has been removed");
            IOUtils.waitForEnter();
            return ProductsMenuCommand.getInstance();
        } else {
            IOUtils.printSeparator();
            System.out.println("There is no product with such ID");
            IOUtils.waitForEnter();
            return this;
        }
    }
}
