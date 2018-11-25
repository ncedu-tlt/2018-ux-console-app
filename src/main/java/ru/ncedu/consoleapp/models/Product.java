package ru.ncedu.consoleapp.models;

public class Product implements  Cloneable{

    private long id;
    private String name;
    private long category_id;
    private String description;

    public Product() {
    }

    public Product(long id) {
        this.id = id;
    }

    public Product(long id, String name, long category_id, String description) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.description = description;
    }

    public Product (Product source) {
        this(source.getId(), source.getName(), source.getCategoryId(), source.getDescription());
    }

    public long getCategoryId() {
        return this.category_id;
    }

    public void setCategory_id(long category_id){
        this.category_id = category_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Product) {
            Product product = (Product) obj;
            return product.getId() == id;
        }
        return false;
    }
}
