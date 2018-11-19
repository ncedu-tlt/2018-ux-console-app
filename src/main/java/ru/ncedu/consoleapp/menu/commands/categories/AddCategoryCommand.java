package ru.ncedu.consoleapp.menu.commands.categories;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.categories.utils.CategoryCommandsUtils;
import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.repositories.CategoriesRepository;

import java.util.Scanner;

public class AddCategoryCommand implements Command {

    private static AddCategoryCommand instance;

    private AddCategoryCommand() {
    }

    public static AddCategoryCommand getInstance() {
        if (instance == null) {
            instance = new AddCategoryCommand();
        }
        return instance;
    }

    @Override
    public Command execute() {
        Scanner scanner = new Scanner(System.in);

        Category category = new Category();
        category.setName(CategoryCommandsUtils.getName(scanner));
        category.setDescription(CategoryCommandsUtils.getDescription(scanner));

        CategoriesRepository.getInstance().add(category);

        return CategoriesMenuCommand.getInstance();
    }
}
