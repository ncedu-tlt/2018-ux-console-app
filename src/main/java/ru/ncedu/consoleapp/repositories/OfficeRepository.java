package ru.ncedu.consoleapp.repositories;


import ru.ncedu.consoleapp.models.Office;

import java.util.ArrayList;
import java.util.List;

public class OfficeRepository implements Repository<Office> {

    private static OfficeRepository instance;
    private List<Office> offices;

    private OfficeRepository() {
        offices = new ArrayList<>();
    }

    public static OfficeRepository getInstance() {
        if (instance == null) {
            instance = new OfficeRepository();
        }
        return instance;
    }

    public List<Office> get() {
        return offices;
    }

    @Override
    public Office get(long id) {
        return offices.stream().filter(office -> office.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Office add(Office object) {
        Office office = new Office(object);
        office.setId(generateID());

        offices.add(office);

        return office;
    }

    private long generateID() {
        long id = 0;
        for (Office office : offices) {
            id = Math.max(id, office.getId());
        }
        id++;
        return id;
    }

    @Override
    public Office update(Office object) {
        Office office = new Office(object);
        offices.remove(office);
        offices.add(office);
        return office;
    }

    @Override
    public boolean remove(Office object) {
        return offices.remove(object);
    }


    public boolean remove(long id) {
        return remove(new Office(id));
    }

}
