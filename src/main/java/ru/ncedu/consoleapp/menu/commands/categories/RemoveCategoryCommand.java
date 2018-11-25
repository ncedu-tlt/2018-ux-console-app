package ru.ncedu.consoleapp.menu.commands.categories;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.categories.utils.CategoryCommandsUtils;
import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.CategoriesRepository;
import ru.ncedu.consoleapp.repositories.ProductsRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class RemoveCategoryCommand implements Command {

    private static RemoveCategoryCommand instance;

    private RemoveCategoryCommand() {
    }

    public static RemoveCategoryCommand getInstance() {
        if (instance == null) {
            instance = new RemoveCategoryCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        List<Category> categories = CategoriesRepository.getInstance().get();

        IOUtils.printSeparator();

        if (categories.isEmpty()) {
            System.out.println("No categories have been found");
            IOUtils.waitForEnter();
            return CategoriesMenuCommand.getInstance();
        }

        for (Category category : categories) {
            CategoryCommandsUtils.printCategory(category);
        }

        IOUtils.printSeparator();
        System.out.println("Enter ID of category to delete:");
        long id = IOUtils.getLong();

        Category category = CategoriesRepository.getInstance().get(id);
        if(category == null){
            IOUtils.printSeparator();
            System.out.println("There is no category with such ID");
            IOUtils.waitForEnter();
            return this;
        }
        else {
            List<Product> productsInCategory = ProductsRepository.getInstance().getByCategoryId(category.getId());
            if (!productsInCategory.isEmpty()) {
                for (Product product : productsInCategory) {
                    IOUtils.printSeparator();
                    ProductsRepository.getInstance().remove(product);
                    System.out.println("Product " + product.getId() + " has been removed");
                    IOUtils.printSeparator();
                }
            }
        }
        boolean success = CategoriesRepository.getInstance().remove(id);

        if (success) {
            IOUtils.printSeparator();
            System.out.println("Category " + id + " has been removed");
            IOUtils.waitForEnter();
            return CategoriesMenuCommand.getInstance();
        } else {
            IOUtils.printSeparator();
            System.out.println("There is no category with such ID");
            IOUtils.waitForEnter();
            return this;
        }
    }
}
