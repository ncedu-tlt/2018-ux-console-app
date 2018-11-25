package ru.ncedu.consoleapp.menu.commands.products;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.products.utils.ProductCommandsUtils;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.ProductsRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class EditProductCommand implements Command{

    private static EditProductCommand instance;

    private EditProductCommand() {
    }

    public static EditProductCommand getInstance() {
        if (instance == null) {
            instance = new EditProductCommand();
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
        System.out.println("Enter ID of product to edit:");
        long id = IOUtils.getLong();

        Product product = ProductsRepository.getInstance().get(id);
        if (product == null) {
            IOUtils.printSeparator();
            System.out.println("No products with such ID have been found");
            IOUtils.waitForEnter();
            return this;
        }

        Scanner scanner = new Scanner(System.in);
        product.setName(ProductCommandsUtils.getName(scanner));
        product.setCategory_id(ProductCommandsUtils.getCategoryId(scanner));
        product.setDescription(ProductCommandsUtils.getDescription(scanner));

        ProductsRepository.getInstance().update(product);

        IOUtils.printSeparator();
        System.out.println("Product has been updated");

        return ProductsMenuCommand.getInstance();
    }
}
