package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsRepository implements Repository<Product> {

    private static ProductsRepository instance;

    private final List<Product> products;

    private ProductsRepository() {
        products = new ArrayList<>();
    }

    public static ProductsRepository getInstance() {
        if (instance == null) {
            instance = new ProductsRepository();
        }
        return instance;
    }

    public List<Product> get() {
        return products;
    }

    @Override
    public Product get(long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    public Product add(Product object) {
        Product product = new Product(object);
        product.setId(generateId());

        products.add(product);

        return product;
    }

    public Product update(Product object) {
        Product product = new Product(object);

        products.remove(product);
        products.add(product);

        return product;
    }

    public boolean remove(Product product) {
        return products.remove(product);
    }

    @Override
    public boolean remove(long id) {
        return remove(new Product(id));
    }

    private long generateId() {
        long id = 0;
        for (Product product : products) {
            id = Math.max(id, product.getId());
        }
        return ++id;
    }

    public Product getByCategoryId(long id) {
        return products.stream().filter(product -> product.getCategoryId() == id).findFirst().orElse(null);
    }
}
