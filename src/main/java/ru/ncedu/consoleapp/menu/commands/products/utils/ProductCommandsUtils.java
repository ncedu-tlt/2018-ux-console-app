package ru.ncedu.consoleapp.menu.commands.products.utils;

import ru.ncedu.consoleapp.menu.commands.categories.utils.CategoryCommandsUtils;
import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.models.Product;
import ru.ncedu.consoleapp.repositories.CategoriesRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class ProductCommandsUtils {

    public static void printProduct(Product product) {
        IOUtils.printOption(String.valueOf(product.getId()), product.getName());
        System.out.println("Category: "+ product.getCategoryId());
        System.out.println("    " + product.getDescription());
    }

    public static String getName(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter product name:");
        IOUtils.printPrompt();

        String name = scanner.nextLine();
        if (name.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Name can't be empty");
            name = getName(scanner);
        }

        return name;
    }

    public static String getDescription(Scanner scanner) {
        IOUtils.printSeparator();
        System.out.println("Enter product description:");
        IOUtils.printPrompt();

        String description = scanner.nextLine();
        if (description.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("Description can't be empty");
            description = getName(scanner);
        }

        return description;
    }

    public static long getCategoryId(Scanner scanner) {

        IOUtils.printSeparator();
        System.out.println("Enter ID of category for new product :");
        List<Category> categories = CategoriesRepository.getInstance().get();
        if(!categories.isEmpty()){
            for(Category category: categories){
                CategoryCommandsUtils.printCategory(category);
            }
        }
        IOUtils.printPrompt();

        String idCategoryString = scanner.nextLine();
        if (idCategoryString.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("ID can't be empty");
            idCategoryString = getName(scanner);
        }

        return Long.parseLong(idCategoryString);

    }
}
