package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoriesRepository implements Repository<Category> {

    private static CategoriesRepository instance;

    private final List<Category> categories;

    private CategoriesRepository() {
        categories = new ArrayList<>();
    }

    public static CategoriesRepository getInstance() {
        if (instance == null) {
            instance = new CategoriesRepository();
        }
        return instance;
    }

    public List<Category> get() {
        return categories;
    }

    @Override
    public Category get(long id) {
        return categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }

    public Category add(Category object) {
        Category category = new Category(object);
        category.setId(generateId());

        categories.add(category);

        return category;
    }

    public Category update(Category object) {
        Category category = new Category(object);

        categories.remove(category);
        categories.add(category);

        return category;
    }

    public boolean remove(Category category) {
        return categories.remove(category);
    }

    @Override
    public boolean remove(long id) {
        return remove(new Category(id));
    }

    private long generateId() {
        long id = 0;
        for (Category category : categories) {
            id = Math.max(id, category.getId());
        }
        return ++id;
    }
}
