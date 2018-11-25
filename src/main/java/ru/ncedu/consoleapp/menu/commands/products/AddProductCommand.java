package ru.ncedu.consoleapp.menu.commands.products;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.products.utils.ProductCommandsUtils;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.ProductsRepository;

import java.util.Scanner;

public class AddProductCommand implements Command{
    private static AddProductCommand instance;

    private AddProductCommand() {
    }

    public static AddProductCommand getInstance() {
        if (instance == null) {
            instance = new AddProductCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        Product product = new Product();
        product.setName(ProductCommandsUtils.getName(scanner));
        product.setCategory_id(ProductCommandsUtils.getCategoryId(scanner));
        product.setDescription(ProductCommandsUtils.getDescription(scanner));

        ProductsRepository.getInstance().add(product);

        return ProductsMenuCommand.getInstance();
    }
}
