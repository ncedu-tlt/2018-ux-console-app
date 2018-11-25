package ru.ncedu.consoleapp.menu.commands.products;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.products.utils.ProductCommandsUtils;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.ProductsRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class ViewProductsCommand implements Command{

    private static ViewProductsCommand instance;

    private ViewProductsCommand() {
    }

    public static ViewProductsCommand getInstance() {
        if (instance == null) {
            instance = new ViewProductsCommand();
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

        IOUtils.waitForEnter();

        return ProductsMenuCommand.getInstance();
    }
}
