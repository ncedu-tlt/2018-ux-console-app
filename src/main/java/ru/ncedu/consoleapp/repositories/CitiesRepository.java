package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CitiesRepository implements Repository<City> {

	private static CitiesRepository instance;

	private final List<City> cities;

	private CitiesRepository(){
        	cities = new ArrayList<>();
	}

	public static CitiesRepository getInstance(){
        	if (instance == null){
            		instance = new CitiesRepository();
		}
        	return instance;
	}

	public List<City> get(){
        	return cities;
	}

	@Override
	public City get(long id){
		return cities.stream().filter(city -> city.getId() == id).findFirst().orElse(null);
	}

	public City add(City object){
        	City city = new City(object);
        	city.setId(generateId());

        	cities.add(city);

        	return city;
    	}

	public City update(City object){
		City city = new City(object);

		cities.remove(city);
		cities.add(city);

		return city;
	}

	public boolean remove(City city) {
		return cities.remove(city);
	}

	@Override
	public boolean remove(long id) {
		return remove(new City(id));
	}

	private long generateId(){
		long id = 0;
		for (City city : cities){
			id = Math.max(id, city.getId());
		}
		return ++id;
	}
}