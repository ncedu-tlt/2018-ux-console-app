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
        String categoryName = CategoriesRepository.getInstance().get(product.getCategoryId()).getName();
        System.out.println("Category: " + product.getCategoryId() + " " + categoryName);
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
            description = getDescription(scanner);
        }

        return description;
    }

    public static long getCategoryId(Scanner scanner) {

        long idCategory;
        IOUtils.printSeparator();
        System.out.println("Enter ID of category for new product :");
        List<Category> categories = CategoriesRepository.getInstance().get();
        if(!categories.isEmpty()){
            for(Category category: categories){
                CategoryCommandsUtils.printCategory(category);
            }
        }
        else{
            System.out.println("No categories have been found. Add categories.");
            return -1;
        }

        IOUtils.printPrompt();

        String idCategoryString = scanner.nextLine();
        if (idCategoryString.isEmpty()) {
            IOUtils.printSeparator();
            System.out.println("ID can't be empty");
            idCategory = getCategoryId(scanner);
        }
        else {
            try{
                idCategory = Long.parseLong(idCategoryString);
            }
            catch(NumberFormatException e){
                System.out.println("ID must be a number");
                idCategory = getCategoryId(scanner);
            }
            Category category = CategoriesRepository.getInstance().get(idCategory);
            if (category == null) {
                IOUtils.printSeparator();
                System.out.println("No categories with such ID have been found");
                idCategory = getCategoryId(scanner);
            }
            else{
                idCategory = category.getId();
            }
        }

        return idCategory;

    }
}
