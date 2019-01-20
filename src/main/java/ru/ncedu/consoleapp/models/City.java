package ru.ncedu.consoleapp.models;

public class City implements Cloneable {
    private long id;
    private long countryId;
    private String name;
    private String phoneExtension;
    private String description;

    public City() {

    }

    public City(long id) {
        this.id = id;
    }

    public City(long id, long countryId, String name, String phoneExtension, String description) {
        this.id = id;
        this.countryId = countryId;
        this.name = name;
        this.phoneExtension = phoneExtension;
        this.description = description;
    }

    public City(City source) {
        this(source.getId(), source.getCountryId(), source.getName(), source.getPhoneExtension(), source.getDescription());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCountryId(long countryId){
        this.countryId = countryId;
    } //установка id страны, в которой находится город

    public long getCountryId(){
        return countryId;
    }  //получение id страны, в которой находится город

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneExtension() {
        return phoneExtension;
    }

    public void setPhoneExtension(String phoneExtension) {
        this.phoneExtension = phoneExtension;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof City) {
            City city = (City) obj;
            return city.getId() == id;
        }
        return false;
    }
}