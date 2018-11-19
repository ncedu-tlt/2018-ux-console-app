package ru.ncedu.consoleapp.models;

public class Category implements Cloneable {

    private long id;
    private String name;
    private String description;

    public Category() {
    }

    public Category(long id) {
        this.id = id;
    }

    public Category(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category(Category source) {
        this(source.getId(), source.getName(), source.getDescription());
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
        if (obj instanceof Category) {
            Category category = (Category) obj;
            return category.getId() == id;
        }
        return false;
    }
}
