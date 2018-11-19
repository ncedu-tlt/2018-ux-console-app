package ru.ncedu.consoleapp.menu.commands.categories;

import ru.ncedu.consoleapp.menu.commands.Command;
import ru.ncedu.consoleapp.menu.commands.categories.utils.CategoryCommandsUtils;
import ru.ncedu.consoleapp.models.Category;
import ru.ncedu.consoleapp.repositories.CategoriesRepository;
import ru.ncedu.consoleapp.utils.IOUtils;

import java.util.List;

public class ViewCategoriesCommand implements Command {

    private static ViewCategoriesCommand instance;

    private ViewCategoriesCommand() {
    }

    public static ViewCategoriesCommand getInstance() {
        if (instance == null) {
            instance = new ViewCategoriesCommand();
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

        IOUtils.waitForEnter();

        return CategoriesMenuCommand.getInstance();
    }
}
