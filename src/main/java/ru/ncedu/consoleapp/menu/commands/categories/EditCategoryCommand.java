package ru.ncedu.consoleapp.menu.commands.categories;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.categories.utils.CategoryCommandsUtils;
import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.repositories.CategoriesRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;
import java.util.Scanner;

public class EditCategoryCommand implements Command {

    private static EditCategoryCommand instance;

    private EditCategoryCommand() {
    }

    public static EditCategoryCommand getInstance() {
        if (instance == null) {
            instance = new EditCategoryCommand();
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
        System.out.println("Enter ID of category to edit:");
        long id = IOUtils.getLong();

        Category category = CategoriesRepository.getInstance().get(id);
        if (category == null) {
            IOUtils.printSeparator();
            System.out.println("No categories with such ID have been found");
            IOUtils.waitForEnter();
            return this;
        }

        Scanner scanner = new Scanner(System.in);
        category.setName(CategoryCommandsUtils.getName(scanner));
        category.setDescription(CategoryCommandsUtils.getDescription(scanner));

        CategoriesRepository.getInstance().update(category);

        IOUtils.printSeparator();
        System.out.println("Category has been updated");

        return CategoriesMenuCommand.getInstance();
    }
}
