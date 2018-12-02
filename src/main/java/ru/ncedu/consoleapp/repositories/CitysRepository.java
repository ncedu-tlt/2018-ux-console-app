package ru.ncedu.consoleapp.repositories;

import ru.ncedu.consoleapp.models.City;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CitysRepository implements Repository<City> 
{

	private static CitysRepository instance;

	private final List<City> citys;

	private CitysRepository(){
        	citys = new ArrayList<>();
	}

	public static CitysRepository getInstance()
	{
        	if (instance == null)
		{
            		instance = new CitysRepository();
		}
        	return instance;
	}

	public List<City> get(){
        	return citys;
	}

	@Override
	public City get(long id){
		return citys.stream().filter(city -> city.getId() == id).findFirst().orElse(null);
	}

	public City add(City object)
	{
        	City city = new City(object);
        	city.setId(generateId());

        	citys.add(city);

        	return city;
    	}

	public City update(City object)
	{
		City city = new City(object);

		citys.remove(city);
		citys.add(city);

		return city;
	}

	public boolean remove(City city) {
		return citys.remove(city);
	}

	@Override
	public boolean remove(long id) {
		return remove(new City(id));
	}

	private long generateId()
	{
		long id = 0;
		for (City city : citys)
		{
			id = Math.max(id, city.getId());
		}
		return ++id;
	}
}