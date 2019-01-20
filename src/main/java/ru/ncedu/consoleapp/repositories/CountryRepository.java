package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.Country;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountryRepository implements Repository<Country>{

    private static CountryRepository instance;

    private final List<Country> countries;

    private CountryRepository(){
        countries = new ArrayList<>();
    }

    public static CountryRepository getInstance(){
        if (instance == null){
            instance = new CountryRepository();
        }
        return instance;
    }

    public List<Country> get(){
        return countries;
    }

    @Override
    public Country get(long id){ return countries.stream().filter(country -> country.getId() == id).findFirst().orElse(null); }

    public Country add(Country object){
        Country country = new Country(object);
        country.setId(generateId());

        countries.add(country);

        return country;
    }

    public Country update(Country object){
        Country country = new Country(object);

        countries.remove(country);
        countries.add(country);

        return country;
    }

    public boolean remove(Country country) {
        return countries.remove(country);
    }

    @Override
    public boolean remove(long id) {
        return remove(new Country(id));
    }

    private long generateId(){
        long id = 0;
        for (Country country : countries){
            id = Math.max(id, country.getId());
        }
        return ++id;
    }
}